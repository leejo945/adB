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

public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {
	// ����tab��Ӧ��Fragment
	private static Fragment mFragmentHome, mFragmentFinance, mFragmentPreView,
			mFragmentSetup;

	// �໬�˵���Ӧ����Ŀ
	private LinearLayout mSlideMenuItemHome, mSlideMenuItemFinance,
			mSlideMenuItemPreview, mSlideMenuItemRecharge,
			mSlideMenuItemFriend, mSlideMenuItemTransfer, mSlideMenuItemTicket,
			mSlideMenuItemSetup;

	// main����tab
	private ImageButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	private FragmentManager fm;
	private ArrayList<ImageButton> tabs;
	private static int mainIndex = Constant.MAIN_INDEX_HOME;

	// �໬�˵���ǰ��ѡ�� ,Ĭ��Ϊ��ҳ
	private static int slidemenuCurSel = R.id.slidemenu_item_home_ll;
	private static int tabCurSel = R.id.main_tab_home_ib;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����������ĵ���ʾ���֣��������������Activity��һ���ġ�
		setContentView(R.layout.slidemenu_content);
		// ���ò໬�˵��Ĳ���
		setBehindContentView(R.layout.slidemenu_menu);
		// ��ʼ���໬�˵�

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
		// �����ʱ��Ĭ����ʾ����
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
		// ����ǲ��ǵ����tab�������tab�Ͳ���ִ�� toggle
		boolean isTab = false;
		int id = v.getId();
		switch (id) {

		// tab+slidemenu ��ҳ (���۵��tab����menu�����߶�Ҫͬʱ�仯)
		case R.id.main_tab_home_ib:
			mTabHome.setImageResource(R.drawable.main_tab_home_pre_che);
			isTab = true;
		case R.id.slidemenu_item_home_ll:
			mainIndex = Constant.MAIN_INDEX_HOME;
			fm.beginTransaction().replace(R.id.ccc, new NavHomeFragment())
					.commit();
			break;

		// tab+slidemenu ����
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

		// tab+slidemenu Ԥ��
		case R.id.main_tab_preview_ib:
			mTabPreview.setImageResource(R.drawable.main_tab_preview_pre_che);
			isTab = true;
		case R.id.slidemenu_item_preview_ll:
			mainIndex = Constant.MAIN_INDEX_PREVIEW;
			fm.beginTransaction().replace(R.id.ccc, new NavPreViewFragmet())
					.commit();

			break;

		// tab+slidemenu ����
		case R.id.main_tab_setup_ib:
			mTabSetup.setImageResource(R.drawable.main_tab_setup_pre_che);
			isTab = true;
		case R.id.slidemenu_item_setup_ll:
			mainIndex = Constant.MAIN_INDEX_SETUP;
			fm.beginTransaction().replace(R.id.ccc, new NavSetUpFragment())
					.commit();

			break;
		// �໬�˵��ĵ���¼�

		// �˺ų�ֵ
		case R.id.slidemenu_item_recharge_ll: {

			break;
		}
		// �Ƽ�����
		case R.id.slidemenu_item_friends_ll: {
			break;
		}
		// ���ת��
		case R.id.slidemenu_item_transfer_ll: {
			break;
		}
		// �Ż�ȯ
		case R.id.slidemenu_item_ticket_ll: {
			break;
		}

		}

		TitleUtil.show(this, mainIndex);
		// �ǲ໬�˵���ִ��
		if (!isTab) {
			toggle();
		}

	}

}
