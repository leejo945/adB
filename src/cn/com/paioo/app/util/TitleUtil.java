package cn.com.paioo.app.util;

import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.ADConsumeRecordActivity;
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
import android.provider.Contacts.Intents.UI;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TitleUtil {
	private static TextView titleName;
	private static ImageButton back;
	private static ImageButton slidemenu;
	private static ImageButton record;
	private static ImageButton share;
	private static ImageButton logo;

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
					UIHelper.switcher(activity, ThreeInOneRecordActivity.class);

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
		// 修改联系方式
		if (ModifyContactWayActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.modify_contact_way);
			back.setVisibility(View.VISIBLE);
		}
		// 修改密码

		if (ModifyPassword.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.modify_password);
			back.setVisibility(View.VISIBLE);
		}
		// 意见反馈
		if (SuggestActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.suggest);
			back.setVisibility(View.VISIBLE);
		}
		// 关于我们
		if (AboutUs.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.about_us);
			back.setVisibility(View.VISIBLE);
		}
		// 预览详情
		if (PreviewDetailedActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.title_bar_title_preview_detailed);
			share.setVisibility(View.VISIBLE);
		}

		// 广告消费记录
		if (ADConsumeRecordActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.title_bar_title_adcosume);
			back.setVisibility(View.VISIBLE);
		}
		if (ThreeInOneRecordActivity.class.getSimpleName().equals(cur)) {
			titleName.setText(R.string.title_bar_title_recharge_rocord);
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
					record.setVisibility(View.VISIBLE);
					titleName.setText(R.string.title_bar_title_finance);
					break;
				case 2:// 预览
					titleName.setText(R.string.title_bar_title_preview);
					break;
				case 3:// 账号充值
					titleName.setText(R.string.slidemenu_recharge);
					record.setVisibility(View.VISIBLE);
					break;
				case 4:// 推荐好友
					titleName.setText(R.string.slidemenu_friends);
					share.setVisibility(View.VISIBLE);
					break;
				case 5:// 余额转账
					titleName.setText(R.string.slidemenu_transfer);
					record.setVisibility(View.VISIBLE);
					break;
				case 6:// 优惠券
					break;
				case 7:// 设置
					titleName.setText(R.string.title_bar_title_setup);
					break;
				}
			}

		}

	}
}
