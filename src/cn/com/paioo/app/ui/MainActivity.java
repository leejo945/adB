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
		  OnItemClickListener, OnCheckedChangeListener {
	String tag = "MainActivity";
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
	private RadioGroup mTabRG;
	private RadioButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	private FragmentManager fm;

	private static int mainIndex = Constant.SLIDEMENU_INDEX_HOME;
	// �໬�˵������������Ŀ
	private LinearLayout slidemenuItemRoot;
	// Ϊ�˸������Ŀ����һ��ѡ��Ч��
	private static View frontClickView;
	 
	 

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

		// �����ʱ��Ĭ����ʾ����
		fm.beginTransaction().replace(R.id.aaa, mFragmentHome).commit();

		mTabRG = (RadioGroup) findViewById(R.id.main_tab_root_rg);

		 
		 mTabHome = (RadioButton) findViewById(R.id.main_tab_home_rb);
		 mTabFinance = (RadioButton) findViewById(R.id.main_tab_finance_rb);
		 mTabPreview = (RadioButton) findViewById(R.id.main_tab_preview_rb);
		 mTabSetup = (RadioButton) findViewById(R.id.main_tab_setup_rb);
		 mTabRG.setOnCheckedChangeListener(this);
	}

 

	/**
	 * �����ж�fragement ����ʾ�������صȵ�
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
		// �����Ϊ�˼����໬�˵��Ǵ򿪻��ǹرա��Զ��ж��Ǵ��ǻ��ǹر�
		toggle();

		// Ϊ�˸������Ŀ����һ��ѡ��Ч��
		if (frontClickView != null) {
			frontClickView.setBackgroundColor(Color.rgb(51, 51, 49));
		}
		view.setBackgroundColor(R.color.title_bar_bg);
		frontClickView = view;

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
			mTabHome.setChecked(true);
			fragmentToggle(ft, mFragmentHome, position);
			break;
		}
		case 1: {// ����
			isTab = true;
			mTabFinance.setChecked(true);
			fragmentToggle(ft, mFragmentFinance, position);
			break;
		}
		case 2: {// Ԥ��
			isTab = true;
			mTabPreview.setChecked(true);
			fragmentToggle(ft, mFragmentPreView, position);
			break;
		}

		case 3: {// �˺ų�ֵ
			fragmentToggle(ft, mFragmentRecharege, position);
			break;
		}
		case 4: {// �Ƽ�����
			fragmentToggle(ft, mFragmentFriends, position);

			break;
		}
		case 5: {// ���ת��
			fragmentToggle(ft, mFragmentTransfer, position);
			break;
		}
		case 6: {// ����
			isTab = true;
			mTabSetup.setChecked(true);
			fragmentToggle(ft, mFragmentSetup, position);

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
