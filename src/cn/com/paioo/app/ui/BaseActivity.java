package cn.com.paioo.app.ui;

import cn.com.paioo.app.App;
import cn.com.paioo.app.util.TitleUtil;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.addActivity(this);
		TitleUtil.show(this,-1);
		init();
		setListener();
	}

	@Override
	protected void onDestroy() {
		App.removeActivity(this);
		super.onDestroy();
	}
  
    public void init(){}
    public void setListener(){}

}
