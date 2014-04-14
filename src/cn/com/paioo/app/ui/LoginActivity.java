package cn.com.paioo.app.ui;

import java.util.HashMap;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBack;
import cn.com.paioo.app.engine.NetCallBackIml;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.LogManager;
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
		//如果已经等了。那就不用了
		if(!StringManager.isEmpty(PreferencesManager.getString(this, ConstantManager.SP_USER_NAME))){
			UIManager.switcher(this, ScanBeforeActivity.class);
			finish(); 
		}else{
			App.addActivity(this);
			// TODO Auto-generated method stub
			setContentView(R.layout.layout_login);
			init();
		}
		super.onCreate(savedInstanceState);
	}

	 

	private void init() {
		mUserName = (AutoCompleteTextView) findViewById(R.id.login_username_actv);
		mPwd = (EditText) findViewById(R.id.login_pwd_et);
		mLogin = (Button) findViewById(R.id.login_login_bt);
		String userName = PreferencesManager.getString(this,
				ConstantManager.SP_USER_NAME);
		mUserName.setText(userName);
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
 		final String userName = StringManager.getStringByET(mUserName);
 		final String pwd = StringManager.getStringByET(mPwd);
		if (StringManager.isEmpty(userName)) {
			ToastManager.show(this, R.string.warn_toast_username_isempty);
			return;
		}
		if (StringManager.isBadUserName(userName)) {
			ToastManager.show(this, R.string.warn_toast_username_unstandard);
			return;
		}
		if (StringManager.isEmpty(pwd)) {
			ToastManager.show(this, R.string.warn_toast_pwd_isempty);
			return;
		}
		if (StringManager.isBadPwd(pwd)) {
			ToastManager.show(this, R.string.warn_toast_pwd_unstandard);
			return;
		}
		final Dialog dialog = UIManager.getLoadingDialog(this,
				R.string.warn_dialog_login);
		dialog.show();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("loginId",userName);
		map.put("password",pwd);
	     DataService.login(map,this, new NetCallBackIml() {
			  @Override
			public void netCallBack(Object response) {
				dialog.dismiss();
				//只要一点击，就会保存用户的登录信息，主要是为了用户的二次登陆
				PreferencesManager.setString(LoginActivity.this, ConstantManager.SP_USER_NAME, userName);
				//返回的response做处理
			 
				((App)getApplication()).setUser((User) response);
				LogManager.e("xx", "保存的用户-----"+(User) response);
				UIManager.switcher(LoginActivity.this, ScanBeforeActivity.class);
				
			 
			 	
			 	 
				
				
				
				finish();
				super.netCallBack(response);
			}
			  @Override
			public void netErrorCallBack(Context context,String errorReason) {
				dialog.dismiss();
				super.netErrorCallBack(context,errorReason);
			}
		 
		});

	}

}
