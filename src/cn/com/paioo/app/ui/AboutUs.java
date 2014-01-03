 
package cn.com.paioo.app.ui;

import android.os.Bundle;
import cn.com.paioo.app.R;
import cn.com.paioo.app.util.TitleManager;

public class AboutUs extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.about_us);
		super.onCreate(savedInstanceState);
		TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.about_us);
	}
}
