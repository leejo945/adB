package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import cn.com.paioo.app.util.LogManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NavTransferAccountFragment extends BaseFragment {
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LogManager.e("paioo", "NavTransferAccountFragment   转账，，，，，创建");
		return inflater.inflate(R.layout.transfer_accounts, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		 
		super.onActivityCreated(savedInstanceState);
	}
	
	
 
	
	 @Override
		public void onDestroy() {
		  
		 LogManager.e("paioo", "NavTransferAccountFragment   转账，，，，，销毁");
		 
			super.onDestroy();
		} 
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.transfer_next_bt:

			break;

		default:
			break;
		}
	}
}
