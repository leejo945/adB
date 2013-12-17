package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.SlideMenuAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.AppUpdateInfo;

import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;
import cn.com.paioo.app.view.ScrollListView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	private Map<Integer, Fragment> frMap = new HashMap<Integer, Fragment>();
	// private Fragment mFragmentHome, mFragmentFinance, mFragmentPreView,
	// mFragmentRecharege, mFragmentFriends, mFragmentTransfer,
	// mFragmentSetup;

	// main����tab
	private RadioGroup mTabRG;
	private RadioButton mTabHome, mTabFinance, mTabPreview, mTabSetup;

	// ������ţ�����fragment�������б�
	private Map<Integer, View> mConts = new HashMap<Integer, View>();

	private FragmentManager fm;

	private static int mainIndex = Constant.SLIDEMENU_INDEX_HOME;
	// �໬�˵������������Ŀ
	private LinearLayout slidemenuItemRoot;
	// Ϊ�˸������Ŀ����һ��ѡ��Ч��
	private static View frontClickView;

	private static final int APPUPDATE = 0;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case APPUPDATE:
				AppUpdateInfo appInfo = (AppUpdateInfo) msg.obj;
				showUpdateDialog(appInfo);
				App.appUpdateInfo = appInfo;
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����������ĵ���ʾ���֣��������������Activity��һ���ġ�
		setContentView(R.layout.slidemenu_content);
		// ���ò໬�˵��Ĳ���
		setBehindContentView(R.layout.slidemenu_menu);
		// ��ʼ���໬�˵�

		init();

		// ����Ƿ�Ҫ����������

		if (App.appUpdateInfo == null) {
			updateApp();
		}

	}

	private void updateApp() {
		App.pool.addTask(new Thread() {
			@Override
			public void run() {
				AppUpdateInfo appInfo = DataService
						.getAppUpdateInfo(MainActivity.this);
				if (appInfo != null) {
					// Ҫ���� �����Ի�����û���ʾ
					Message msg = handler.obtainMessage(APPUPDATE, appInfo);
					handler.sendMessage(msg);
				}

			}
		});
	}

	private void showUpdateDialog(final AppUpdateInfo updateInfo) {
		// Dialog dialog = new Dialog(this, R.style.MyDialog);
		// View view = View.inflate(this, R.layout.updateapp_dialog, null);
		// dialog.setContentView(view);
		// dialog.show();

		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle(R.string.warn_dialog_update);
		builder.setMessage(updateInfo.description);
		builder.setPositiveButton(R.string.warn_dialog_sureupdate,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						// ���ȷ����������
						DataService.updateAPK(MainActivity.this,
								updateInfo.apkurl, handler);

					}
				});
		builder.setNegativeButton(R.string.warn_dialog_cancle,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
                        
					}
				});
		builder.create().show();

	}

	private void init() {
		TitleUtil.show(this, mainIndex);
		initSlideMenu();
		initMainTab();
	}

	/**
	 * �໬�˵��ĳ�ʼ�� ����ʽ�������ݣ������ȣ�
	 */

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
				R.string.slidemenu_transfer, R.string.slidemenu_setup };

		slv.setAdapter(new SlideMenuAdapter(this, icons, names));

		slv.setOnItemClickListener(this);

		// ���������ͼ
		mConts.put(R.id.content_home, findViewById(R.id.content_home));
		mConts.put(R.id.content_finance, findViewById(R.id.content_finance));
		mConts.put(R.id.content_preview, findViewById(R.id.content_preview));
		mConts.put(R.id.content_recharge, findViewById(R.id.content_recharge));
		mConts.put(R.id.content_friends, findViewById(R.id.content_friends));
		mConts.put(R.id.content_transfer, findViewById(R.id.content_transfer));
		mConts.put(R.id.content_setup, findViewById(R.id.content_setup));

	}

	private void initMainTab() {
		fm = getSupportFragmentManager();
		frMap.put(R.id.content_home, new TabHomeFragment());
		frMap.put(R.id.content_finance, new TabFinanceFragment());
		frMap.put(R.id.content_preview, new TabPreViewFragmet());
		frMap.put(R.id.content_recharge, new NavRechargeFragment());
		frMap.put(R.id.content_friends, new NavRecommendFriendsFragment());
		frMap.put(R.id.content_transfer, new NavTransferAccountFragment());
		frMap.put(R.id.content_setup, new TabSetUpFragment());

		// �����ʱ��Ĭ����ʾ����
		fm.beginTransaction()
				.replace(R.id.content_home, frMap.get(R.id.content_home))
				.commit();

		mTabRG = (RadioGroup) findViewById(R.id.main_tab_root_rg);

		mTabHome = (RadioButton) findViewById(R.id.main_tab_home_rb);
		mTabFinance = (RadioButton) findViewById(R.id.main_tab_finance_rb);
		mTabPreview = (RadioButton) findViewById(R.id.main_tab_preview_rb);
		mTabSetup = (RadioButton) findViewById(R.id.main_tab_setup_rb);
		mTabRG.setOnCheckedChangeListener(this);
	}

	private void hiddenView(int conId) {
		Set<Integer> keys = mConts.keySet();
		for (int key : keys) {
			if (key == conId) {
				mConts.get(key).setVisibility(View.VISIBLE);
			} else {
				mConts.get(key).setVisibility(View.GONE);
			}

		}

	}

	/**
	 * �����ж�fragement ����ʾ�������صȵ�
	 */
	public void fragmentToggle(FragmentTransaction ft, int conId) {
		hiddenView(conId);
		Fragment fragment = frMap.get(conId);
		if (fragment != null) {
			if (!fragment.isAdded()) {// �������fragment��add��
				ft.replace(conId, fragment).commit();
			} else {
				Log.e("paioo", "����Ŀ�Ѿ����");
			}
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
			fragmentToggle(ft, R.id.content_home);
			break;
		}
		case 1: {// ����
			isTab = true;
			mTabFinance.setChecked(true);
			fragmentToggle(ft, R.id.content_finance);
			break;
		}
		case 2: {// Ԥ��
			isTab = true;
			mTabPreview.setChecked(true);
			fragmentToggle(ft, R.id.content_preview);
			break;
		}

		case 3: {// �˺ų�ֵ
			fragmentToggle(ft, R.id.content_recharge);
			break;
		}
		case 4: {// �Ƽ�����
			fragmentToggle(ft, R.id.content_friends);

			break;
		}
		case 5: {// ���ת��
			fragmentToggle(ft, R.id.content_transfer);
			break;
		}
		case 6: {// ����
			isTab = true;
			mTabSetup.setChecked(true);
			fragmentToggle(ft, R.id.content_setup);

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
		int position = 0;
		switch (checkedId) {
		case R.id.main_tab_home_rb:
			position = 0;
			fragmentToggle(ft, R.id.content_home);
			break;
		case R.id.main_tab_finance_rb:
			position = 1;
			fragmentToggle(ft, R.id.content_finance);
			break;
		case R.id.main_tab_preview_rb:
			position = 2;
			fragmentToggle(ft, R.id.content_preview);
			break;
		case R.id.main_tab_setup_rb:
			position = 6;
			fragmentToggle(ft, R.id.content_setup);
			break;
		}
		TitleUtil.show(this, position);
	}

	private static long fristBack;
	private static final int BACKSPACTIEM = 5000;
	// @Override
	// public void onBackPressed() {
	// if(fristBack==0){
	// MyToast.show(this, R.string.warn_toast_again_click_exit);
	// fristBack = System.currentTimeMillis();
	// handler.sendEmptyMessageDelayed(0, BACKSPACTIEM);
	// }else{
	// Log.e(tag, System.currentTimeMillis()-fristBack+"");
	// if(System.currentTimeMillis()-fristBack<BACKSPACTIEM){
	// fristBack = 0;
	// }
	// }
	//
	//
	// }
	// private Handler handler = new Handler() {
	// public void handleMessage(android.os.Message msg) {
	// fristBack = 0;
	// };
	// };

}
