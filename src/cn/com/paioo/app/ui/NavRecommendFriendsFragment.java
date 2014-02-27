package cn.com.paioo.app.ui;

import java.util.ArrayList;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.ShareAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.UIManager;
import cn.com.paioo.app.view.ScrGridView;

import android.app.Dialog;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.text.ClipboardManager;
import cn.com.paioo.app.util.LogManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class NavRecommendFriendsFragment extends BaseFragment implements
		OnItemClickListener, OnClickListener {
	String tag = "NavRecommendFriendsActivity";
	// 静态主要是优化多次点击分享，重复产生对象.并且又要多处使用
	private ArrayList<ShareInfo> list;

	 
	private TextView mRecommedCode;
	private ScrGridView mReGV;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LogManager.e("paioo", "NavRecommendFriendsFragment  推荐好友，，，，，创建");

		return inflater.inflate(R.layout.recommend_friends, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		mRecommedCode = (TextView) fa
				.findViewById(R.id.friends_recommed_code_tv);

		mRecommedCode.setText("8888888");

		mReGV = (ScrGridView) fa.findViewById(R.id.recommend_friends_gv);
		list = DataService.getShareInfoList(fa);
		mReGV.setAdapter(new ShareAdapter(fa, list));
		mReGV.setOnItemClickListener(this);

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		LogManager.e("paioo", "NavRecommendFriendsFragment  推荐好友，，，，，，销毁");
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position == list.size()-1) {
			ClipboardManager cmb = (ClipboardManager) fa.getSystemService(Context.CLIPBOARD_SERVICE);
			cmb.setText("http://www.gooogle.com");
			ToastManager.show(fa, R.string.warn_toast_already);
			return;
		}

		String packName = list.get(position).packName;
		if (StringManager.isEmpty(packName)) {
			ToastManager.show(fa, R.string.warn_toast_app_uninstall);
			return;
		}
		Intent it = new Intent(Intent.ACTION_SEND);
		it.setPackage(packName);
		it.setType("text/plain");
		it.putExtra(Intent.EXTRA_TEXT, "这是分享中，测试的文本内容");
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(it, "分享到**"));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		//
		// case R.id.friends_share_to_friends_ll: {
		// list = (list == null ? DataService.getShareInfoList(fa) : list);
		// if (!list.isEmpty()) {
		// dialog = (dialog == null ? new Dialog(fa, R.style.MyDialog)
		// : dialog);
		//
		//
		// View view = View.inflate(fa, R.layout.share_dialog, null);
		// GridView mGV = (GridView) view
		// .findViewById(R.id.share_dialog_gv);
		// ShareAdapter adapter = new ShareAdapter(fa, list);
		// mGV.setAdapter(adapter);
		// dialog.setContentView(view);
		// dialog.show();
		// mGV.setOnItemClickListener(this);
		// }
		// break;
		}

	}

}
