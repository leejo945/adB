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
		// �����������ĵ���ʾ���֣��������������Activity��һ���ġ�
		setContentView(R.layout.slidemenu_content);
		// ���ò໬�˵��Ĳ���
		setBehindContentView(R.layout.slidemenu_menu);
		// ��ʼ���໬�˵�

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
		
		//���ý���
		case R.id.setup_modify_contact_way_rl:
			//�޸���ϵ��ʽ
			UIHelper.switcher(this, ModifyContactWayActivity.class);
			break;

		case R.id.setup_modify_password_rl:
			UIHelper.switcher(this, ModifyPassword.class);
			//�޸�����
			break;
		case R.id.setup_suggest_rl:
			UIHelper.switcher(this, SuggestActivity.class);
			//�������
			break;
		case R.id.setup_about_us_rl:
			//��������
			UIHelper.switcher(this, AboutUs.class);
			break;
		case R.id.setup_safe_exit_bt:
			//�˳��˺�
			
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
		// item.name = "��ҳ"+i;
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
		// menu������ʱ����Ӱ
		sm.setShadowWidth(0);
		// ��Ӱ����ɫ
		// sm.setShadowDrawable(R.drawable.shadow);
		// �໬�˵��������ұߵľ���
		sm.setBehindOffset(250);
		//
		sm.setFadeDegree(0.35f);
		// ���ò໬���������費��Ҫ������0-1֮��
		sm.setBehindScrollScale(0.5f);
		// //����slding menu�ļ�������ģʽ
		// //TOUCHMODE_FULLSCREEN ȫ��ģʽ����contentҳ���У����������Դ�sliding menu
		// //TOUCHMODE_MARGIN ��Եģʽ����contentҳ���У�������slding
		// ,����Ҫ����Ļ��Ե�����ſ��Դ�slding menu
		// //TOUCHMODE_NONE ��Ȼ�ǲ���ͨ�����ƴ���
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// ���ô�����������Ǵ��ұ��������ֲ˵�
		sm.setMode(SlidingMenu.LEFT);
	}
}
