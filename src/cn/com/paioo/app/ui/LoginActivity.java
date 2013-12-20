package cn.com.paioo.app.ui;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.UIHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity// BaseActivity
{
	private EditText mMail,mPwd;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		App.addActivity(this);
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		mMail = (EditText) findViewById(R.id.login_mail_et);
		mPwd = (EditText) findViewById(R.id.login_pwd_et);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_bt:
             //登录成功。去首页
			String mail = StringUtils.getStringByET(mMail);
			String pwd = StringUtils.getStringByET(mPwd);
			if (StringUtils.isEmpty(mail)) {
				MyToast.show(this, R.string.warn_toast_mail_isempty);
				return;
			} 
			if(!StringUtils.isEmail(mail)){
				MyToast.show(this, R.string.warn_toast_mail_unstandard);
				return;
			}
			if (StringUtils.isEmpty(pwd)) {
				MyToast.show(this, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringUtils.isStandardPwd(pwd)) {
				MyToast.show(this, R.string.warn_toast_pwd_unstandard);
				return;
			}
			
			
			
			
			
			UIHelper.switcher(this, MainActivity.class);
			break;

		case R.id.login_register_bt:
			UIHelper.switcher(this, RegisterActivity.class);
			break;
		case R.id.login_forget_pwd_tv:
			//忘记密码界面
			Toast.makeText(this, "777777777", 1).show();
			 UIHelper.switcher(LoginActivity.this, ForgetActivity.class);
			break;
		}
	}
}
