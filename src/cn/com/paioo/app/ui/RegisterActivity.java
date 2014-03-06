package cn.com.paioo.app.ui;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBackIml;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.util.UIManager;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends BaseActivity {
	private EditText mAccount, mPwd, mSurePwd, mContacts, mCellPhone, mMail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.layout_free_register);
		super.onCreate(savedInstanceState);
		TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.free_register);

	}

	@Override
	public void init() {
		mAccount = (EditText) findViewById(R.id.free_register_account_et);
		mPwd = (EditText) findViewById(R.id.free_register_pwd_et);
		mSurePwd = (EditText) findViewById(R.id.free_register_sure_pwd_et);
		mContacts = (EditText) findViewById(R.id.free_register_contacts_et);
		mCellPhone = (EditText) findViewById(R.id.free_register_cellphone_et);
		mMail = (EditText) findViewById(R.id.free_register_mail_et);
		super.init();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.free_register_submit_bt:
			String account = StringManager.getStringByET(mAccount);
			String pwd = StringManager.getStringByET(mPwd);
			String surePwd = StringManager.getStringByET(mSurePwd);
			String contacts = StringManager.getStringByET(mContacts);
			String cellPhone = StringManager.getStringByET(mCellPhone);
			String mail = StringManager.getStringByET(mMail);

			// //账号
			if (StringManager.isEmpty(account)) {
				ToastManager.show(this, R.string.warn_toast_account_isempty);
				return;
			}
			// 密码
			if (StringManager.isEmpty(pwd)) {
				ToastManager.show(this, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringManager.isStandardPwd(pwd)) {
				ToastManager.show(this, R.string.warn_toast_pwd_unstandard);
				return;
			}
			
			
			if (!StringManager.isSamePwd(pwd, surePwd)) {
				ToastManager.show(this, R.string.warn_toast_twice_pwd_difference);
				return;
			}
			// 联系人判空
			if (StringManager.isEmpty(contacts)) {
				ToastManager.show(this, R.string.warn_toast_contacts_isempty);
				return;
			}
			// 手机号码判空
			if (StringManager.isEmpty(cellPhone)) {
				ToastManager.show(this, R.string.warn_toast_cellphone_isempty);
				return;
			}
			// 手机号码格式不对 不到11 位
			if (!StringManager.isStandardCellphoneNumber(cellPhone)) {
				ToastManager.show(this, R.string.warn_toast_cellphone_unstandard);
				return;
			}

			// 邮箱
			if (StringManager.isEmpty(mail)) {
				ToastManager.show(this, R.string.warn_toast_mail_isempty);
				return;
			}
			if (!StringManager.isEmail(mail)) {
				ToastManager.show(this, R.string.warn_toast_mail_unstandard);
				return;
			}
			
			final Dialog dialog = UIManager.getLoadingDialog(this,
					R.string.warn_dialog_login);
			dialog.show();
			// 提交.......
//			DataService.login(ConstantManager.URL_LOGIN, this, new NetCallBackIml() {
//				  @Override
//				public void netCallBack(Object response) {
//					dialog.dismiss();
//					
//					UIManager.switcher(RegisterActivity.this, ExtraCompanyInfoActivity.class);
//					super.netCallBack(response);
//				}
//				  @Override
//				public void netErrorCallBack(Context context,String errorReason) {
//					dialog.dismiss();
//					super.netErrorCallBack(context,errorReason);
//				}
//			 
//			});
			

			break;

		}
	}

}
