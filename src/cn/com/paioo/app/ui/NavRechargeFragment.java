package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NavRechargeFragment extends BaseFragment {
	private TextView mNewMoney;
	private EditText mCardid,mPwd;
	private FragmentActivity fa;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    Log.e("paioo", "NavRechargeFragment   充值界面，，，，，，创建");
		return  inflater.inflate(R.layout.account_recharge, container, false);
		 
	}
	@Override
	public void onDestroy() {
	    Log.e("paioo", "NavRechargeFragment   充值界面，，，，，，销毁");
		 
		
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		fa = getActivity();
		super.onStart();
	}
 
//public void init() {
//	// TODO Auto-generated method stub
//	   mNewMoney = (TextView) findViewById(R.id.account_recharge_new_money_tv);
//	   mCardid = (EditText) findViewById(R.id.account_recharge_cardid_et);
//	   mPwd = (EditText) findViewById(R.id.account_recharge_pwd_et);
//	super.init();
//}
//	public void onClick(View v) {
//		switch (v.getId()) {
// 		case R.id.account_recharge_recharge_bt:{
// 			 String cardid = StringUtils.getStringByET(mCardid);
// 			 String pwd = StringUtils.getStringByET(mPwd);
// 			 if(StringUtils.isEmpty(cardid)){
// 				 MyToast.show(this, R.string.warn_toast_cardid_isempty);
// 				 return;
// 			 }
// 			 if(!StringUtils.isStandradCardid(cardid)){
// 				 MyToast.show(this, R.string.warn_toast_cardid_unstandard);
// 				 return;
// 			 }
// 			 if(StringUtils.isEmpty(pwd)){
// 				 MyToast.show(this, R.string.warn_toast_pwd_isempty);
// 				 return;
// 			 }
// 			 if(!StringUtils.isStandardPwd(pwd)){
// 				 MyToast.show(this, R.string.warn_toast_pwd_unstandard);
// 				 return;
// 			 }
// 			break;
//		}
//
//			
//
//		}
//	}
}
