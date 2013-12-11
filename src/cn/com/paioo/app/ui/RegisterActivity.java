package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends BaseActivity {
	private EditText mAccount, mPwd, mSurePwd, mContacts, mCellPhone, mMail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.free_register);
		super.onCreate(savedInstanceState);

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
			String account = StringUtils.getStringByET(mAccount);
			String pwd = StringUtils.getStringByET(mPwd);
			String surePwd = StringUtils.getStringByET(mSurePwd);
			String contacts = StringUtils.getStringByET(mContacts);
			String cellPhone = StringUtils.getStringByET(mCellPhone);
			String mail = StringUtils.getStringByET(mMail);

			// //账号
			if (StringUtils.isEmpty(account)) {
				MyToast.show(this, R.string.warn_toast_account_isempty);
				return;
			}
			// 密码
			if (StringUtils.isEmpty(pwd)) {
				MyToast.show(this, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringUtils.isStandardPwd(pwd)) {
				MyToast.show(this, R.string.warn_toast_pwd_unstandard);
				return;
			}
			
			
			if (!StringUtils.isSamePwd(pwd, surePwd)) {
				MyToast.show(this, R.string.warn_toast_twice_pwd_difference);
				return;
			}
			// 联系人判空
			if (StringUtils.isEmpty(contacts)) {
				MyToast.show(this, R.string.warn_toast_contacts_isempty);
				return;
			}
			// 手机号码判空
			if (StringUtils.isEmpty(cellPhone)) {
				MyToast.show(this, R.string.warn_toast_cellphone_isempty);
				return;
			}
			// 手机号码格式不对 不到11 位
			if (!StringUtils.isStandardCellphoneNumber(cellPhone)) {
				MyToast.show(this, R.string.warn_toast_cellphone_unstandard);
				return;
			}

			// 邮箱
			if (StringUtils.isEmpty(mail)) {
				MyToast.show(this, R.string.warn_toast_mail_isempty);
				return;
			}
			if (!StringUtils.isEmail(mail)) {
				MyToast.show(this, R.string.warn_toast_mail_unstandard);
				return;
			}
			// 提交.......

			UIHelper.switcher(this, ExtraCompanyInfoActivity.class);

			break;

		}
	}

}
