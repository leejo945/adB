package cn.com.paioo.app.ui;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.RechargeRecordAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.view.CustomExpandableListView;
import cn.com.paioo.app.view.PinnedSectionListView;
import android.os.Bundle;

public class RechargeRecordActivity extends BaseActivity {
	private PinnedSectionListView mPSLV; 
	private RechargeRecordAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.recharge_record);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void init() {
//		mPSLV = (PinnedSectionListView) findViewById(R.id.recharge_record_lv);
//		
//	//	ArrayList<User> list = DataService.getRechargeRecordList();
//		
//		 adapter = new RechargeRecordAdapter(this);
//	   	mPSLV.setAdapter(adapter);
//		super.init();
	}
}
