package cn.com.paioo.app.view;

import cn.com.paioo.app.ui.BaseFragment;
import cn.com.paioo.app.ui.PreViewBaseFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.AbsListView.OnScrollListener;
public class AutoLoadMoreGridView extends GridView implements   OnScrollListener {
    
    private  Fragment context;
	public AutoLoadMoreGridView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		 this.setOnScrollListener(this);
		  
		// TODO Auto-generated constructor stub
	}
	public AutoLoadMoreGridView(Context context, AttributeSet attrs) {
		super(context, attrs );
		 this.setOnScrollListener(this);
		 
	}
	public AutoLoadMoreGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		 this.setOnScrollListener(this);
		  
	}
	public void setContext(Fragment context){
		this.context = context;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int lastVisiblePosition = view.getLastVisiblePosition();
		if (lastVisiblePosition > 0
				&& lastVisiblePosition + 1 == totalItemCount) {
			  if(context!=null&&context instanceof BaseFragment){
				  ((PreViewBaseFragment)context).pageLoad();
			  }
			  
		}
		
	}
	 
	
	
	
}
