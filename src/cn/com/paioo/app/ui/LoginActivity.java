package cn.com.paioo.app.ui;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.SecurityUtil;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.UIHelper;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends Activity// BaseActivity
{
	private EditText mMail,mPwd;
	private ProgressBar mPB;
	private Button mLogin;
	private Handler handler =  new Handler(){
		public void handleMessage(android.os.Message msg) {
			mPB.setVisibility(View.INVISIBLE);
			switch (msg.what) {
			case Constant.FILL_DATA_SUCCESS:
				
				break;

			case Constant.FILL_DATA_FAIL:
				
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
		mMail = (EditText) findViewById(R.id.login_mail_et);
		mPwd = (EditText) findViewById(R.id.login_pwd_et);
		mPB = (ProgressBar) findViewById(R.id.login_pb);
		mLogin = (Button) findViewById(R.id.login_login_bt);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_bt:
             //登录成功。去首页
			final String mail = StringUtils.getStringByET(mMail);
			final String pwd = StringUtils.getStringByET(mPwd);
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
			mPB.setVisibility(View.VISIBLE);
			mLogin.setClickable(false);
			App.pool.addTask(new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					User user =  DataService.login(mail,SecurityUtil.getMD5HashText(pwd));
					Message msg;
					if(user!=null){
 						  msg = handler.obtainMessage(Constant.FILL_DATA_SUCCESS);
 
						 UIHelper.switcher(LoginActivity.this, MainActivity.class);
					}else{
						msg = handler.obtainMessage(Constant.FILL_DATA_FAIL);
						
					}
					mLogin.setClickable(true);
					handler.sendMessage(msg);
					super.run();
				}
			});
			
			
			
			
			break;

		case R.id.login_register_bt:
			UIHelper.switcher(this, RegisterActivity.class);
			break;
		case R.id.login_forget_pwd_tv:
			//忘记密码界面
			 UIHelper.switcher(this, ForgetActivity.class);
			break;
		}
	}
}
