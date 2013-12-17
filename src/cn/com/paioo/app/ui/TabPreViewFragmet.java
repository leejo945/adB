package cn.com.paioo.app.ui;

import com.google.zxing.oned.rss.FinderPattern;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.UIHelper;
import cn.com.paioo.app.view.AutoMoreListView;
import cn.com.paioo.app.view.PullToRefreshGridView;

@SuppressLint("ResourceAsColor")
public class TabPreViewFragmet extends BaseFragment implements  OnTouchListener, OnCheckedChangeListener  {
	String tag = "TabPreViewFragmet";
	 private FragmentActivity  fa;
	 
	 private PreviewPushADFragment push;
	 private PreviewDeskADFragment desk;
	 private RadioGroup mRG;
	 private RadioButton mPushRB,mDeskRB;
	 private FragmentManager fm;
	 private LinearLayout mPushLL,mDeskLL;
	 
	 
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 Log.e("paioo", "TabFinanceFragment   Ԥ����������������");
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.nav_preview, container, false);
	} 
	@Override
	public void onStart() {
 		fa = getActivity();
		fm = getFragmentManager();
		push = new PreviewPushADFragment();
		desk = new PreviewDeskADFragment();
		mPushLL = (LinearLayout) fa.findViewById(R.id.preview_push_ad_ll);
		mDeskLL = (LinearLayout) fa.findViewById(R.id.preview_desk_ad_ll);
		fm.beginTransaction().replace(R.id.preview_push_ad_ll, push).commit();
 		mRG = (RadioGroup) fa.findViewById(R.id.preview_rg);
 		mPushRB = (RadioButton) fa.findViewById(R.id.preview_push_ad_rb);
 		mDeskRB = (RadioButton) fa.findViewById(R.id.preview_desk_ad_rb);
 		mPushRB.setChecked(true);
 		mPushRB.setTextColor(Color.rgb(255, 97, 0));
 		mRG.setOnCheckedChangeListener(this);
		super.onStart();
	}
 
 
    
	@Override
	public void onDestroy() {
		 Log.e("paioo", "TabPreViewFragmet   Ԥ����������������");
	 
		
		 
		super.onDestroy();
	}
	/**
	 * �����ǰΪ�����Ŀ��
	 * 1.left--->right   ������
	 * 2.right---left    ���ұ���Ŀ
	 * 
	 * �����ǰΪ�ұ���Ŀ
	 * 1.left--->right    ����
	 * 2.right---left    ������
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		
		 
		Log.e(tag, event.getX()+"-------"+event.getY());
		return false;
	}
	private void hiddenView(int id){
		if(id==R.id.preview_push_ad_rb){
			mPushLL.setVisibility(View.VISIBLE);
			mDeskLL.setVisibility(View.GONE);
		}else{
			mPushLL.setVisibility(View.GONE);
			mDeskLL.setVisibility(View.VISIBLE);
		}
	}
	
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction ft = fm.beginTransaction();
		hiddenView(checkedId);
		switch (checkedId) {
		case R.id.preview_push_ad_rb:
	 		mPushRB.setTextColor(Color.rgb(255, 97, 0));
	 		mDeskRB.setTextColor(Color.rgb(0, 0, 0));
			if(!push.isAdded()){
				ft.replace(R.id.preview_push_ad_ll, push).commit();
			}else{
				Log.e("paioo", "����Ŀ�Ѿ����");
			}
			break;

		case R.id.preview_desk_ad_rb:
			mDeskRB.setTextColor(Color.rgb(255, 97, 0));
	 		mPushRB.setTextColor(Color.rgb(0, 0, 0));
			if(!desk.isAdded()){
				ft.replace(R.id.preview_desk_ad_ll, desk).commit();
			}else{
				Log.e("paioo", "����Ŀ�Ѿ����");
			}
			break;
		}
		
	}
 
	
	
}
