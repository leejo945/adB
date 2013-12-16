 
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

public class TabFinanceFragment extends BaseFragment implements OnClickListener {
     private LinearLayout mAD;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 Log.e("paioo", "TabFinanceFragment   财务，，，，，创建");
		return inflater.inflate(R.layout.nav_finance, container, false);
	}
	 
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		 
	//	mAD  = (LinearLayout) fa.findViewById(R.id.finance_adconsume_ll);
		
	 
		
		
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.finance_adconsume_ll:
			 
			break;

		 
		}
	}
	
	@Override
	public void onDestroy() {
		 Log.e("paioo", "TabFinanceFragment   财务，，，，，销毁");
		super.onDestroy();
	}
	
	
}
