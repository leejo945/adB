package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.Date;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.adapter.ThreeInOneAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.entity.Record;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.view.PullToRefreshView;
import cn.com.paioo.app.view.PullToRefreshView.OnContainerRefreshListener;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

/**
 * 充值记录,广告消费记录 转账记录 都是同一个界面不同的数据
 * 
 * @author lee
 * 
 */
public class ThreeInOneRecordActivity extends BaseActivity implements
		OnContainerRefreshListener {
	private ExpandableListView mELV;
	private static int pageNum;
	private ThreeInOneAdapter adapter;
	private int titleResId;
    private ProgressBar mTitleBarPB;
    private PullToRefreshView mPullRefresh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.three_in_one);
		titleResId = getIntent().getIntExtra("title", 0);
		TitleManager.show(this, new int[] { TitleManager.BACK }, titleResId);

		super.onCreate(savedInstanceState);

	}
	
	

	@Override
	public void init() {
		mPullRefresh = (PullToRefreshView) findViewById(R.id.three_in_one_pullrefresh);
		mPullRefresh.setOnRefreshListener(this);
		mELV = (ExpandableListView) findViewById(R.id.recharge_record_elv);
		mTitleBarPB = (ProgressBar) findViewById(R.id.title_bar_load_pb);
		onContainerRefresh();
		
	}

	/**
	 * 数据的分页获取
	 */
	public void pageLoad() {
		 mTitleBarPB.setVisibility(View.VISIBLE);
		App.pool.addTask(new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Record> list = DataService.getRecordList(++pageNum);
				if (list.size() > 0) {
					Message msg = handler.obtainMessage(
							Constant.FILL_DATA_SUCCESS, list);
					handler.sendMessage(msg);
				} else {
					handler.sendEmptyMessage(Constant.FILL_DATA_FAIL);
				}
			}
		});
	}

	@Override
	public void onContainerRefresh() {
		pageNum = 0;
		adapter = null;
		pageLoad();

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			mTitleBarPB.setVisibility(View.GONE);
			mPullRefresh.onComplete(new Date().toLocaleString());
			switch (msg.what) {
			case Constant.FILL_DATA_SUCCESS: {
				if (adapter == null) {
                  adapter = new ThreeInOneAdapter(
							ThreeInOneRecordActivity.this,(ArrayList<Record>) msg.obj,titleResId);
					mELV.setAdapter(adapter);
					
				} else {
					 adapter.setData((ArrayList<Record>) msg.obj);
					 adapter.notifyDataSetChanged();
				}
				for (int i = 0; i < adapter.getGroupCount(); i++) {
					mELV.expandGroup(i);
				}
				break;
			}

			case Constant.FILL_DATA_FAIL:
				break;
			}
		};
	};
}
