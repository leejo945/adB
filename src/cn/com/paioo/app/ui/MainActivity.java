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
 * ����������������ԭ�� �໬�˵�+fragment
 * 
 * 1.��һ�ν����ʼ��ҳ�˵��� 2.fragment��̬���ؽ��롣ֻ��fragmentΪ�յ�ʱ��ż��أ����fragment�����ݸı���Ҫ���أ���
 * 3.���tab����fragment֮�����غ���ʾ 4.fragment���л�����ж�̬��Ч��
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
		// �����������ĵ���ʾ���֣��������������Activity��һ���ġ�
		setContentView(R.layout.slidemenu_content);
		// ���ò໬�˵��Ĳ���
		setBehindContentView(R.layout.slidemenu_menu);
		// ��ʼ���໬�˵�

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
		// �����ʱ��Ĭ����ʾ����
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
		// menu������ʱ����Ӱ
		sm.setShadowWidth(0);
		// ��Ӱ����ɫ
		// sm.setShadowDrawable(R.drawable.shadow);

		Display d = getWindowManager().getDefaultDisplay();
		// �໬�˵��������ұߵľ��� (Ϊ��Ļ���һ��)
		sm.setBehindOffset(d.getWidth() / 2);
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
