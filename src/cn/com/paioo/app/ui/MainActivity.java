package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;

import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;
import cn.com.paioo.app.view.ScrollListView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.os.Handler;
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
	private static Fragment mFragmentHome, mFragmentFinance, mFragmentPreView,
			mFragmentRecharege, mFragmentFriends, mFragmentTransfer,
			mFragmentSetup;

	// 侧滑菜单对应的条目
	// private LinearLayout mSlideMenuItemHome, mSlideMenuItemFinance,
	// mSlideMenuItemPreview, mSlideMenuItemRecharge,
	// mSlideMenuItemFriend, mSlideMenuItemTransfer, mSlideMenuItemTicket,
	// mSlideMenuItemSetup;

	// main。。tab
	private RadioGroup mTabRG;
	private RadioButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	private FragmentManager fm;

	private static int mainIndex = Constant.SLIDEMENU_INDEX_HOME;
	// 侧滑菜单的最上面的条目
	private LinearLayout slidemenuItemRoot;
	// 为了个点击条目产生一个选择效果
	private static View frontClickView;
	 
	 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置我们正文的显示布局，这和我们正常的Activity是一样的。
		setContentView(R.layout.slidemenu_content);
		// 设置侧滑菜单的布局
		setBehindContentView(R.layout.slidemenu_menu);
		// 初始化侧滑菜单

		init();

	}

	private void init() {
		TitleUtil.show(this, mainIndex);
		initSlideMenu();
		initMainTab();
		// setListener();
	}

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
				R.string.slidemenu_transfer,
				R.string.slidemenu_setup };

		slv.setAdapter(new SlideMenuAdapter(this, icons, names));

		slv.setOnItemClickListener(this);

	 
	}

	private void initMainTab() {
		fm = getSupportFragmentManager();
		mFragmentHome = new TabHomeFragment();
		mFragmentFinance = new TabFinanceFragment();
		mFragmentPreView = new TabPreViewFragmet();
		mFragmentRecharege = new NavRechargeFragment();
		mFragmentFriends = new NavRecommendFriendsFragment();
		mFragmentTransfer = new NavTransferAccountFragment();
		mFragmentSetup = new TabSetUpFragment();

		// 进入的时候默认显示界面
		fm.beginTransaction().replace(R.id.aaa, mFragmentHome).commit();

		mTabRG = (RadioGroup) findViewById(R.id.main_tab_root_rg);

		 
		 mTabHome = (RadioButton) findViewById(R.id.main_tab_home_rb);
		 mTabFinance = (RadioButton) findViewById(R.id.main_tab_finance_rb);
		 mTabPreview = (RadioButton) findViewById(R.id.main_tab_preview_rb);
		 mTabSetup = (RadioButton) findViewById(R.id.main_tab_setup_rb);
		 mTabRG.setOnCheckedChangeListener(this);
	}

 

	/**
	 * 用于判断fragement 是显示还是隐藏等等
	 */
	public void fragmentToggle(FragmentTransaction ft, Fragment fragment,
			int fragmentIndex) {
		if (fragment != null) {
			ft.replace(R.id.aaa, fragment).commit();
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
		boolean isTab = false;
		 
		FragmentTransaction ft = fm.beginTransaction();
		switch (position) {
		case 0: {// 首页
			isTab = true;
			mTabHome.setChecked(true);
			fragmentToggle(ft, mFragmentHome, position);
			break;
		}
		case 1: {// 财务
			isTab = true;
			mTabFinance.setChecked(true);
			fragmentToggle(ft, mFragmentFinance, position);
			break;
		}
		case 2: {// 预览
			isTab = true;
			mTabPreview.setChecked(true);
			fragmentToggle(ft, mFragmentPreView, position);
			break;
		}

		case 3: {// 账号充值
			fragmentToggle(ft, mFragmentRecharege, position);
			break;
		}
		case 4: {// 推荐好友
			fragmentToggle(ft, mFragmentFriends, position);

			break;
		}
		case 5: {// 余额转账
			fragmentToggle(ft, mFragmentTransfer, position);
			break;
		}
		case 6: {// 设置
			isTab = true;
			mTabSetup.setChecked(true);
			fragmentToggle(ft, mFragmentSetup, position);

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
		FragmentTransaction ft = fm.beginTransaction();
		 switch (checkedId) {
		case R.id.main_tab_home_rb:
			fragmentToggle(ft, mFragmentHome, checkedId);
			break;
		case  R.id.main_tab_finance_rb:
			fragmentToggle(ft, mFragmentFinance, checkedId);
			break;
		case  R.id.main_tab_preview_rb:
			fragmentToggle(ft, mFragmentPreView, checkedId);
			break;
		case  R.id.main_tab_setup_rb:
			fragmentToggle(ft, mFragmentSetup, checkedId);
			break;
		}
		
	}

	 private static long fristBack;
	 private static final int BACKSPACTIEM = 5000;
//	@Override
//	public void onBackPressed() {
//		if(fristBack==0){
//			 MyToast.show(this, R.string.warn_toast_again_click_exit);
//			 fristBack = System.currentTimeMillis();
//			 handler.sendEmptyMessageDelayed(0, BACKSPACTIEM);
//		}else{
//			Log.e(tag, System.currentTimeMillis()-fristBack+"");
//            if(System.currentTimeMillis()-fristBack<BACKSPACTIEM){
//            	fristBack  = 0;
//           }			
//		}
//	   
//		 
//	}
	private Handler handler = new Handler(){
		 public void handleMessage(android.os.Message msg) {
			  fristBack = 0;
		 };
	};
	
	 
}
