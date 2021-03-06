package cn.com.paioo.app.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBackIml;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.UIManager;
import cn.com.paioo.app.zxing.CameraManager;
import cn.com.paioo.app.zxing.CaptureActivityHandler;
import cn.com.paioo.app.zxing.InactivityTimer;
import cn.com.paioo.app.zxing.ViewfinderView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class ScanInActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private View result;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;

	private InactivityTimer inactivityTimer;
	// private MediaPlayer mediaPlayer;
	// private boolean playBeep;
	// private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private SurfaceHolder surfaceHolder;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_zxing);
		// 初始化 CameraManager
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		viewfinderView.setCameraManager(CameraManager.get());
		// hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		result = findViewById(R.id.result);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;
		vibrate = true;

	}

	@Override
	protected void onPause() {
		super.onPause();

		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
		if (surfaceHolder != null) {
			surfaceHolder.removeCallback(this);
		}
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {

		try {
			if (surfaceHolder == null) {
				return;
			}
			CameraManager.get().openDriver(surfaceHolder);

			if (handler == null) {
				handler = new CaptureActivityHandler(this, decodeFormats,
						characterSet);
			}
		} catch (Exception e) {
			return;
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	/**
	 * 扫描结果处理类
	 * 
	 * @param obj
	 * @param barcode
	 */
	public void handleDecode(final Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		viewfinderView.setVisibility(View.GONE);
		result.setVisibility(View.VISIBLE);
		playBeepSoundAndVibrate();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ConstantManager.ZXING_SCANRESULT, obj.getText().toString().trim());
		UIManager.switcher(ScanInActivity.this, ScanAfterActivity.class,
				map);
		finish();
 }

	// 充值扫描视图
	private void resetStatusView() {
		findViewById(R.id.result).setVisibility(View.GONE);
		// resultView.setVisibility(View.GONE);
		// statusView.setText(R.string.msg_default_status);
		// statusView.setVisibility(View.VISIBLE);
		viewfinderView.setVisibility(View.VISIBLE);
		// lastResult = null;
	}

	public void restartPreviewAfterDelay(long delayMS) {
		if (handler != null) {
			handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
		}
		resetStatusView();
	}

	// private void initBeepSound() {
	// if (playBeep && mediaPlayer == null) {
	// // The volume on STREAM_SYSTEM is not adjustable, and users found it
	// // too loud,
	// // so we now play on the music stream.
	// setVolumeControlStream(AudioManager.STREAM_MUSIC);
	// mediaPlayer = new MediaPlayer();
	// mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	// mediaPlayer.setOnCompletionListener(beepListener);
	//
	// AssetFileDescriptor file = getResources().openRawResourceFd(
	// R.raw.beep);
	// try {
	// mediaPlayer.setDataSource(file.getFileDescriptor(),
	// file.getStartOffset(), file.getLength());
	// file.close();
	// mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
	// mediaPlayer.prepare();
	// } catch (IOException e) {
	// mediaPlayer = null;
	// }
	// }
	// }
	// 扫描获得结果后。震动一下一下时间200毫秒
	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		// if (playBeep && mediaPlayer != null) {
		// mediaPlayer.start();
		// }
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	// private final OnCompletionListener beepListener = new
	// OnCompletionListener() {
	// public void onCompletion(MediaPlayer mediaPlayer) {
	// mediaPlayer.seekTo(0);
	// }
	// };

}