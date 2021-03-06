package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.util.UIManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SuggestActivity extends BaseActivity {
	private EditText mContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.layout_suggest);
		super.onCreate(savedInstanceState);
		TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.suggest);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		mContent = (EditText) findViewById(R.id.suggest_et);
		super.init();
	}
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.suggest_call_rl:
			//打电话
			String telephone = getResources().getString(R.string.telephone_number);
			Uri uri = Uri.parse("tel:"+telephone);
			Intent intent = new Intent(Intent.ACTION_CALL, uri);
			startActivity(intent);
			break;

		case R.id.suggest_submit_bt:
			//提交
			String content =  StringManager.getStringByET(mContent);
			if(StringManager.isEmpty(content)){
				ToastManager.show(this, R.string.warn_toast_suggest_isempty);
				return;
			}
			
			
			
			UIManager.switcher(this, MainActivity.class);
			break;
		}
	}
}
