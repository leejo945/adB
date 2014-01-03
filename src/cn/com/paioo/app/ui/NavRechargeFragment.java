package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.UIHelper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NavRechargeFragment extends BaseFragment implements
		OnClickListener {
	private TextView mNewMoney;
	private EditText mCardid, mPwd;
	 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("paioo", "NavRechargeFragment   充值界面，，，，，，创建");
		return inflater.inflate(R.layout.account_recharge, container, false);

	}
 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		fa.findViewById(R.id.recharge_barcode_iv).setOnClickListener(this);
		fa.findViewById(R.id.recharge_recharge_bt).setOnClickListener(this);
		mCardid = (EditText) fa.findViewById(R.id.recharge_cardid_et);
		mPwd = (EditText) fa.findViewById(R.id.recharge_pwd_et);
		mNewMoney = (TextView) fa.findViewById(R.id.recharege_now_money_tv);
		mNewMoney.setText("2350");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		Log.e("paioo", "NavRechargeFragment   充值界面，，，，，，销毁");

		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.recharge_barcode_iv:
			// 二维码界面
			// UIHelper.switcher(fa,ZxingActivity.class);
			Intent i = new Intent(fa, ZxingActivity.class);
			startActivityForResult(i, 1);
			break;

		case R.id.recharge_recharge_bt:
			String cardid = StringManager.getStringByET(mCardid);
			String pwd = StringManager.getStringByET(mPwd);
			if (StringManager.isEmpty(cardid)) {
				MyToast.show(fa, R.string.warn_toast_cardid_isempty);
				return;
			}
			if (!StringManager.isStandradCardid(cardid)) {
				MyToast.show(fa, R.string.warn_toast_cardid_unstandard);
				return;
			}
			if (StringManager.isEmpty(pwd)) {
				MyToast.show(fa, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringManager.isStandardPwd(pwd)) {
				MyToast.show(fa, R.string.warn_toast_pwd_unstandard);
				return;
			}

			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		mCardid.setText(data.getStringExtra("result"));

		super.onActivityResult(requestCode, resultCode, data);
	}

}
