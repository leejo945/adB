package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.UIHelper;
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
			String userName  = StringUtils.getStringByET(mUserName);
			if(StringUtils.isEmpty(userName)){
				MyToast.show(this, "fuck ,这是用户名和邮箱一样的吗");
				return;
			}
			
			UIHelper.switcher(this, MainActivity.class);
			break;
		}
	}
}
