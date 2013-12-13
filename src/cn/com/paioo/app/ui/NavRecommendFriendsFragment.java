package cn.com.paioo.app.ui;

import java.util.ArrayList;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.ShareAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.util.MyToast;

import android.app.Dialog;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class NavRecommendFriendsFragment extends BaseFragment implements
		OnItemClickListener {
	String tag = "NavRecommendFriendsActivity";
	// 静态主要是优化多次点击分享，重复产生对象.并且又要多处使用
	private static ArrayList<ShareInfo> list;
	private static Dialog dialog;
   private FragmentActivity fa;
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 Log.e("paioo", "NavRecommendFriendsFragment  推荐好友，，，，，创建");
		// TODO Auto-generated method stub
		 	return  inflater.inflate(R.layout.recommend_friends, container, false);
	}
	
	
	 @Override
		public void onDestroy() {
		 Log.e("paioo", "NavRecommendFriendsFragment  推荐好友，，，，，，销毁");
			 
			// TODO Auto-generated method stub
			super.onDestroy();
		} 
//
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.recommend_friends_share_ib: {
//			list = (list == null ? DataService.getShareInfoList(this) : list);
//			if (!list.isEmpty()) {
//				dialog = (dialog == null ? new Dialog(this, R.style.MyDialog)
//						: dialog);
//				View view = View.inflate(this, R.layout.share_dialog, null);
//				GridView mGV = (GridView) view
//						.findViewById(R.id.share_dialog_gv);
//				ShareAdapter adapter = new ShareAdapter(this, list);
//				mGV.setAdapter(adapter);
//				dialog.setContentView(view);
//				dialog.show();
//				mGV.setOnItemClickListener(this);
//			}
//			break;
//		}
//		case R.id.recommend_friends_copy_ib:
//			break;
//		}
//	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Intent it = new Intent(Intent.ACTION_SEND);
		it.setPackage(list.get(position).packName);
		it.setType("text/plain");
		it.putExtra(Intent.EXTRA_TEXT, "分享的文本内容");
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(it, "分享到**"));

		dialog.cancel();
	}

}
