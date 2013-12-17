package cn.com.paioo.app.util;

import java.util.HashMap;
import java.util.Map;

import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.AboutUs;
import cn.com.paioo.app.ui.ExtraCompanyInfoActivity;
import cn.com.paioo.app.ui.ForgetActivity;
import cn.com.paioo.app.ui.MainActivity;
import cn.com.paioo.app.ui.ModifyContactWayActivity;
import cn.com.paioo.app.ui.ModifyPassword;
import cn.com.paioo.app.ui.PreviewDetailedActivity;
import cn.com.paioo.app.ui.ThreeInOneRecordActivity;
import cn.com.paioo.app.ui.RegisterActivity;
import cn.com.paioo.app.ui.SuggestActivity;
import android.app.Activity;
import android.content.Intent;
import android.provider.Contacts.Intents.UI;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleUtil {
	private static TextView titleName;
	private static ImageButton back;
	private static ImageButton slidemenu;
	private static ImageButton record;
	private static ImageButton share;
	private static ImageButton logo;

	public static final int BACK = 0;
	public static final int SHARE = 1;

	
	private static  int recordRes;
	
	/**
	 * 非fragment界面的title设置
	 * 
	 * @param activity
	 */
	public static void show(final Activity activity, int[] flags, int title) {
		if (title != 0) {
			TextView tv = (TextView) activity
					.findViewById(R.id.title_bar_title_tv);
			tv.setText(title);
		}
		for (int flag : flags) {
			switch (flag) {
			case BACK: {
				ImageButton iv = (ImageButton) activity
						.findViewById(R.id.title_bar_back_ib);
				iv.setVisibility(View.VISIBLE);
				iv.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						activity.finish();
					}
				});
				break;
			}
			case SHARE: {
				ImageButton iv = (ImageButton) activity
						.findViewById(R.id.title_bar_share_ib);

				iv.setVisibility(View.VISIBLE);

				break;
			}

			// case HOME: {
			// ImageView iv = (ImageView) activity
			// .findViewById(R.id.title_right_home_iv);
			//
			// iv.setVisibility(View.VISIBLE);
			// iv.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// Intent i = new Intent(activity, MainMenuActivity.class);
			// i.putExtra("tag", 0);
			// activity.startActivity(i);
			// activity.finish();
			// }
			// });
			// break;
			// }
			// case BACK_ARROW: {
			// ImageView iv = (ImageView) activity
			// .findViewById(R.id.title_left_back_arrow_iv);
			// iv.setVisibility(View.VISIBLE);
			// iv.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// activity.finish();
			// }
			// });
			// break;
			// }

			}
		}

	}

	private static void initTitleView(final Activity activity) {
		if (titleName == null) {
			titleName = (TextView) activity
					.findViewById(R.id.title_bar_title_tv);

		}

		if (back == null) {
			back = (ImageButton) activity.findViewById(R.id.title_bar_back_ib);
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					activity.finish();
				}
			});
		}

		if (slidemenu == null) {
			slidemenu = (ImageButton) activity
					.findViewById(R.id.title_bar_slidemenu_ib);

			slidemenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 打开侧滑
					if (activity instanceof SlidingFragmentActivity) {
						((SlidingFragmentActivity) activity).toggle();
					}
				}
			});

		}

		if (record == null) {
			record = (ImageButton) activity
					.findViewById(R.id.title_bar_recharge_record_ib);

			record.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 充值记录,广告消费记录 转账记录 都是同一个界面不同的数据
					Map<String,Object> extras = new HashMap<String, Object>();
					extras.put("title",recordRes);
					UIHelper.switcher(activity, ThreeInOneRecordActivity.class,extras);

				}
			});
		}
		if (logo == null) {

			logo = (ImageButton) activity.findViewById(R.id.title_bar_logo);
		}
		if (share == null) {
			share = (ImageButton) activity
					.findViewById(R.id.title_bar_share_ib);
			share.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MyToast.show(activity, "分享");
				}
			});
		}
	}

	/**
	 * title显示的工具类
	 * 
	 * @param activity
	 * @param mainIndex
	 *            为了在MainActivity界面中。tab切换的时候，title也变化
	 */
	public static void show(final Activity activity, int mainIndex) {
		String temp = activity.getLocalClassName();
		String cur = temp.substring(temp.lastIndexOf(".") + 1);
		initTitleView(activity);

		if (RegisterActivity.class.getSimpleName().equals(cur)) {// 注册界面
			titleName.setText(R.string.free_register);
			back.setVisibility(View.VISIBLE);
		}
		// 补充公司信息
		if (ExtraCompanyInfoActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.eci_company_info_title);
			back.setVisibility(View.VISIBLE);
		}
		// 忘记密码
		if (ForgetActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.find_pwd);
			back.setVisibility(View.VISIBLE);
		}
		 
		if (MainActivity.class.getSimpleName().equals(cur)) {
			slidemenu.setVisibility(View.VISIBLE);
			if (mainIndex >= 0) {
				logo.setVisibility(View.GONE);
				record.setVisibility(View.GONE);
				share.setVisibility(View.GONE);
				switch (mainIndex) {
				case 0:// 首页
					logo.setVisibility(View.VISIBLE);
					titleName.setText("");
					break;
				case 1:// 财务 ()
					//财务对应广告消费记录
					recordRes  = R.string.title_bar_title_adcosume;
					record.setVisibility(View.VISIBLE);
					titleName.setText(R.string.title_bar_title_finance);
					break;
				case 2:// 预览
					titleName.setText(R.string.title_bar_title_preview);
					break;
				case 3:// 账号充值
					recordRes  = R.string.title_bar_title_recharge_rocord;
					titleName.setText(R.string.slidemenu_recharge);
					record.setVisibility(View.VISIBLE);
					break;
				case 4:// 推荐好友
					titleName.setText(R.string.slidemenu_friends);
					share.setVisibility(View.VISIBLE);
					break;
				case 5:// 余额转账
					recordRes  = R.string.title_bar_title_transfer;
					titleName.setText(R.string.slidemenu_transfer);
					record.setVisibility(View.VISIBLE);
					break;
				case 6:// 设置
					titleName.setText(R.string.title_bar_title_setup);
					break;
				}
			}

		}

	}
}
