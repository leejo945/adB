package cn.com.paioo.app.ui;

import java.util.ArrayList;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SplashAdapter;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.NetUtil;
import cn.com.paioo.app.util.UIHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class SplashActivity extends Activity// BaseActivity
		implements OnPageChangeListener {
	private static final int LOGOSHOW = 0;
	private RadioButton mPoint0, mPoint1, mPoint2, mPoint3;
	private ArrayList<RadioButton> points;

	// private Handler handler = new Handler() {
	// public void handleMessage(android.os.Message msg) {
	// switch (msg.what) {
	// case LOGOSHOW:
	// if(NetUtil.isNetworkConnected(getApplicationContext())){
	//
	// }
	//
	//
	// User user = App.getUser();
	// LinearLayout mLoginRe = (LinearLayout)SplashActivity.this
	// .findViewById(R.id.splash_login_register_ll);
	// if (user == null) {// Ã»ÓÐµÇÂ¼
	// mLoginRe.setVisibility(View.VISIBLE);
	// } else {
	// mLoginRe.setVisibility(View.GONE);
	// }
	// break;
	//
	// }
	// };
	// };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		App.addActivity(this);
		setContentView(R.layout.splash);
		super.onCreate(savedInstanceState);
		init();
	}

	public void init() {
		// handler.sendEmptyMessageDelayed(LOGOSHOW, 2000);
		ViewPager vp = (ViewPager) findViewById(R.id.splash_vp);

		points = new ArrayList<RadioButton>();
		points.add((RadioButton) findViewById(R.id.splash_point0_rb));
		points.add((RadioButton) findViewById(R.id.splash_point1_rb));
		points.add((RadioButton) findViewById(R.id.splash_point2_rb));
		points.add((RadioButton) findViewById(R.id.splash_point3_rb));

		ArrayList<View> vs = new ArrayList<View>();
		vs.add(View.inflate(this, R.layout.splash_vp0, null));
		vs.add(View.inflate(this, R.layout.splash_vp1, null));
		vs.add(View.inflate(this, R.layout.splash_vp2, null));
		vs.add(View.inflate(this, R.layout.splash_vp3, null));
		SplashAdapter adapter = new SplashAdapter(vs);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(this);
	}

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

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		//MyToast.show(this, arg0+"");
		points.get(arg0).setChecked(true);
	 
	}
}
