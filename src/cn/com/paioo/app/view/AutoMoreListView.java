package cn.com.paioo.app.view;

import cn.com.paioo.app.LoadData;
import cn.com.paioo.app.R;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class AutoMoreListView extends ListView implements OnScrollListener {
	String tag = "CustomListView";
	private Context context;
	private int currScrollState;

	public AutoMoreListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public AutoMoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		setOnScrollListener(this);
	}

	public AutoMoreListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    @Override
    public void setAdapter(ListAdapter adapter) {
    	// TODO Auto-generated method stub
        View v = View.inflate(context, R.layout.lv_footer, null);
    	addFooterView(v);
    	super.setAdapter(adapter);
    }
	 
	
	
	
	
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		currScrollState = scrollState;

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		int lastVisiblePosition = view.getLastVisiblePosition();
//		Log.e(tag, (lastVisiblePosition == (totalItemCount - 1)) + "");
//		Log.e(tag, currScrollState + "-----------" + lastVisiblePosition);

		if (lastVisiblePosition == (totalItemCount - 1)
				&& lastVisiblePosition > 0) {
			// 获得下一页
			// 这里设置一个回调函数
			((LoadData) context).load();

		}
	}

}
