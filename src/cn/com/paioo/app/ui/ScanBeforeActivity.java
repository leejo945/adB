 
package cn.com.paioo.app.ui;

 
 

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.UIManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class ScanBeforeActivity extends BaseActivity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setContentView(R.layout.layout_scan);
    	super.onCreate(savedInstanceState);
    }
    @Override
    public void setListener() {
    	findViewById(R.id.scan_iv).setOnClickListener(this);
    	super.setListener();
    }
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.scan_iv:
			UIManager.switcher(this, ScanInActivity.class);
			break;

		 
		}
		
	}
	
	
	
	/**
	 * 连续按两次返回键就退出
	 */
	private long firstTime;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - firstTime < 4000) {
			((App)getApplication()).exit();
		} else {
			firstTime = System.currentTimeMillis();
			ToastManager.show(this, R.string.warn_toast_again_click_exit);
		}
	}

	
	//退出
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
	    ((App)getApplication()).exit();
	    PreferencesManager.setString(this, ConstantManager.SP_USER_NAME, "");
		return super.onMenuItemSelected(featureId, item);
	}
	
	
	
}
