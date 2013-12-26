package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.UIHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabFinanceFragment extends BaseFragment {
    private TextView mFreeze,mNowTotal,mAllTotal,mAward,mTransfer,mComsume;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("paioo", "TabFinanceFragment   财务，，，，，创建");
		return inflater.inflate(R.layout.nav_finance, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mFreeze = (TextView) fa.findViewById(R.id.nav_finance_freeze_total_tv);
		mNowTotal = (TextView) fa.findViewById(R.id.nav_finance_now_total_tv);
		mAllTotal = (TextView) fa.findViewById(R.id.nav_finance_all_total_tv);
		mAward = (TextView) fa.findViewById(R.id.nav_finance_recommend_award_tv);
		mTransfer = (TextView) fa.findViewById(R.id.nav_finance_balance_transfer_tv);
		mComsume = (TextView) fa.findViewById(R.id.nav_finance_ad_comsume_tv);
		 
		mFreeze.setText("冻结金额120元");
		mNowTotal.setText("2350.33");
		mAllTotal.setText("￥1200.00");
		mAward.setText("￥231.00");
		mTransfer.setText("￥4512.1");
		mComsume.setText("￥4512");
		
		Log.e("paioo", "TabFinanceFragment   财务，，，，onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

 

	@Override
	public void onDestroy() {
		Log.e("paioo", "TabFinanceFragment   财务，，，，，销毁");
		super.onDestroy();
	}

}
