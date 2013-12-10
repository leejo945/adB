package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.UIHelper;
import android.os.Bundle;
import android.view.View;

public class ForgetActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.forget_pwd);
		super.onCreate(savedInstanceState);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.forget_pwd_submit:
			UIHelper.switcher(this, MainActivity.class);
			break;
		}
	}
}
