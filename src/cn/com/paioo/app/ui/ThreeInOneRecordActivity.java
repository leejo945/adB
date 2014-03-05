package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.Date;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.adapter.ThreeInOneAdapter;
import cn.com.paioo.app.adapter.ThreeInOneAdapter2;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.entity.Record;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.view.PullToRefreshView;
import cn.com.paioo.app.view.PullToRefreshView.OnContainerRefreshListener;
import cn.com.paioo.app.view.stub.PinnedSectionListView;
import cn.com.paioo.app.view.stub.SimpleSectionedListAdapter;
import cn.com.paioo.app.view.stub.SimpleSectionedListAdapter.Section;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.com.paioo.app.util.LogManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
		setContentView(R.layout.layout_three_in_one);
		titleResId = getIntent().getIntExtra("title", 0);
		TitleManager.show(this, new int[] { TitleManager.BACK }, titleResId);

		super.onCreate(savedInstanceState);

	}

	// test----------------------------------------------------

	private void initControls() {
		ArrayList<Section> sections = new ArrayList<Section>();
		Integer[] mHeaderPositions = { 0, 6, 11, 37, 38, 60, 77, 89, 99, 100,
				124, 125, 135, 149, 158, 159, 167, 190, 199 };
		PinnedSectionListView list = (PinnedSectionListView) findViewById(R.id.list);
 
	   ThreeInOneAdapter mAdapter = new ThreeInOneAdapter(this, null, titleResId);
		for (int i = 0; i < mHeaderPositions.length; i++) {
			sections.add(new Section(mHeaderPositions[i], i + "月"));
		}
		SimpleSectionedListAdapter simpleSectionedGridAdapter = new SimpleSectionedListAdapter(
				this, R.layout.three_in_one_item_group, mAdapter);
		// toArray 将列表中的元素，以数组的形式返回
		 simpleSectionedGridAdapter
		 		.setSections(sections.toArray(new Section[0]));

		list.setAdapter(simpleSectionedGridAdapter);

	}

	@Override
	public void init() {

		initControls();

		// mPullRefresh = (PullToRefreshView)
		// findViewById(R.id.three_in_one_pullrefresh);
		// mPullRefresh.setOnRefreshListener(this);
		// mELV = (ExpandableListView) findViewById(R.id.recharge_record_elv);
		// mTitleBarPB = (ProgressBar) findViewById(R.id.title_bar_load_pb);
		// onContainerRefresh();

	}

	/**
	 * 数据的分页获取
	 */
	public void pageLoad() {
		mTitleBarPB.setVisibility(View.VISIBLE);
//		App.pool.addTask(new Thread() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				ArrayList<Record> list = DataService.getRecordList(++pageNum);
//				if (list.size() > 0) {
//					Message msg = handler.obtainMessage(
//							Constant.FILL_DATA_SUCCESS, list);
//					handler.sendMessage(msg);
//				} else {
//					handler.sendEmptyMessage(Constant.FILL_DATA_FAIL);
//				}
//			}
//		});
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
			case ConstantManager.FILL_DATA_SUCCESS: {
				if (adapter == null) {
					adapter = new ThreeInOneAdapter(
							ThreeInOneRecordActivity.this,
							(ArrayList<Record>) msg.obj, titleResId);
					mELV.setAdapter(adapter);

				} else {
					adapter.setData((ArrayList<Record>) msg.obj);
					adapter.notifyDataSetChanged();
				}
				//for (int i = 0; i < adapter.getGroupCount(); i++) {
				//	mELV.expandGroup(i);
				//}
				break;
			}

			case ConstantManager.FILL_DATA_FAIL:
				break;
			}
		};
	};
}
