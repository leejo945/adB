package cn.com.paioo.app.view;

import cn.com.paioo.app.LoadData;
import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.BaseFragment;
import cn.com.paioo.app.ui.PreViewBaseFragment;
import cn.com.paioo.app.ui.PresenterIncomeActivity;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import cn.com.paioo.app.util.LogManager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class AutoMoreListView extends ListView implements OnScrollListener {
	String tag = "CustomListView";
    private Context context;
	public AutoMoreListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		 this.setOnScrollListener(this);
	}

	public AutoMoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.setOnScrollListener(this);
	}

	public AutoMoreListView(Context context) {
		super(context);
		 this.setOnScrollListener(this);
	}
  
	
	
	
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		 

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int lastVisiblePosition = view.getLastVisiblePosition();
		if (lastVisiblePosition > 0
				&& lastVisiblePosition + 1 == totalItemCount) {
			  if(context!=null&&context instanceof PresenterIncomeActivity){
				  ((PresenterIncomeActivity)context).pageLoad();
			  }
			  
		}
		 
	}

}
