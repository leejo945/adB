package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.util.UIManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ExtraCompanyInfoActivity extends BaseActivity {
	private EditText mCompanyName,mLocation,mAddress,
	  mProperty, mCapital;
	private ImageView mRight;
    private static boolean flag = true;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setContentView(R.layout.extra_company_info);
    	super.onCreate(savedInstanceState);
    	TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.eci_company_info_title);
    }
    @Override
    public void init() {
    	// TODO Auto-generated method stub
    	mCompanyName = (EditText) findViewById(R.id.eci_company_name_et);
    	mLocation = (EditText) findViewById(R.id.eci_company_location_et);
    	mAddress = (EditText) findViewById(R.id.eci_company_address_et);
    	mProperty = (EditText) findViewById(R.id.eci_company_property_et);
    	mCapital = (EditText) findViewById(R.id.eci_company_capital_et);
    	mRight = (ImageView) findViewById(R.id.eci_company_right_iv);
    	super.init();
    }
    public void onClick(View v){
    	switch (v.getId()) {
		case R.id.eci_company_submit_bt:
			String companyName = StringManager.getStringByET(mCompanyName);
			String location = StringManager.getStringByET(mLocation);
 			String address = StringManager.getStringByET(mAddress);
			String property = StringManager.getStringByET(mProperty);
			String capital = StringManager.getStringByET(mCapital);
			if(StringManager.isEmpty(companyName)){
            	ToastManager.show(this, R.string.warn_toast_company_name_isempty);
            	return;
            }
			if(StringManager.isEmpty(location)){
            	ToastManager.show(this, R.string.warn_toast_location_isempty);
            	return;
            }
			if(StringManager.isEmpty(address)){
            	ToastManager.show(this, R.string.warn_toast_address_isempty);
            	return;
            }
			if(StringManager.isEmpty(property)){
            	ToastManager.show(this, R.string.warn_toast_property_isempty);
            	return;
            }
			if(StringManager.isEmpty(capital)){
            	ToastManager.show(this, R.string.warn_toast_capital_isempty);
            	return;
            }
			if(!flag){
				ToastManager.show(this, R.string.warn_toast_unagree);
            	return;
			}
			UIManager.switcher(this, MainActivity.class);
			break;
			
		case R.id.eci_company_right_iv2:
		case R.id.eci_company_right_iv:
			if(flag){
				mRight.setVisibility(View.INVISIBLE);
				flag = false;
			}else{
				mRight.setVisibility(View.VISIBLE);
				flag = true;
			}
			
			break;
		 
		}
    }
}
