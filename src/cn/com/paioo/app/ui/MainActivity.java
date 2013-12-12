package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;
import cn.com.paioo.app.entity.SlideMenuItem;
import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;

import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

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

public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {
	// 导航tab对应的Fragment
	private static Fragment mFragmentHome, mFragmentFinance, mFragmentPreView,
			mFragmentSetup;

	// 侧滑菜单对应的条目
	private LinearLayout mSlideMenuItemHome, mSlideMenuItemFinance,
			mSlideMenuItemPreview, mSlideMenuItemRecharge,
			mSlideMenuItemFriend, mSlideMenuItemTransfer, mSlideMenuItemTicket,
			mSlideMenuItemSetup;

	// main。。tab
	private ImageButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	private FragmentManager fm;
	private ArrayList<ImageButton> tabs;
	private static int mainIndex = Constant.MAIN_INDEX_HOME;

	// 侧滑菜单当前的选项 ,默认为首页
	private static int slidemenuCurSel = R.id.slidemenu_item_home_ll;
	private static int tabCurSel = R.id.main_tab_home_ib;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置我们正文的显示布局，这和我们正常的Activity是一样的。
		setContentView(R.layout.slidemenu_content);
		// 设置侧滑菜单的布局
		setBehindContentView(R.layout.slidemenu_menu);
		// 初始化侧滑菜单

		//init();

	}

	private void init() {
		TitleUtil.show(this, mainIndex);
		initSlideMenu();
		initMainTab();
		setListener();
	}

	Map<Integer, View> map = new HashMap<Integer, View>();

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

		mSlideMenuItemHome = (LinearLayout) findViewById(R.id.slidemenu_item_home_ll);
		mSlideMenuItemFinance = (LinearLayout) findViewById(R.id.slidemenu_item_finac_ll);
		mSlideMenuItemPreview = (LinearLayout) findViewById(R.id.slidemenu_item_preview_ll);
		mSlideMenuItemRecharge = (LinearLayout) findViewById(R.id.slidemenu_item_recharge_ll);
		mSlideMenuItemFriend = (LinearLayout) findViewById(R.id.slidemenu_item_friends_ll);
		mSlideMenuItemTransfer = (LinearLayout) findViewById(R.id.slidemenu_item_transfer_ll);
		mSlideMenuItemTicket = (LinearLayout) findViewById(R.id.slidemenu_item_ticket_ll);
		mSlideMenuItemSetup = (LinearLayout) findViewById(R.id.slidemenu_item_setup_ll);

		map.put(R.id.slidemenu_item_home_ll, mSlideMenuItemHome);
		map.put(R.id.slidemenu_item_finac_ll, mSlideMenuItemFinance);
		map.put(R.id.slidemenu_item_preview_ll, mSlideMenuItemPreview);
		map.put(R.id.slidemenu_item_recharge_ll, mSlideMenuItemRecharge);
		map.put(R.id.slidemenu_item_friends_ll, mSlideMenuItemFriend);
		map.put(R.id.slidemenu_item_transfer_ll, mSlideMenuItemTransfer);
		map.put(R.id.slidemenu_item_ticket_ll, mSlideMenuItemTicket);
		map.put(R.id.slidemenu_item_setup_ll, mSlideMenuItemSetup);

	}

	private void initMainTab() {
		fm = getSupportFragmentManager();
		mFragmentHome = new NavHomeFragment();
		// 进入的时候默认显示界面
		fm.beginTransaction().replace(R.id.ccc, mFragmentHome).commit();

		mTabHome = (ImageButton) findViewById(R.id.main_tab_home_ib);
		mTabFinance = (ImageButton) findViewById(R.id.main_tab_finance_ib);
		mTabPreview = (ImageButton) findViewById(R.id.main_tab_preview_ib);
		mTabSetup = (ImageButton) findViewById(R.id.main_tab_setup_ib);

		map.put(R.id.main_tab_home_ib, mTabHome);
		map.put(R.id.main_tab_finance_ib, mTabFinance);
		map.put(R.id.main_tab_preview_ib, mTabPreview);
		map.put(R.id.main_tab_setup_ib, mTabSetup);

		// tabs = new ArrayList<ImageButton>();
		// tabs.add((ImageButton) findViewById(R.id.main_tab_home_ib));
		// tabs.add((ImageButton) findViewById(R.id.main_tab_finance_ib));
		// tabs.add((ImageButton) findViewById(R.id.main_tab_preview_ib));
		// tabs.add((ImageButton) findViewById(R.id.main_tab_setup_ib));
	}

	private void setListener() {
		mSlideMenuItemHome.setOnClickListener(this);
		mSlideMenuItemFinance.setOnClickListener(this);
		mSlideMenuItemPreview.setOnClickListener(this);
		mSlideMenuItemRecharge.setOnClickListener(this);
		mSlideMenuItemFriend.setOnClickListener(this);
		mSlideMenuItemTransfer.setOnClickListener(this);
		mSlideMenuItemTicket.setOnClickListener(this);
		mSlideMenuItemSetup.setOnClickListener(this);
		mTabHome.setOnClickListener(this);
		mTabFinance.setOnClickListener(this);
		mTabPreview.setOnClickListener(this);
		mTabSetup.setOnClickListener(this);
	}

	public void onClick(View v) {
		// 标记是不是点击得tab，如果是tab就不用执行 toggle
		boolean isTab = false;
		int id = v.getId();
		switch (id) {

		// tab+slidemenu 首页 (无论点击tab还是menu。二者都要同时变化)
		case R.id.main_tab_home_ib:
			mTabHome.setImageResource(R.drawable.main_tab_home_pre_che);
			isTab = true;
		case R.id.slidemenu_item_home_ll:
			mainIndex = Constant.MAIN_INDEX_HOME;
			fm.beginTransaction().replace(R.id.ccc, new NavHomeFragment())
					.commit();
			break;

		// tab+slidemenu 财务
		case R.id.main_tab_finance_ib:
			mTabFinance.setImageResource(R.drawable.main_tab_finance_pre_che);
			isTab = true;
		case R.id.slidemenu_item_finac_ll:
			mainIndex = Constant.MAIN_INDEX_FINANCE;
			ImageButton ib1 = (ImageButton) findViewById(R.id.main_tab_finance_ib);
			ib1.setImageResource(R.drawable.main_tab_finance_pre_che);
			fm.beginTransaction().replace(R.id.ccc, new NavFinanceFragment())
					.commit();
			break;

		// tab+slidemenu 预览
		case R.id.main_tab_preview_ib:
			mTabPreview.setImageResource(R.drawable.main_tab_preview_pre_che);
			isTab = true;
		case R.id.slidemenu_item_preview_ll:
			mainIndex = Constant.MAIN_INDEX_PREVIEW;
			fm.beginTransaction().replace(R.id.ccc, new NavPreViewFragmet())
					.commit();

			break;

		// tab+slidemenu 设置
		case R.id.main_tab_setup_ib:
			mTabSetup.setImageResource(R.drawable.main_tab_setup_pre_che);
			isTab = true;
		case R.id.slidemenu_item_setup_ll:
			mainIndex = Constant.MAIN_INDEX_SETUP;
			fm.beginTransaction().replace(R.id.ccc, new NavSetUpFragment())
					.commit();

			break;
		// 侧滑菜单的点击事件

		// 账号充值
		case R.id.slidemenu_item_recharge_ll: {

			break;
		}
		// 推荐好友
		case R.id.slidemenu_item_friends_ll: {
			break;
		}
		// 余额转账
		case R.id.slidemenu_item_transfer_ll: {
			break;
		}
		// 优惠券
		case R.id.slidemenu_item_ticket_ll: {
			break;
		}

		}

		TitleUtil.show(this, mainIndex);
		// 是侧滑菜单才执行
		if (!isTab) {
			toggle();
		}

	}

}
