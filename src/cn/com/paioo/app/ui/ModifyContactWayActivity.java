package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.TitleUtil;
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
		TitleUtil.show(this, new int[]{TitleUtil.BACK}, R.string.modify_contact_way);
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
			String contacts = StringUtils.getStringByET(mContacts);
			String cellPhone = StringUtils.getStringByET(mCellPhone);
			String telePhone = StringUtils.getStringByET(mTelePhone);
			String mail = StringUtils.getStringByET(mMail);
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
			// 手机号码格式不对
			if (!StringUtils.isStandardCellphoneNumber(cellPhone)) {
				MyToast.show(this, R.string.warn_toast_cellphone_unstandard);
				return;
			}
			// 固话判空
			if (StringUtils.isEmpty(telePhone)) {
				MyToast.show(this, R.string.warn_toast_telephone_isempty);
				return;
			}

			if (!StringUtils.isStandardTelephoneNumber(telePhone)) {
				MyToast.show(this, R.string.warn_toast_telephone_unstandard);
				return;
			}
			if (StringUtils.isEmpty(mail)) {
				MyToast.show(this, R.string.warn_toast_mail_isempty);
				return;
			}
			if (!StringUtils.isEmail(mail)) {
				MyToast.show(this, R.string.warn_toast_mail_unstandard);
				return;
			}
			// //提交.......

			MyToast.show(this, "修改成功到主页");

			break;
		}
		}
	}
}
