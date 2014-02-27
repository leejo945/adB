package cn.com.paioo.app.ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.SecurityManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.UIManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends Activity// BaseActivity
{
	private AutoCompleteTextView mMail;
	private EditText mPwd;
	private Button mLogin;
	private Handler handler =  new Handler(){
		public void handleMessage(android.os.Message msg) {
		 
			switch (msg.what) {
			case ConstantManager.FILL_DATA_SUCCESS:
				
				break;

			case ConstantManager.FILL_DATA_FAIL:
				
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		App.addActivity(this);
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
		super.onCreate(savedInstanceState);
		init();
	    
	}

	private void init() {
		mMail = (AutoCompleteTextView) findViewById(R.id.login_mail_actv);
		mPwd = (EditText) findViewById(R.id.login_pwd_et);
		mLogin = (Button) findViewById(R.id.login_login_bt);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_bt:
             //登录成功。去首页
			final String mail = StringManager.getStringByET(mMail);
			final String pwd = StringManager.getStringByET(mPwd);
			if (StringManager.isEmpty(mail)) {
				ToastManager.show(this, R.string.warn_toast_mail_isempty);
				return;
			} 
			if(!StringManager.isEmail(mail)){
				ToastManager.show(this, R.string.warn_toast_mail_unstandard);
				return;
			}
			if (StringManager.isEmpty(pwd)) {
				ToastManager.show(this, R.string.warn_toast_pwd_isempty);
				return;
			}
			if (!StringManager.isStandardPwd(pwd)) {
				ToastManager.show(this, R.string.warn_toast_pwd_unstandard);
				return;
			}
			mLogin.setClickable(false);
         
			 
			
			
//			App.pool.addTask(new Thread(){
//				@Override
//				public void run() {
//					try { 
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					User user =  DataService.login(mail,SecurityManager.getMD5HashText(pwd));
//					Message msg;
//					if(user!=null){
// 						  msg = handler.obtainMessage(Constant.FILL_DATA_SUCCESS);
// 
//						 UIHelper.switcher(LoginActivity.this, MainActivity.class);
//					}else{
//						msg = handler.obtainMessage(Constant.FILL_DATA_FAIL);
//						
//					}
//					mLogin.setClickable(true);
//					handler.sendMessage(msg);
//					super.run();
//				}
//			});
//			
//			
			
			
			break;

		case R.id.login_register_bt:
			UIManager.switcher(this, RegisterActivity.class);
			break;
		case R.id.login_forget_pwd_tv:
			//忘记密码界面
			 UIManager.switcher(this, ForgetActivity.class);
			break;
		}
	}
}
