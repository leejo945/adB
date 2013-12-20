package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NavTransferAccountFragment extends BaseFragment {
    private FragmentActivity fa;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("paioo", "NavTransferAccountFragment   转账，，，，，创建");
		return inflater.inflate(R.layout.transfer_accounts, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		 fa = getActivity();
		super.onActivityCreated(savedInstanceState);
	}
	
	
 
	
	 @Override
		public void onDestroy() {
		  
		 Log.e("paioo", "NavTransferAccountFragment   转账，，，，，销毁");
			
			// TODO Auto-generated method stub
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
