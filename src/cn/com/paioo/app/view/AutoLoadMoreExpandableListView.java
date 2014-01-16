 
package cn.com.paioo.app.view;

import cn.com.paioo.app.ui.BaseFragment;
import cn.com.paioo.app.ui.PreViewBaseFragment;
import cn.com.paioo.app.ui.ThreeInOneRecordActivity;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import cn.com.paioo.app.util.LogManager;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.AbsListView.OnScrollListener;
public class AutoLoadMoreExpandableListView extends ExpandableListView implements OnScrollListener {
    private Context context;
	public AutoLoadMoreExpandableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		 this.setOnScrollListener(this);
	}
	public AutoLoadMoreExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnScrollListener(this);
		this.context = context;
		 
	}

	public AutoLoadMoreExpandableListView(Context context) {
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
			  if(context!=null&&context instanceof ThreeInOneRecordActivity){
				  ((ThreeInOneRecordActivity)context).pageLoad();
			  }
			  
		}
		
	}

}
