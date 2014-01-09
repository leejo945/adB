package cn.com.paioo.app.util;

import java.util.HashMap;
import java.util.Map;

import com.slidingmenu.lib.app.SlidingFragmentActivity;

 

import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.AboutUs;
import cn.com.paioo.app.ui.ExtraCompanyInfoActivity;
import cn.com.paioo.app.ui.ForgetActivity;
import cn.com.paioo.app.ui.MainActivity;
import cn.com.paioo.app.ui.ModifyContactWayActivity;
import cn.com.paioo.app.ui.ModifyPassword;
import cn.com.paioo.app.ui.PresenterIncomeActivity;
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

public class TitleManager {
 

	public static final int BACK = 0;
	public static final int SHARE = 1;

	private static int recordRes;

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

			}
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
		// initTitleView(activity);

		TextView titleName = (TextView) activity
				.findViewById(R.id.title_bar_title_tv);
		ImageButton slidemenu = (ImageButton) activity
				.findViewById(R.id.title_bar_slidemenu_ib);
		ImageButton logo = (ImageButton) activity
				.findViewById(R.id.title_bar_logo);
		ImageButton record = (ImageButton) activity
				.findViewById(R.id.title_bar_record_ib);

		slidemenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 打开侧滑
				if (activity instanceof SlidingFragmentActivity) {
					((SlidingFragmentActivity) activity).toggle();
				}
			}
		});
		record.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 充值记录,广告消费记录 转账记录 都是同一个界面不同的数据

				if (recordRes != R.string.title_bar_title_referee_money) {
					Map<String, Object> extras = new HashMap<String, Object>();
					extras.put("title", recordRes);
					UIHelper.switcher(activity,
							ThreeInOneRecordActivity.class, extras);
				} else {
					UIHelper.switcher(activity,
							PresenterIncomeActivity.class, null);
				}

			}
		});
		if (MainActivity.class.getSimpleName().equals(cur)) {
			slidemenu.setVisibility(View.VISIBLE);
			if (mainIndex >= 0) {
				logo.setVisibility(View.GONE);
				record.setVisibility(View.GONE);
				switch (mainIndex) {
				case 0:// 首页
					logo.setVisibility(View.VISIBLE);
					titleName.setText("");
					break;
				case 1:// 财务 ()
						// 财务对应广告消费记录
					recordRes = R.string.title_bar_title_adcosume;
					record.setVisibility(View.VISIBLE);
					titleName.setText(R.string.title_bar_title_finance);
					break;
				case 2:// 预览
					titleName.setText(R.string.title_bar_title_preview);
					break;
				case 3:// 账号充值
					recordRes = R.string.title_bar_title_recharge_rocord;
					titleName.setText(R.string.slidemenu_recharge);
					record.setVisibility(View.VISIBLE);
					break;
				case 4:// 推荐好友
					recordRes = R.string.title_bar_title_referee_money;
					titleName.setText(R.string.slidemenu_friends);
					record.setVisibility(View.VISIBLE);
					break;
				case 5:// 余额转账
					recordRes = R.string.title_bar_title_transfer;
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
