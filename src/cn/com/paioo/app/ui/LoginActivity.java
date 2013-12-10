package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.UIHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LoginActivity extends Activity// BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
		super.onCreate(savedInstanceState);
	}

	private void init() {
		 findViewById(R.id.login_forget_pwd_tv).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 MyToast.show(LoginActivity.this, "忘记密码");
					UIHelper.switcher(LoginActivity.this, ForgetActivity.class);
			}
		});

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_bt:
             //登录成功。去首页
			UIHelper.switcher(this, MainActivity.class);
			break;

		case R.id.login_register_bt:
			UIHelper.switcher(this, RegisterActivity.class);
			break;
//		case R.id.login_forget_pwd_tv:
//			//忘记密码界面
//	
//			break;
		}
	}
}
