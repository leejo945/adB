package cn.com.paioo.app.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.util.UIHelper;
import cn.com.paioo.app.view.CustomListView;

public class NavPreViewFragmet extends BaseFragment implements OnItemClickListener   {
	 private CustomListView mLV;
	 private FragmentActivity  fa;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.nav_preview, container, false);
	} 
	@Override
	public void onStart() {
		fa = getActivity();
		// TODO Auto-generated method stub
		mLV = (CustomListView) fa.findViewById(R.id.preview_lv);
		PreviewAdapter adapter = new PreviewAdapter(fa, null);
		mLV.setAdapter(adapter);
		mLV.setOnItemClickListener(this);
		super.onStart();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		 UIHelper.switcher(fa, PreviewDetailedActivity.class);
	}
    
	 
}
