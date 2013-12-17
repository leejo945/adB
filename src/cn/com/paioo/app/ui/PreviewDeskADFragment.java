package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.util.UIHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class PreviewDeskADFragment extends BaseFragment implements OnItemClickListener {
	FragmentActivity fa;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("paioo", "´´½¨PushADFragment");
		return inflater.inflate(R.layout.preview_ad_desk, container, false);
	}

	@Override
	public void onStart() {
		 fa = getActivity();
		PreviewAdapter pushAdapter = new PreviewAdapter(fa, null, 1 );
		GridView gv = (GridView) fa.findViewById(R.id.desk_ad_gv);
		gv.setAdapter(pushAdapter);
        gv.setOnItemClickListener(this);
		super.onStart();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		 UIHelper.switcher(fa, PreviewDetailedActivity.class);
		
	}
}
