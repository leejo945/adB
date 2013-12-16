package cn.com.paioo.app.ui;

import com.google.zxing.oned.rss.FinderPattern;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.UIHelper;
import cn.com.paioo.app.view.AutoMoreListView;

public class TabPreViewFragmet extends BaseFragment implements OnItemClickListener, OnTouchListener, OnCheckedChangeListener  {
	String tag = "TabPreViewFragmet";
	 private FragmentActivity  fa;
	 
	 private GridView mDeskOrPush;
	  
	
	 private RadioGroup mRG;
	 
	 public static final int AD_TYPE_PUSH =0;
	 public static final int AD_TYPE_DESK =1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 Log.e("paioo", "TabFinanceFragment   预览，，，，，创建");
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.nav_preview, container, false);
	} 
	@Override
	public void onStart() {
		fa = getActivity();
		mDeskOrPush = (GridView) fa.findViewById(R.id.preview_desk_or_push_gv);
		// TODO Auto-generated method stub
		  initPushAD();
		
		
	 
		
		
		
		mRG = (RadioGroup) fa.findViewById(R.id.preview_rg);
		mRG.setOnCheckedChangeListener(this);
		super.onStart();
	}
	private void initDeskAD() {
		mDeskOrPush.setNumColumns(2);
		PreviewAdapter deskAdapter = new PreviewAdapter(fa, null,AD_TYPE_DESK);
		mDeskOrPush.setAdapter(deskAdapter);
		//mDeskOrPush.setOnItemClickListener(this);
		//mDesk.setOnTouchListener(this);
		
	}
	private void initPushAD() {
 		
		PreviewAdapter pushAdapter = new PreviewAdapter(fa, null,AD_TYPE_PUSH);
	 
		mDeskOrPush.setAdapter(pushAdapter);
		
		
		
	
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		 UIHelper.switcher(fa, PreviewDetailedActivity.class);
	}
    
	@Override
	public void onDestroy() {
		 Log.e("paioo", "TabPreViewFragmet   预览，，，，，销毁");
	 
		
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	/**
	 * 如果当前为左边条目：
	 * 1.left--->right   不处理
	 * 2.right---left    到右边条目
	 * 
	 * 如果当前为右边条目
	 * 1.left--->right    处理
	 * 2.right---left    不处理
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		
		 
		Log.e(tag, event.getX()+"-------"+event.getY());
		return false;
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.preview_push_ad_rb:
			initPushAD();
			break;

		case R.id.preview_desktop_ad_rb:
			initDeskAD();
			break;
		}
		
	}
 
	
	
}
