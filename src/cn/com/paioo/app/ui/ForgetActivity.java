package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.util.UIManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgetActivity extends BaseActivity {
	private EditText mUserName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.forget_pwd);
		super.onCreate(savedInstanceState);
		TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.find_pwd);

	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		mUserName = (EditText) findViewById(R.id.forget_pwd_username_et);
		super.init();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.forget_pwd_submit:
			String userName  = StringManager.getStringByET(mUserName);
			if(StringManager.isEmpty(userName)){
				ToastManager.show(this, "fuck ,这是用户名和邮箱一样的吗");
				return;
			}
			
			UIManager.switcher(this, MainActivity.class);
			break;
		}
	}
}
