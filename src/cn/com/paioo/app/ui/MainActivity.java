package cn.com.paioo.app.ui;

import java.util.ArrayList;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;
import cn.com.paioo.app.entity.SlideMenuItem;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

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

	ArrayList<View> temp = new ArrayList<View>();
	
	
	
	
	private void init() {
		TitleUtil.show(this);
		createSlideMenu();
		initSlideMenu();
		initMainTab();
		 
		 
		temp.add(findViewById(R.id.slidemenu_content_home));
		temp.add(findViewById(R.id.slidemenu_content_finance));
		temp.add(findViewById(R.id.slidemenu_content_preview));
		temp.add(findViewById(R.id.slidemenu_content_setup));
		
	}
     
	public void onClick(View v){
		switch (v.getId()) {
		
		//设置界面
		case R.id.setup_modify_contact_way_rl:
			//修改联系方式
			UIHelper.switcher(this, ModifyContactWayActivity.class);
			break;

		case R.id.setup_modify_password_rl:
			UIHelper.switcher(this, ModifyPassword.class);
			//修改密码
			break;
		case R.id.setup_suggest_rl:
			UIHelper.switcher(this, SuggestActivity.class);
			//意见反馈
			break;
		case R.id.setup_about_us_rl:
			//关于我们
			UIHelper.switcher(this, AboutUs.class);
			break;
		case R.id.setup_safe_exit_bt:
			//退出账号
			
			UIHelper.switcher(this, LoginActivity.class);
			
			
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	private void show(int a){
    	for(int i=0;i<temp.size();i++){
    		if(i==a){
    			temp.get(i).setVisibility(View.VISIBLE);
    		}else{
    			temp.get(i).setVisibility(View.GONE);
    		}
    	}
    }
	private void initMainTab() {
		// TODO Auto-generated method stub
		RadioGroup rg = (RadioGroup) findViewById(R.id.main_tab_rg);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.main_tab_home_rb:
                    show(0);
					break;

				case R.id.main_tab_finance_rb:
					show(1);
					break;

				case R.id.main_tab_preview_rb:
					show(2);
					break;

				case R.id.main_tab_setup_rb:
					show(3);
					break;
				}

			}
		});

	}

	private void initSlideMenu() {
		// ListView lv = (ListView) findViewById(R.id.slidemenu_menu_lv);
		// ArrayList<SlideMenuItem> list = new ArrayList<SlideMenuItem>();
		// for(int i=0;i<10;i++){
		// SlideMenuItem item = new SlideMenuItem();
		// item.name = "首页"+i;
		// list.add(item);
		// }
		//
		//
		//
		//
		// lv.setAdapter(new SlideMenuAdapter(this, list));
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
