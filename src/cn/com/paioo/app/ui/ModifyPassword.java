package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.util.UIManager;
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
		TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.modify_password);
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
			String oldPwd = StringManager.getStringByET(mOldPwd);
			String newPwd = StringManager.getStringByET(mNewPwd);
			String newPwd2 = StringManager.getStringByET(mNewPwd2);
			//现在密码判空
			if (StringManager.isEmpty(oldPwd)) {
				ToastManager.show(this, R.string.warn_toast_old_pwd_isempty);
				return;
			} 
			if(!StringManager.isStandardPwd(oldPwd)){
				ToastManager.show(this, R.string.warn_toast_old_pwd_unstandard);
				return;
			}
			//新密码
			if (StringManager.isEmpty(newPwd)) {
				ToastManager.show(this, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringManager.isStandardPwd(newPwd)) {
				ToastManager.show(this, R.string.warn_toast_pwd_unstandard);
				return;
			}
			//确认二者密码一致
			
			if (!StringManager.isSamePwd(newPwd, newPwd2)) {
				ToastManager.show(this, R.string.warn_toast_twice_pwd_difference);
				return;
			}
			
		 
			
			UIManager.switcher(this, LoginActivity.class);
			break;
		}
	}
}
