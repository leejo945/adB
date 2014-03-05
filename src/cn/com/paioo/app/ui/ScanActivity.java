 
package cn.com.paioo.app.ui;

 
import cn.com.paioo.app.R;
import cn.com.paioo.app.util.UIManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ScanActivity extends BaseActivity implements OnClickListener {
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
			UIManager.switcher(this, ZxingActivity.class);
			break;

		default:
			break;
		}
		
	}
}
