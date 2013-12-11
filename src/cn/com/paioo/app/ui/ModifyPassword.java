package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.UIHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifyPassword extends BaseActivity {
	private EditText mOldPwd, mNewPwd, mNewPwd2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.modify_password);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		mOldPwd = (EditText) findViewById(R.id.modify_password_old_pwd_et);
		mNewPwd = (EditText) findViewById(R.id.modify_password_new_pwd_et);
		mNewPwd2 = (EditText) findViewById(R.id.modify_password_sure_pwd_et);

		super.init();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.modify_password_submit_bt:
			String oldPwd = StringUtils.getStringByET(mOldPwd);
			String newPwd = StringUtils.getStringByET(mNewPwd);
			String newPwd2 = StringUtils.getStringByET(mNewPwd2);
			//现在密码判空
			if (StringUtils.isEmpty(oldPwd)) {
				MyToast.show(this, R.string.warn_toast_old_pwd_isempty);
				return;
			} 
			if(!StringUtils.isStandardPwd(oldPwd)){
				MyToast.show(this, R.string.warn_toast_old_pwd_unstandard);
				return;
			}
			//新密码
			if (StringUtils.isEmpty(newPwd)) {
				MyToast.show(this, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringUtils.isStandardPwd(newPwd)) {
				MyToast.show(this, R.string.warn_toast_pwd_unstandard);
				return;
			}
			//确认二者密码一致
			
			if (!StringUtils.isSamePwd(newPwd, newPwd2)) {
				MyToast.show(this, R.string.warn_toast_twice_pwd_difference);
				return;
			}
			
		 
			
			UIHelper.switcher(this, LoginActivity.class);
			break;
		}
	}
}
