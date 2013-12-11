package cn.com.paioo.app.ui;

import java.util.ArrayList;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;
import cn.com.paioo.app.entity.SlideMenuItem;
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

public class MainActivity extends SlidingFragmentActivity {
	public FragmentManager fm;
	public static Fragment mHome, mFinance, mPreView, mSetup;
    private ArrayList<ImageButton> tabs;
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
		// TitleUtil.show(this);

		createSlideMenu();
		initMainTab();

	}

	private void initMainTab() {
		initFragment();
		 tabs =   new ArrayList<ImageButton>();
		 tabs.add((ImageButton) findViewById(R.id.main_tab_home_ib));
		 tabs.add((ImageButton) findViewById(R.id.main_tab_finance_ib));
		 tabs.add((ImageButton) findViewById(R.id.main_tab_preview_ib));
		 tabs.add((ImageButton) findViewById(R.id.main_tab_setup_ib));
	}

	private void initFragment() {
		fm = getSupportFragmentManager();
		mHome = new NavHomeFragment();
		// 进入的时候默认显示界面
		fm.beginTransaction().replace(R.id.ccc, mHome).commit();

	}
    
	
	
	
	public void onClick(View v) {
	 
		int id = v.getId();
		switch (id) {
		case R.id.main_tab_home_ib:
			ImageButton ib = (ImageButton) findViewById(id);
            ib.setImageResource(R.drawable.main_tab_home_pre_che);
			fm.beginTransaction().replace(R.id.ccc, new NavHomeFragment())
					.commit();

			break;

		case R.id.main_tab_finance_ib:
			ImageButton ib1 = (ImageButton) findViewById(id);
            ib1.setImageResource(R.drawable.main_tab_finance_pre_che);
			fm.beginTransaction().replace(R.id.ccc, new NavFinanceFragment())
					.commit();
			break;
		case R.id.main_tab_preview_ib:
			fm.beginTransaction().replace(R.id.ccc, new NavPreViewFragmet())
					.commit();

			break;
		case R.id.main_tab_setup_ib:
			fm.beginTransaction().replace(R.id.ccc, new NavSetUpFragment())
					.commit();

			break;
		}
	}

	private void createSlideMenu() {
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
	}
}
