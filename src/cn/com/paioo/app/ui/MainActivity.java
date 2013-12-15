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

@SuppressLint("ResourceAsColor")
public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener, OnItemClickListener {
	// ����tab��Ӧ��Fragment
	private static Fragment mFragmentHome, mFragmentFinance, mFragmentPreView,
			mFragmentRecharege, mFragmentFriends, mFragmentTransfer,
			mFragmentSetup;

	// �໬�˵���Ӧ����Ŀ
	// private LinearLayout mSlideMenuItemHome, mSlideMenuItemFinance,
	// mSlideMenuItemPreview, mSlideMenuItemRecharge,
	// mSlideMenuItemFriend, mSlideMenuItemTransfer, mSlideMenuItemTicket,
	// mSlideMenuItemSetup;

	// main����tab
	// private RadioButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	private FragmentManager fm;

	private static int mainIndex = Constant.SLIDEMENU_INDEX_HOME;
	// �໬�˵������������Ŀ
	private LinearLayout slidemenuItemRoot;

	private RadioGroup mTabRG;

	// �໬�˵���ǰ��ѡ�� ,Ĭ��Ϊ��ҳ
	// private static int slidemenuCurSel = R.id.slidemenu_item_home_ll;
	// private Map<Integer, View> slidemenuItems = new HashMap<Integer, View>();

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
		TitleUtil.show(this, mainIndex);
		initSlideMenu();
		initMainTab();
		// setListener();
	}

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
				R.drawable.slidemenu_ticket, R.drawable.slidemenu_setup };
		int[] names = { R.string.slidemenu_finance, R.string.slidemenu_preview,
				R.string.slidemenu_recharge, R.string.slidemenu_friends,
				R.string.slidemenu_transfer, R.string.slidemenu_ticket,
				R.string.slidemenu_setup };

		slv.setAdapter(new SlideMenuAdapter(this, icons, names));

		slv.setOnItemClickListener(this);

		//
		//
		// mSlideMenuItemHome = (LinearLayout)
		// findViewById(R.id.slidemenu_item_home_ll);
		// mSlideMenuItemFinance = (LinearLayout)
		// findViewById(R.id.slidemenu_item_finac_ll);
		// mSlideMenuItemPreview = (LinearLayout)
		// findViewById(R.id.slidemenu_item_preview_ll);
		// mSlideMenuItemRecharge = (LinearLayout)
		// findViewById(R.id.slidemenu_item_recharge_ll);
		// mSlideMenuItemFriend = (LinearLayout)
		// findViewById(R.id.slidemenu_item_friends_ll);
		// mSlideMenuItemTransfer = (LinearLayout)
		// findViewById(R.id.slidemenu_item_transfer_ll);
		// mSlideMenuItemTicket = (LinearLayout)
		// findViewById(R.id.slidemenu_item_ticket_ll);
		// mSlideMenuItemSetup = (LinearLayout)
		// findViewById(R.id.slidemenu_item_setup_ll);

		// slidemenuItems.put(R.id.slidemenu_item_home_ll, mSlideMenuItemHome);
		// slidemenuItems.put(R.id.slidemenu_item_finac_ll,
		// mSlideMenuItemFinance);
		// slidemenuItems.put(R.id.slidemenu_item_preview_ll,
		// mSlideMenuItemPreview);
		// slidemenuItems.put(R.id.slidemenu_item_recharge_ll,
		// mSlideMenuItemRecharge);
		// slidemenuItems.put(R.id.slidemenu_item_friends_ll,
		// mSlideMenuItemFriend);
		// slidemenuItems.put(R.id.slidemenu_item_transfer_ll,
		// mSlideMenuItemTransfer);
		// slidemenuItems.put(R.id.slidemenu_item_ticket_ll,
		// mSlideMenuItemTicket);
		// slidemenuItems.put(R.id.slidemenu_item_setup_ll,
		// mSlideMenuItemSetup);

		// ��ʼѡ�еĲ˵�
		// slidemenuItems.get(slidemenuCurSel).setBackgroundResource(R.drawable.slidemenu_item_bg_pre_sel);
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

		
		
		
		
		
		// �����ʱ��Ĭ����ʾ����
		fm.beginTransaction().replace(R.id.aaa, mFragmentHome).commit();

		mTabRG = (RadioGroup) findViewById(R.id.main_tab_root_rg);

		//
		// mTabHome = (RadioButton) findViewById(R.id.main_tab_home_rb);
		// mTabFinance = (RadioButton) findViewById(R.id.main_tab_finance_rb);
		// mTabPreview = (RadioButton) findViewById(R.id.main_tab_preview_rb);
		// mTabSetup = (RadioButton) findViewById(R.id.main_tab_setup_rb);

	}

	private void setListener() {
		// mSlideMenuItemHome.setOnClickListener(this);
		// mSlideMenuItemFinance.setOnClickListener(this);
		// mSlideMenuItemPreview.setOnClickListener(this);
		// mSlideMenuItemRecharge.setOnClickListener(this);
		// mSlideMenuItemFriend.setOnClickListener(this);
		// mSlideMenuItemTransfer.setOnClickListener(this);
		// mSlideMenuItemTicket.setOnClickListener(this);
		// mSlideMenuItemSetup.setOnClickListener(this);
		// mTabHome.setOnClickListener(this);
		// mTabFinance.setOnClickListener(this);
		// mTabPreview.setOnClickListener(this);
		// mTabSetup.setOnClickListener(this);
	}

	/**
	 * �����ж�fragement ����ʾ�������صȵ�
	 */
	public void fragmentToggle(FragmentTransaction ft, Fragment fragment,int fragmentIndex) {
		if(fragment!=null){
 			ft.replace(R.id.aaa, fragment).commit();
//			if (fragment.isHidden()) {
//				MyToast.show(this, "show");
//				ft.show(fragment);
//			}else{
//				MyToast.show(this, "hide");
//				ft.hide(fragment);
//			}
//		}else{
//			
		}
		         
//		   switch (fragmentIndex) {
//		case 0:
		//	 ft.replace(R.id.aaa, fragment).commit();
//			break;
//
//		case 1:
//			 ft.replace(R.id.bbb, fragment).commit();
//			break;
//		case 2:
//			 ft.replace(R.id.ccc, fragment).commit();
//			break;
//		}	 
//         
			  
			
			
//		} 
			
			
//			switch (fragmentIndex) {
////			mFragmentHome = new TabHomeFragment();
////			mFragmentFinance = new TabFinanceFragment();
////			mFragmentPreView = new TabPreViewFragmet();
////			mFragmentRecharege = new NavRechargeFragment();
////			mFragmentFriends = new NavRecommendFriendsFragment();
////			mFragmentTransfer = new NavTransferAccountFragment();
////			mFragmentSetup = new TabSetUpFragment();
//			
////			
////			 
////			case 0:
////				mFragmentHome = new TabHomeFragment();
// 				ft.replace(R.id.aaa, fragment);
////				break;
////
////			case 1:
////				mFragmentFinance = new TabFinanceFragment();
////				ft.replace(R.id.aaa, mFragmentFinance);
////				break;
////			case 2:
////				mFragmentPreView = new TabPreViewFragmet();
////				ft.replace(R.id.aaa, mFragmentPreView);
////				break;
//				
//			}
//		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// �����Ϊ�˼����໬�˵��Ǵ򿪻��ǹرա��Զ��ж��Ǵ��ǻ��ǹر�
		toggle();

		if (slidemenuItemRoot != null) {
			if (position != 0) {// ֻҪ���ǵ���ĵ�һ����Ŀ����ô�����õ�һ����Ŀ�ı���
				slidemenuItemRoot.setBackgroundColor(Color.rgb(51, 51, 49));
			} else {// ����ǵ�һ����Ŀ
				slidemenuItemRoot.setBackgroundColor(R.color.title_bar_bg);
			}
		}
		boolean isTab = false;
		FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
		case 0: {// ��ҳ
			isTab = true;
			//ft.replace(R.id.aaa,new TabHomeFragment()).commit();
			 fragmentToggle(ft, mFragmentHome,position);
			break;
		}
		case 1: {// ����
			isTab = true;
		//	ft.replace(R.id.aaa,new TabFinanceFragment()).commit();
		 fragmentToggle(ft, mFragmentFinance,position);
			break;
		}
		case 2: {// Ԥ��
			isTab = true;
			//ft.replace(R.id.aaa,new TabPreViewFragmet()).commit();
			fragmentToggle(ft, mFragmentPreView,position);
			break;
		}

		case 3:// �˺ų�ֵ
			fragmentToggle(ft, mFragmentRecharege,position);
			break;
		case 4:// �Ƽ�����
			fragmentToggle(ft, mFragmentFriends,position);

			break;
		case 5:// ���ת��
			fragmentToggle(ft, mFragmentTransfer,position);
			break;
		case 6:// �Ż�ȯ
			break;
		case 7: {// ����
			isTab = true;
			fragmentToggle(ft, mFragmentSetup,position);

			break;
		}

		}
	 
		if (!isTab) {// �������tab,����tabbar
			mTabRG.setVisibility(View.GONE);
		} else {// �����tab����ʾ
			mTabRG.setVisibility(View.VISIBLE);
		}

		// ���title������Ҫ�仯
		TitleUtil.show(this, position);

	}

	public void onClick(View v) {
		// // ����ǲ��ǵ����tab�������tab�Ͳ���ִ�� toggle
		// boolean isTab = false;
		// int id = v.getId();
		//
		//
		// switch (id) {
		// // tab+slidemenu ��ҳ (���۵��tab����menu�����߶�Ҫͬʱ�仯)
		// case R.id.main_tab_home_rb:
		// isTab = true;
		// case R.id.slidemenu_item_home_ll:
		// mainIndex = Constant.MAIN_INDEX_HOME;
		// fm.beginTransaction().replace(R.id.ccc, new NavHomeFragment())
		// .commit();
		// break;
		//
		// // tab+slidemenu ����
		// case R.id.main_tab_finance_rb:
		// isTab = true;
		// case R.id.slidemenu_item_finac_ll:
		// mainIndex = Constant.MAIN_INDEX_FINANCE;
		// fm.beginTransaction().replace(R.id.ccc, new NavFinanceFragment())
		// .commit();
		// break;
		//
		// // tab+slidemenu Ԥ��
		// case R.id.main_tab_preview_rb:
		// isTab = true;
		// case R.id.slidemenu_item_preview_ll:
		// mainIndex = Constant.MAIN_INDEX_PREVIEW;
		// fm.beginTransaction().replace(R.id.ccc, new NavPreViewFragmet())
		// .commit();
		//
		// break;
		//
		// // tab+slidemenu ����
		// case R.id.main_tab_setup_rb:
		// isTab = true;
		// case R.id.slidemenu_item_setup_ll:
		// mainIndex = Constant.MAIN_INDEX_SETUP;
		// fm.beginTransaction().replace(R.id.ccc, new NavSetUpFragment())
		// .commit();
		//
		// break;
		// // �໬�˵��ĵ���¼�
		//
		// // �˺ų�ֵ
		// case R.id.slidemenu_item_recharge_ll: {
		// UIHelper.switcher(this, AccountRechargeActivity.class);
		// break;
		// }
		// // �Ƽ�����
		// case R.id.slidemenu_item_friends_ll: {
		// UIHelper.switcher(this, NavRecommendFriendsActivity.class);
		// break;
		// }
		// // ���ת��
		// case R.id.slidemenu_item_transfer_ll: {
		// UIHelper.switcher(this, TransferAccountActivity.class);
		// break;
		// }
		// // �Ż�ȯ
		// case R.id.slidemenu_item_ticket_ll: {
		// break;
		// }
		//
		// }
		//
		//
		// if(!isTab&&id!=slidemenuCurSel){//�����ǰѡ�е���Ŀ�͵�ǰ�������Ŀ��һ���ġ��Ͳ����������ˡ�
		// MyToast.show(this, "aaaa");
		// //��ԭ����item������ԭ
		// View item = slidemenuItems.get(slidemenuCurSel);
		// if(item!=null){
		// item.setBackgroundResource(R.drawable.slidemenu_item_bg_nor);
		// //�µ����Ŀ�ı䱳��
		// slidemenuItems.get(id).setBackgroundResource(R.drawable.slidemenu_item_bg_pre_sel);
		// slidemenuCurSel = id;
		// }
		//
		// }
		//
		//
		//
		//
		// TitleUtil.show(this, mainIndex);
		// // �ǲ໬�˵���ִ��
		// if (!isTab) {
		// toggle();
		// }

	}
}
