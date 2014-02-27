package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.TitleManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifyContactWayActivity extends BaseActivity {
	private EditText mContacts, mCellPhone, mTelePhone, mMail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.modify_contact_way);
		super.onCreate(savedInstanceState);
		TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.modify_contact_way);
	}

	@Override
	public void init() {
		mContacts = (EditText) findViewById(R.id.modify_contact_way_contacts_et);
		mCellPhone = (EditText) findViewById(R.id.modify_contact_way_cellphone_et);
		mTelePhone = (EditText) findViewById(R.id.modify_contact_way_telephone_et);
		mMail = (EditText) findViewById(R.id.modify_contact_way_mail_et);
		super.init();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.modify_contact_way_submit_bt: {
			String contacts = StringManager.getStringByET(mContacts);
			String cellPhone = StringManager.getStringByET(mCellPhone);
			String telePhone = StringManager.getStringByET(mTelePhone);
			String mail = StringManager.getStringByET(mMail);
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
			// 手机号码格式不对
			if (!StringManager.isStandardCellphoneNumber(cellPhone)) {
				ToastManager.show(this, R.string.warn_toast_cellphone_unstandard);
				return;
			}
			// 固话判空
			if (StringManager.isEmpty(telePhone)) {
				ToastManager.show(this, R.string.warn_toast_telephone_isempty);
				return;
			}

			if (!StringManager.isStandardTelephoneNumber(telePhone)) {
				ToastManager.show(this, R.string.warn_toast_telephone_unstandard);
				return;
			}
			if (StringManager.isEmpty(mail)) {
				ToastManager.show(this, R.string.warn_toast_mail_isempty);
				return;
			}
			if (!StringManager.isEmail(mail)) {
				ToastManager.show(this, R.string.warn_toast_mail_unstandard);
				return;
			}
			// //提交.......

			ToastManager.show(this, "修改成功到主页");

			break;
		}
		}
	}
}
