package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import android.os.Bundle;
import android.view.View;

public class TransferAccountActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.transfer_accounts);
		super.onCreate(savedInstanceState);
	}
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.transfer_next_bt:
			
			break;

		default:
			break;
		}
	}
}
