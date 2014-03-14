package cn.com.paioo.app.ui;

import java.util.Map;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBack;
import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.UIManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ScanBeforeActivity extends BaseActivity implements
		OnClickListener, NetCallBack {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.layout_scan);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void init() {
		if (App.appUpdateInfo == null) {

			DataService.getAppUpdateInfo(this);

		}
		super.init();
	}

	@Override
	public void setListener() {
		findViewById(R.id.scan_iv).setOnClickListener(this);
		super.setListener();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.scan_iv:
			UIManager.switcher(this, ScanInActivity.class);
			break;

		}

	}

	/**
	 * 连续按两次返回键就退出
	 */
	private long firstTime;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - firstTime < 4000) {
			((App) getApplication()).exit();
		} else {
			firstTime = System.currentTimeMillis();
			ToastManager.show(this, R.string.warn_toast_again_click_exit);
		}
	}

	/**
	 * 菜单切换用户
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		((App) getApplication()).exit();
		PreferencesManager.setString(this, ConstantManager.SP_USER_NAME, "");
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void netCallBack(Object response) {
		// 更新
		if (response != null && response instanceof AppUpdateInfo) {
			App.appUpdateInfo = (AppUpdateInfo) response;
			 
			showUpdateDialog(App.appUpdateInfo);
		}

	}

	@Override
	public void netErrorCallBack(Context context, String errorReason) {
		// TODO Auto-generated method stub

	}

	/**
	 * 展示升级对话框
	 * 
	 * @param updateInfo
	 */
	private void showUpdateDialog(final AppUpdateInfo updateInfo) {

		final Dialog dialog = new Dialog(this, R.style.MyDialog);
		dialog.setContentView(R.layout.updateapp_dialog);
		TextView content = (TextView) dialog.findViewById(R.id.dialog_content);
		content.setText(updateInfo.description);

		dialog.findViewById(R.id.dialog_sure).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 点击确定马上升级
						DataService.updateAPK(updateInfo.apkurl,
								ScanBeforeActivity.this, handler);
						dialog.dismiss();
					}
				});
		dialog.findViewById(R.id.dialog_cancle).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		dialog.show();
	}

	private NotificationManager mNotificationManager;
	private Notification mNotification;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ConstantManager.UPDATE_APP_INIT:
				Map<String, Object> map = (Map<String, Object>) msg.obj;
				mNotificationManager = (NotificationManager) map
						.get("mNotifyManager");
				mNotification = (Notification) map.get("mNotification");
				break;
			case ConstantManager.UPDATE_APP_MSG:
				int pros = (Integer) msg.obj;
				if (pros == 100) {
					mNotificationManager.cancel(0);
				} else {
					mNotification.setLatestEventInfo(ScanBeforeActivity.this,
							"app更新", "正在下载" + pros + "%", null);
					mNotificationManager.notify(0, mNotification);

				}
				break;
			}
		};
	};

}
