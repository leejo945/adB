package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

 

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.ViewPagerAdapter;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.NetManager;
import cn.com.paioo.app.util.UIManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import cn.com.paioo.app.util.LogManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SplashActivity extends Activity// BaseActivity
		implements OnPageChangeListener {
	String tag = "SplashActivity";

	private ArrayList<RadioButton> points;
	private ArrayList<View> vs;
	private ViewPagerAdapter adapter;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ConstantManager.SHOW:
				// 网络检测
				// if (NetUtil.isNetworkConnected(getApplicationContext())) {
				//
				// }

				User user = App.getUser();
				if (user == null) {// 第一次登录，显示登录和注册按钮，显示指示圆圈
					initIndicate();
				} else {
					// 非第一次登录，那么跳转到主界面
					UIManager.switcher(SplashActivity.this, MainActivity.class);

				}

				break;

			}
		}

		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		App.addActivity(this);
		setContentView(R.layout.splash);
		super.onCreate(savedInstanceState);

		init();

	}

	public void init() {
		// 登录和注册按钮按照需要显示
		handler.sendEmptyMessageDelayed(ConstantManager.SHOW, ConstantManager.SHOW_LOGO_TIME);

		
		//芝麻广告平台必须初始化显示
		initLogo();
	}
   /**
    * logo  初始化
    */
	private void initLogo() {
		ViewPager vp = (ViewPager) findViewById(R.id.splash_vp);
		vs = new ArrayList<View>();
		View item0 = View.inflate(this, R.layout.splash_vp_item, null);
		((ImageView) item0.findViewById(R.id.splash_vp_item_iv))
				.setImageResource(R.drawable.logo);
		vs.add(item0);
		adapter = new ViewPagerAdapter(vs);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(this);
	}
	/**
	 * 注册，登录按钮，指示点以及后三张图片初始化显示
	 */
	private void initIndicate() {
		findViewById(R.id.splash_login_register_ll).setVisibility(
				View.VISIBLE);
		View item1 = View.inflate(SplashActivity.this,
				R.layout.splash_vp_item, null);
		View item2 = View.inflate(SplashActivity.this,
				R.layout.splash_vp_item, null);
		View item3 = View.inflate(SplashActivity.this,
				R.layout.splash_vp_item, null);
		((ImageView) item1.findViewById(R.id.splash_vp_item_iv))
				.setImageResource(R.drawable.splash_vp1);
		((ImageView) item2.findViewById(R.id.splash_vp_item_iv))
				.setImageResource(R.drawable.splash_vp2);
		((ImageView) item3.findViewById(R.id.splash_vp_item_iv))
				.setImageResource(R.drawable.splash_vp3);
		vs.add(item1);
		vs.add(item2);
		vs.add(item3);
		points = new ArrayList<RadioButton>();
		points.add((RadioButton) findViewById(R.id.splash_point0_rb));
		points.add((RadioButton) findViewById(R.id.splash_point1_rb));
		points.add((RadioButton) findViewById(R.id.splash_point2_rb));
		points.add((RadioButton) findViewById(R.id.splash_point3_rb));
		findViewById(R.id.splash_point_rg).setVisibility(
				View.VISIBLE);
		adapter.notifyDataSetChanged();
	};
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.splash_login_bt:
			HashMap<String, Object> map =  new HashMap<String, Object>();
			map.put("from",ConstantManager.FROM_SPLASH );
			UIManager.switcher(this, LoginActivity.class,map);
			break;

		case R.id.splash_register_bt:
			UIManager.switcher(this, RegisterActivity.class);
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
		// MyToast.show(this, arg0+"");
		points.get(arg0).setChecked(true);

	}
}
