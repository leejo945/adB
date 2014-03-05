 
package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.Date;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PresenterIncomeAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.PresenterIncome;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.TitleManager;
import cn.com.paioo.app.view.AutoLoadMoreGridView;
import cn.com.paioo.app.view.AutoMoreListView;
import cn.com.paioo.app.view.PullToRefreshView;
import cn.com.paioo.app.view.PullToRefreshView.OnContainerRefreshListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

public class PresenterIncomeActivity extends BaseActivity implements OnContainerRefreshListener {
	private AutoMoreListView lv;
	private  PresenterIncomeAdapter  adapter;
	private PullToRefreshView mPullRefresh;
	private static int pageNum;
	private ProgressBar  mTitlePB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setContentView(R.layout.layout_presenter_income);
    	super.onCreate(savedInstanceState);
    	TitleManager.show(this, new int[]{TitleManager.BACK}, R.string.title_bar_title_referee_money);
    }
    @Override
    public void init() {
    	 lv = (AutoMoreListView) findViewById(R.id.presenter_income_lv);
    	 mPullRefresh = (PullToRefreshView) findViewById(R.id.presenter_income_pullrefresh);
    	 mTitlePB = (ProgressBar) findViewById(R.id.title_bar_load_pb);
    	 View head = View.inflate(this, R.layout.presenter_income_lv_head, null);
    	 lv.addHeaderView(head);
    	 mPullRefresh.setOnRefreshListener(this);
    	 onContainerRefresh();
    
    	 super.init();
    }
    
    public void pageLoad(){
    	mTitlePB.setVisibility(View.VISIBLE);
//    	App.pool.addTask(new Thread(){
//    		@Override
//    		public void run() {
//    			try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//    			 ArrayList<PresenterIncome> list = DataService.getPresenterIncomeList(++pageNum); 
//    			 if(list.size()>0){
//    				 Message msg = handler.obtainMessage(Constant.FILL_DATA_SUCCESS,list);
//        			 handler.sendMessage(msg);
//    			 }else{
//    				 handler.sendEmptyMessage(Constant.FILL_DATA_FAIL);
//    			 }
//    			
//    		}
//    	});
    }
    private Handler handler = new Handler(){
    	 public void handleMessage(android.os.Message msg) {
    			mTitlePB.setVisibility(View.GONE);
    			mPullRefresh.onComplete(new Date().toLocaleString());
    		 switch (msg.what) {
			case ConstantManager.FILL_DATA_SUCCESS:
				if(adapter==null){
					adapter = new PresenterIncomeAdapter(PresenterIncomeActivity.this,( ArrayList<PresenterIncome>)msg.obj);
			    	 lv.setAdapter(adapter);
				}else{
					adapter.setData(( ArrayList<PresenterIncome>)msg.obj);
					adapter.notifyDataSetChanged();
				}
				
				break;

			case ConstantManager.FILL_DATA_FAIL:
				break;
			}
    	 };
    };
	@Override
	public void onContainerRefresh() {
		pageNum = 0;
		adapter = null;
		pageLoad();
	}
}
