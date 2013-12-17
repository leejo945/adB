 
package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.TitleUtil;
import android.os.Bundle;

public class PreviewDetailedActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setContentView(R.layout.preview_detailed);
    	super.onCreate(savedInstanceState);
    	 int titleResId = getIntent().getIntExtra("title", 0);
 		TitleUtil.show(this, new int[]{TitleUtil.BACK,TitleUtil.SHARE}, titleResId);
    }
}
