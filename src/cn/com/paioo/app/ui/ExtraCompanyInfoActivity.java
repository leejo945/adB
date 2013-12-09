package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExtraCompanyInfoActivity extends BaseActivity {
	private EditText mCompanyName,mLocation,mAddress,
	  mProperty, mCapital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setContentView(R.layout.extra_company_info);
    	super.onCreate(savedInstanceState);
    }
    @Override
    public void init() {
    	// TODO Auto-generated method stub
    	mCompanyName = (EditText) findViewById(R.id.eci_company_name_et);
    	mLocation = (EditText) findViewById(R.id.eci_company_location_et);
    	mAddress = (EditText) findViewById(R.id.eci_company_address_et);
    	mProperty = (EditText) findViewById(R.id.eci_company_property_et);
    	mCapital = (EditText) findViewById(R.id.eci_company_capital_et);
    	super.init();
    }
    public void onClick(View v){
    	switch (v.getId()) {
		case R.id.eci_company_submit_bt:
			String companyName = StringUtils.getStringByET(mCompanyName);
			String location = StringUtils.getStringByET(mLocation);
 			String address = StringUtils.getStringByET(mAddress);
			String property = StringUtils.getStringByET(mProperty);
			String capital = StringUtils.getStringByET(mCapital);
			if(StringUtils.isEmpty(companyName)){
            	MyToast.show(this, R.string.warn_toast_company_name_isempty);
            	return;
            }
			if(StringUtils.isEmpty(location)){
            	MyToast.show(this, R.string.warn_toast_location_isempty);
            	return;
            }
			if(StringUtils.isEmpty(address)){
            	MyToast.show(this, R.string.warn_toast_address_isempty);
            	return;
            }
			if(StringUtils.isEmpty(property)){
            	MyToast.show(this, R.string.warn_toast_property_isempty);
            	return;
            }
			if(StringUtils.isEmpty(capital)){
            	MyToast.show(this, R.string.warn_toast_capital_isempty);
            	return;
            }
			
			
			break;

		 
		}
    }
}
