package cn.com.paioo.app.ui;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.NetUtil;
import cn.com.paioo.app.util.UIHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SplashActivity extends BaseActivity {
	private static final int LOGOSHOW = 0;
	private  Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOGOSHOW:
				if(NetUtil.isNetworkConnected(getApplicationContext())){
					
				}
				
				
				User user = App.getUser();
				LinearLayout mLoginRe = (LinearLayout)SplashActivity.this
						.findViewById(R.id.splash_login_register_ll);
				if (user == null) {// Ã»ÓÐµÇÂ¼
					mLoginRe.setVisibility(View.VISIBLE);
				} else {
					mLoginRe.setVisibility(View.GONE);
				}
				break;

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.splash);
		super.onCreate(savedInstanceState);
	}

//	@Override
//	public void init() {
//		handler.sendEmptyMessageDelayed(LOGOSHOW, 2000);
//
//		super.init();
//	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.splash_login_bt:
			UIHelper.switcher(this, LoginActivity.class);
			break;

		case R.id.splash_register_bt:
			UIHelper.switcher(this, RegisterActivity.class);
			break;
		}
	}
}
