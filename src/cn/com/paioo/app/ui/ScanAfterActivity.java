package cn.com.paioo.app.ui;

import java.util.HashMap;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBackIml;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.ImageManager;
import cn.com.paioo.app.util.LogManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.UIManager;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScanAfterActivity extends BaseActivity {
	String tag = "ScanAfterActivity";
	private ImageView mResultIcon;
	private TextView mResultTV;
	private LinearLayout mProductInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layout_scanresult);
		super.onCreate(savedInstanceState);
		// new NetworkImageView(this);
	}

	@Override
	public void init() {
		mResultIcon = (ImageView) findViewById(R.id.scanresult_iv);
		mResultTV = (TextView) findViewById(R.id.scanresult_tv);
		mProductInfo = (LinearLayout) findViewById(R.id.scan_reulst_pr_info_ll);
		// 扫描的结果传递过来了。。。
		String scanResult = getIntent().getStringExtra(
				ConstantManager.ZXING_SCANRESULT);
	 
		if (!scanResult.matches("\\d{12}")) {
			// 验证失败，非本商家优惠券 （非标准的）
			//ToastManager.show(this, R.string.warn_qrcode_error);
			mResultIcon.setImageResource(R.drawable.scan_error);
			mResultTV.setText(R.string.warn_qrcode_error);
			return;
		}
		// 验证二维码
		verifyQrCode(scanResult);
		super.init();
	}

	private void verifyQrCode(String scanResult) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int advertisedid =  ((App) getApplication()).getUser().advertiseid;
		if(advertisedid==-1){//数据被清除
			ToastManager.show(this, "数据被清除，请重新登录");
			UIManager.switcher(this, LoginActivity.class);
	        ((App)getApplication()).exit();
			return;
		}
		
		map.put("advertiseid",advertisedid);
		final Dialog dialog = UIManager.getLoadingDialog(this,
				R.string.warn_dialog_verify_qrcode);
		dialog.show();

		
		map.put("qrcode", scanResult);
		
		DataService.sendQrInfo(map, getApplicationContext(),
				new NetCallBackIml() {
					@Override
					public void netCallBack(Object response) {
						dialog.dismiss();
						// 返回的response做处理
						Product p = (Product) response;
						
						LogManager.e(tag, "传递回来的p"+p.describe);
						
						// 验证如果优惠券没有使用，p中就会有url ,会有商品描述
						if (StringManager.isEmpty(p.describe)) {
							LogManager.e(tag, "验证失败信息");
							// 商家优惠券。不行
							mResultIcon.setImageResource(R.drawable.scan_error);
							mProductInfo.setVisibility(View.GONE);
						} else {
							LogManager.e(tag, "验证成功信息。。。。");
							//优惠券通过。显示商家信息
							mResultIcon
									.setImageResource(R.drawable.scan_success);
							TextView describe = (TextView) findViewById(R.id.pr_info_describe_tv);
							TextView xfm = (TextView) findViewById(R.id.pr_info_xfm_tv);
							ImageView iv = (ImageView) findViewById(R.id.pr_info_url_niv);
							describe.setText(p.describe);
							xfm.setText(String.valueOf(p.xiaofeima));
							DataService
									.loadImage(iv,
											p.urls[0]);
							
							mProductInfo.setVisibility(View.VISIBLE);
						}
						// 无论商品目前是什么状态。都要提示文字
						mResultTV.setText(p.couponStatus);
						super.netCallBack(response);
					}

					@Override
					public void netErrorCallBack(Context context,
							String errorReason) {
						dialog.dismiss();
						super.netErrorCallBack(context, errorReason);
					}

				});
	}

}
