package cn.com.paioo.app.ui;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBack;
import cn.com.paioo.app.engine.NetCallBackIml;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.UIManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity// BaseActivity
{
	private AutoCompleteTextView mUserName;
	private EditText mPwd;
	private Button mLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		App.addActivity(this);
		// TODO Auto-generated method stub
		setContentView(R.layout.layout_login);
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	protected void onResume() {
		String userName = PreferencesManager.getString(this,
				ConstantManager.SP_USER_NAME);
		mUserName.setText(userName);
		super.onResume();
	}

	private void init() {
		mUserName = (AutoCompleteTextView) findViewById(R.id.login_mail_actv);
		mPwd = (EditText) findViewById(R.id.login_pwd_et);
		mLogin = (Button) findViewById(R.id.login_login_bt);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_bt:
			// 登录成功。去首页
			login();
			break;

		case R.id.login_register_bt:
			// 注册
			UIManager.switcher(this, RegisterActivity.class);
			break;
		case R.id.login_forget_pwd_tv:
			// 忘记密码界面
			UIManager.switcher(this, ForgetActivity.class);
			break;
		}
	}

	private void login() {
//		final String mail = StringManager.getStringByET(mUserName);
//		final String pwd = StringManager.getStringByET(mPwd);
//		if (StringManager.isEmpty(mail)) {
//			ToastManager.show(this, R.string.warn_toast_mail_isempty);
//			return;
//		}
//		if (!StringManager.isEmail(mail)) {
//			ToastManager.show(this, R.string.warn_toast_mail_unstandard);
//			return;
//		}
//		if (StringManager.isEmpty(pwd)) {
//			ToastManager.show(this, R.string.warn_toast_pwd_isempty);
//			return;
//		}
//		if (!StringManager.isStandardPwd(pwd)) {
//			ToastManager.show(this, R.string.warn_toast_pwd_unstandard);
//			return;
//		}
//		final Dialog dialog = UIManager.getLoadingDialog(this,
//				R.string.warn_dialog_login);
//		dialog.show();
//		DataService.login(ConstantManager.URL_LOGIN, this, new NetCallBackIml() {
//			  @Override
//			public void netCallBack(Object response) {
//				dialog.dismiss();
//				PreferencesManager.setString(LoginActivity.this, ConstantManager.SP_USER_NAME, mail);
//				//返回的response做处理
//				
//				User user = (User) response;
//				((App)getApplication()).setUser(user);
				UIManager.switcher(LoginActivity.this, ScanActivity.class);
//				super.netCallBack(response);
//			}
//			  @Override
//			public void netErrorCallBack(Context context,String errorReason) {
//				dialog.dismiss();
//				super.netErrorCallBack(context,errorReason);
//			}
//		 
//		});

	}

}
