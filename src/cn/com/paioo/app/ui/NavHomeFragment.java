package cn.com.paioo.app.ui;

import java.util.ArrayList;

import cn.com.paioo.app.LoadData;
import cn.com.paioo.app.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;

public class NavHomeFragment extends BaseFragment  {
	String tag = "NavHomeActivity";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.nav_home, container, false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	ArrayList<String> list;
//	static int index = 1;
//	ArrayAdapter<String> adapter;

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		setContentView(R.layout.nav_home);
//		super.onCreate(savedInstanceState);
//		//CustomListView lv = (CustomListView) findViewById(R.id.test);
//		list = new ArrayList<String>();
//		settest(index);
//		adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, list);
//		lv.setAdapter(adapter);
//	}
//
//	@Override
//	public void load() {
//		Log.e(tag, "加载下一页。。。。。");
//		settest(index);
//		adapter.notifyDataSetChanged();
//	}
//
//	public void settest(int index) {
//		for (int i = (index - 1) * 10; i < index * 10; i++) {
//			list.add(i + "-----------");
//		}
//		this.index++;
//	}

//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.home_account_recharge_ib:
//			// 账户充值
//
//			break;
//		case R.id.home_balance_switch_ib:
//			// 转账
//
//			break;
//		case R.id.home_recommend_friends_ib:
//			// 推荐好友
//
//			break;
//		}
//	}

}
