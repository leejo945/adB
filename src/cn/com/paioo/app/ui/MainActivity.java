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
		// �����������ĵ���ʾ���֣��������������Activity��һ���ġ�
		setContentView(R.layout.slidemenu_content);
		// ���ò໬�˵��Ĳ���
		setBehindContentView(R.layout.slidemenu_menu);
		// ��ʼ���໬�˵�

		init();

	}

	private void init() {
		TitleUtil.show(this);
		createSlideMenu();
		initSlideMenu();
	}

	private void initSlideMenu() {
//         ListView  lv = (ListView) findViewById(R.id.slidemenu_menu_lv);
//        ArrayList<SlideMenuItem> list = new ArrayList<SlideMenuItem>();
//         for(int i=0;i<10;i++){
//        	 SlideMenuItem item = new SlideMenuItem();
//        	 item.name = "��ҳ"+i;
//        	 list.add(item);
//         }
//         
//         
//         
//         
//         lv.setAdapter(new SlideMenuAdapter(this, list));
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
