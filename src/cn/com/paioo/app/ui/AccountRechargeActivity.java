package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccountRechargeActivity extends BaseActivity {
	private TextView mNewMoney;
	private EditText mCardid,mPwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.account_recharge);
		super.onCreate(savedInstanceState);
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
