package cn.com.paioo.app.ui;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.RechargeRecordAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.CustomExpandableListView;
import android.os.Bundle;

public class RechargeRecordActivity extends BaseActivity {
	private CustomExpandableListView mCELV; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.recharge_record);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void init() {
		mCELV = (CustomExpandableListView) findViewById(R.id.recharge_record_elv);
		
		ArrayList<User> list = DataService.getRechargeRecordList();
		
		RechargeRecordAdapter adapter = new RechargeRecordAdapter(this);
		mCELV.setAdapter(adapter);
		mCELV.setOnGroupExpandListener(null);
		super.init();
	}
}
