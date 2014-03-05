package cn.com.paioo.app.ui;

import java.util.Date;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.UIManager;
import cn.com.paioo.app.view.PullToRefreshView;
import cn.com.paioo.app.view.PullToRefreshView.OnContainerRefreshListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import cn.com.paioo.app.util.LogManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabFinanceFragment extends BaseFragment implements
		OnContainerRefreshListener {
	private TextView mFreeze, mNowTotal, mAllTotal, mAward, mTransfer,
			mComsume;
	private PullToRefreshView mPullRefresh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LogManager.e("paioo", "TabFinanceFragment   财务，，，，，创建");
		return inflater.inflate(R.layout.layout_nav_finance, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		LogManager.e("paioo", "TabFinanceFragment   财务，，，，onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void findViews() {
		// TODO Auto-generated method stub
		mPullRefresh = (PullToRefreshView) fa
				.findViewById(R.id.nav_finance_pullrefresh);
		mFreeze = (TextView) fa.findViewById(R.id.nav_finance_freeze_total_tv);
		mNowTotal = (TextView) fa.findViewById(R.id.nav_finance_now_total_tv);
		mAllTotal = (TextView) fa.findViewById(R.id.nav_finance_all_total_tv);
		mAward = (TextView) fa
				.findViewById(R.id.nav_finance_recommend_award_tv);
		mTransfer = (TextView) fa
				.findViewById(R.id.nav_finance_balance_transfer_tv);
		mComsume = (TextView) fa.findViewById(R.id.nav_finance_ad_comsume_tv);
		super.findViews();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

		mFreeze.setText("冻结金额120元");
		mNowTotal.setText("2350.33");
		mAllTotal.setText("￥1200.00");
		mAward.setText("￥231.00");
		mTransfer.setText("￥4512.1");
		mComsume.setText("￥4512");
		super.init();
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		mPullRefresh.setOnRefreshListener(this);
		super.setListener();
	}

	@Override
	public void onDestroy() {
		LogManager.e("paioo", "TabFinanceFragment   财务，，，，，销毁");
		super.onDestroy();
	}

	@Override
	public void onContainerRefresh() {
		// TODO Auto-generated method stub
		mPullRefresh.onComplete(new Date().toLocaleString());
	}

}
