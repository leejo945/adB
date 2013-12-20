package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.AppUpdateInfo;

import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * 整个导航界面的设计原则： 侧滑菜单+fragment
 * 
 * 1.第一次进入初始首页菜单。 2.fragment动态加载进入。只有fragment为空的时候才加载（如果fragment有数据改变需要加载）。
 * 3.点击tab是在fragment之间隐藏和显示 4.fragment的切换最好有动态的效果
 * 
 * 
 * 
 * @author Administrator
 * 
 */

@SuppressLint("ResourceAsColor")
public class MainActivity extends SlidingFragmentActivity implements
		OnItemClickListener, OnCheckedChangeListener {
	String tag = "MainActivity";

	// 导航tab对应的Fragment
	private Map<Integer, Fragment> frMap = new HashMap<Integer, Fragment>();

	// main。。tab
	private RadioGroup mTabRG;
	private RadioButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	// 用来存放，所有fragment的容器列表
	private Map<Integer, View> mConts = new HashMap<Integer, View>();

	private FragmentManager fm;

	private static int mainIndex = Constant.SLIDEMENU_INDEX_HOME;
	// 侧滑菜单的最上面的条目
	private LinearLayout slidemenuItemRoot;

	private static boolean isTab;
	// 为了个点击条目产生一个选择效果
	private static View frontClickView;

	private static final int APPUPDATE = 0;
    private static final int DESTORYFRISTBACK =1;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case APPUPDATE:
				AppUpdateInfo appInfo = (AppUpdateInfo) msg.obj;
				showUpdateDialog(appInfo);
				App.appUpdateInfo = appInfo;
				break;

			case DESTORYFRISTBACK:
				 fristBack = 0;
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		App.addActivity(this);
		super.onCreate(savedInstanceState);
		// 设置我们正文的显示布局，这和我们正常的Activity是一样的。
		setContentView(R.layout.slidemenu_content);
		// 设置侧滑菜单的布局
		setBehindContentView(R.layout.slidemenu_menu);
		// 初始化侧滑菜单

		init();

		// 检查是否要升级。。。

		// if (App.appUpdateInfo == null) {
		// updateApp();
		// }

	}

	private void updateApp() {
		App.pool.addTask(new Thread() {
			@Override
			public void run() {
				AppUpdateInfo appInfo = DataService
						.getAppUpdateInfo(MainActivity.this);
				if (appInfo != null) {
					// 要更新 弹出对话框给用户提示
					Message msg = handler.obtainMessage(APPUPDATE, appInfo);
					handler.sendMessage(msg);
				}

			}
		});
	}

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
								MainActivity.this);
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

	private void init() {
		TitleUtil.show(this, mainIndex);
		initSlideMenu();
		initMainTab();
	}

	/**
	 * 侧滑菜单的初始化 （样式，，数据，监听等）
	 */

	private void initSlideMenu() {
		// // customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		// menu滑动的时候阴影
		sm.setShadowWidth(0);
		// 阴影的颜色
		// sm.setShadowDrawable(R.drawable.shadow);

		Display d = getWindowManager().getDefaultDisplay();
		// 侧滑菜单滑动离右边的距离 (为屏幕宽的一半)
		sm.setBehindOffset(d.getWidth() / 2);
		//
		sm.setFadeDegree(0.35f);
		// 设置侧滑条拉出来需不需要联动。0-1之间
		sm.setBehindScrollScale(0.5f);
		// //设置slding menu的几种手势模式
		// //TOUCHMODE_FULLSCREEN 全屏模式，在content页面中，滑动，可以打开sliding menu
		// //TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开slding
		// ,你需要在屏幕边缘滑动才可以打开slding menu
		// //TOUCHMODE_NONE 自然是不能通过手势打开啦
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置从左边拉动还是从右边拉动出现菜单
		sm.setMode(SlidingMenu.LEFT);

		ListView slv = (ListView) findViewById(R.id.slidemenu_lv);

		if (slv.getHeaderViewsCount() == 0) {
			View head = View.inflate(this, R.layout.slidemenu_head, null);
			slidemenuItemRoot = (LinearLayout) head
					.findViewById(R.id.slidemenu_item_root_ll);
			ImageView headItemIcon = (ImageView) head
					.findViewById(R.id.slidemenu_item_icon_iv);
			TextView headItemName = (TextView) head
					.findViewById(R.id.slidemenu_item_name_tv);
			slidemenuItemRoot.setBackgroundColor(R.color.title_bar_bg);
			headItemIcon.setImageResource(R.drawable.slidemenu_home);
			headItemName.setText(R.string.slidemenu_home);
			slv.addHeaderView(head);
		}

		int[] icons = { R.drawable.slidemenu_financial,
				R.drawable.slidemenu_preview, R.drawable.slidemenu_recharge,
				R.drawable.slidemenu_friends, R.drawable.slidemenu_transfer,
				R.drawable.slidemenu_setup };
		int[] names = { R.string.slidemenu_finance, R.string.slidemenu_preview,
				R.string.slidemenu_recharge, R.string.slidemenu_friends,
				R.string.slidemenu_transfer, R.string.slidemenu_setup };

		slv.setAdapter(new SlideMenuAdapter(this, icons, names));

		slv.setOnItemClickListener(this);

		// 存放容器视图
		mConts.put(R.id.content_home, findViewById(R.id.content_home));
		mConts.put(R.id.content_finance, findViewById(R.id.content_finance));
		mConts.put(R.id.content_preview, findViewById(R.id.content_preview));
		mConts.put(R.id.content_recharge, findViewById(R.id.content_recharge));
		mConts.put(R.id.content_friends, findViewById(R.id.content_friends));
		mConts.put(R.id.content_transfer, findViewById(R.id.content_transfer));
		mConts.put(R.id.content_setup, findViewById(R.id.content_setup));

	}

	private void initMainTab() {
		fm = getSupportFragmentManager();
		frMap.put(R.id.content_home, new TabHomeFragment());
		frMap.put(R.id.content_finance, new TabFinanceFragment());
		frMap.put(R.id.content_preview, new TabPreViewFragmet());
		frMap.put(R.id.content_recharge, new NavRechargeFragment());
		frMap.put(R.id.content_friends, new NavRecommendFriendsFragment());
		frMap.put(R.id.content_transfer, new NavTransferAccountFragment());
		frMap.put(R.id.content_setup, new TabSetUpFragment());

		// 进入的时候默认显示界面
		fm.beginTransaction()
				.replace(R.id.content_home, frMap.get(R.id.content_home))
				.commit();

		mTabRG = (RadioGroup) findViewById(R.id.main_tab_root_rg);

		mTabHome = (RadioButton) findViewById(R.id.main_tab_home_rb);
		mTabFinance = (RadioButton) findViewById(R.id.main_tab_finance_rb);
		mTabPreview = (RadioButton) findViewById(R.id.main_tab_preview_rb);
		mTabSetup = (RadioButton) findViewById(R.id.main_tab_setup_rb);
		mTabRG.setOnCheckedChangeListener(this);

	}

	private void hiddenView(int conId) {
		Set<Integer> keys = mConts.keySet();
		for (int key : keys) {
			if (key == conId) {
				mConts.get(key).setVisibility(View.VISIBLE);
			} else {
				mConts.get(key).setVisibility(View.GONE);
			}

		}

	}

	/**
	 * 用于判断fragement 是显示还是隐藏等等
	 */
	public void fragmentToggle(int conId) {
		hiddenView(conId);
		Fragment fragment = frMap.get(conId);
		if (fragment != null) {
			if (!fragment.isAdded()) {// 如果整个fragment是add。
				fm.beginTransaction().replace(conId, fragment).commit();
			} else {
				Log.e("paioo", "该项目已经添加");
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 这个是为了监听侧滑菜单是打开还是关闭。自动判断是打开是还是关闭
		toggle();

		// 为了个点击条目产生一个选择效果
		if (frontClickView != null) {
			frontClickView.setBackgroundColor(Color.rgb(51, 51, 49));
		}
		view.setBackgroundColor(R.color.title_bar_bg);
		frontClickView = view;

		if (slidemenuItemRoot != null) {
			if (position != 0) {// 只要不是点击的第一个条目，那么就设置第一个条目的背景
				slidemenuItemRoot.setBackgroundColor(Color.rgb(51, 51, 49));
			} else {// 如果是第一个条目
				slidemenuItemRoot.setBackgroundColor(R.color.title_bar_bg);
			}
		}

		isTab = false;

		switch (position) {
		case 0: {// 首页
			isTab = true;
			mTabHome.setChecked(true);
			fragmentToggle(R.id.content_home);
			break;
		}
		case 1: {// 财务
			isTab = true;
			mTabFinance.setChecked(true);
			fragmentToggle(R.id.content_finance);
			break;
		}
		case 2: {// 预览
			isTab = true;
			mTabPreview.setChecked(true);
			fragmentToggle(R.id.content_preview);
			break;
		}

		case 3: {// 账号充值
			fragmentToggle(R.id.content_recharge);
			break;
		}
		case 4: {// 推荐好友
			fragmentToggle(R.id.content_friends);

			break;
		}
		case 5: {// 余额转账
			fragmentToggle(R.id.content_transfer);
			break;
		}
		case 6: {// 设置
			isTab = true;
			mTabSetup.setChecked(true);
			fragmentToggle(R.id.content_setup);
			break;
		}

		}

		if (!isTab) {// 如果不是tab,隐藏tabbar
			mTabRG.setVisibility(View.GONE);
		} else {// 如果是tab，显示
			mTabRG.setVisibility(View.VISIBLE);
		}

		// 点击title部分需要变化
		TitleUtil.show(this, position);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// if(isTab){//点击侧滑菜单的时候，tab部分check也会做相应的变化。从而触发这个方法的执行。所以执行了。两次。这里阻止其重复执行
		// return;
		// }

		int position = 0;
		switch (checkedId) {
		case R.id.main_tab_home_rb:
			position = 0;
			fragmentToggle(R.id.content_home);
			break;
		case R.id.main_tab_finance_rb:
			position = 1;
			fragmentToggle(R.id.content_finance);
			break;
		case R.id.main_tab_preview_rb:
			position = 2;
			fragmentToggle(R.id.content_preview);
			break;
		case R.id.main_tab_setup_rb:
			position = 6;
			fragmentToggle(R.id.content_setup);
			break;
		}

		TitleUtil.show(this, position);
	}

	private static long fristBack;
	private static final int BACKSPACTIEM = 4000;

	@Override
	public void onBackPressed() {
		if (fristBack == 0) {
			MyToast.show(this, R.string.warn_toast_again_click_exit);
			fristBack = System.currentTimeMillis();
			handler.sendEmptyMessageDelayed(DESTORYFRISTBACK, BACKSPACTIEM);
		} else {
			Log.e(tag, System.currentTimeMillis() - fristBack + "");
			if (System.currentTimeMillis() - fristBack <=BACKSPACTIEM) {
				//退出app
				App.exit();
				fristBack = 0;
			}
		}

	}
	 

}
