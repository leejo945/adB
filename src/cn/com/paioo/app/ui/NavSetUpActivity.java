package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import android.os.Bundle;
import android.view.View;

public class NavSetUpActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.nav_setup);
		super.onCreate(savedInstanceState);
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setup_modify_contact_way_rl:

			break;

		case R.id.setup_modify_password_rl:
			break;
		case R.id.setup_check_update_rl:
			break;
		case R.id.setup_safe_exit_bt:
			
			break;
		}
	}
}
