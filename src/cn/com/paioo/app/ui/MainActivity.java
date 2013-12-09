package cn.com.paioo.app.ui;

import java.util.ArrayList;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;
import cn.com.paioo.app.entity.SlideMenuItem;
import cn.com.paioo.app.util.TitleUtil;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends SlidingActivity {

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
		TitleUtil.show(this);
		createSlideMenu();
		initSlideMenu();
	}

	private void initSlideMenu() {
         ListView  lv = (ListView) findViewById(R.id.slidemenu_menu_lv);
        ArrayList<SlideMenuItem> list = new ArrayList<SlideMenuItem>();
         for(int i=0;i<10;i++){
        	 SlideMenuItem item = new SlideMenuItem();
        	 item.name = "首页"+i;
        	 list.add(item);
         }
         
         
         
         
         lv.setAdapter(new SlideMenuAdapter(this, list));
	}

	private void createSlideMenu() {
		// // customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		// menu滑动的时候阴影
		sm.setShadowWidth(0);
		// 阴影的颜色
		// sm.setShadowDrawable(R.drawable.shadow);
		// 侧滑菜单滑动离右边的距离
		sm.setBehindOffset(250);
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
