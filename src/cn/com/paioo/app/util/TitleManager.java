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
	 * ��fragment�����title����
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
	 * title��ʾ�Ĺ�����
	 * 
	 * @param activity
	 * @param mainIndex
	 *            Ϊ����MainActivity�����С�tab�л���ʱ��titleҲ�仯
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
				// �򿪲໬
				if (activity instanceof SlidingFragmentActivity) {
					((SlidingFragmentActivity) activity).toggle();
				}
			}
		});
		record.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ֵ��¼,������Ѽ�¼ ת�˼�¼ ����ͬһ�����治ͬ������

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
				case 0:// ��ҳ
					logo.setVisibility(View.VISIBLE);
					titleName.setText("");
					break;
				case 1:// ���� ()
						// �����Ӧ������Ѽ�¼
					recordRes = R.string.title_bar_title_adcosume;
					record.setVisibility(View.VISIBLE);
					titleName.setText(R.string.title_bar_title_finance);
					break;
				case 2:// Ԥ��
					titleName.setText(R.string.title_bar_title_preview);
					break;
				case 3:// �˺ų�ֵ
					recordRes = R.string.title_bar_title_recharge_rocord;
					titleName.setText(R.string.slidemenu_recharge);
					record.setVisibility(View.VISIBLE);
					break;
				case 4:// �Ƽ�����
					recordRes = R.string.title_bar_title_referee_money;
					titleName.setText(R.string.slidemenu_friends);
					record.setVisibility(View.VISIBLE);
					break;
				case 5:// ���ת��
					recordRes = R.string.title_bar_title_transfer;
					titleName.setText(R.string.slidemenu_transfer);
					record.setVisibility(View.VISIBLE);
					break;
				case 6:// ����
					titleName.setText(R.string.title_bar_title_setup);
					break;
				}
			}

		}

	}
}
