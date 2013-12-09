package cn.com.paioo.app.ui;

import java.util.ArrayList;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.ShareAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.ShareInfo;

import android.app.Dialog;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class NavRecommendFriendsActivity extends BaseActivity implements
		OnItemClickListener {
	String tag = "NavRecommendFriendsActivity";
	// 静态主要是优化多次点击分享，重复产生对象.并且又要多处使用
	private static ArrayList<ShareInfo> list;
	private static Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.recommend_friends);
		super.onCreate(savedInstanceState);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.recommend_friends_share_ib: {
			list = (list == null ? DataService.getShareInfoList(this) : list);
			if (!list.isEmpty()) {
				dialog = (dialog == null ? new Dialog(this, R.style.MyDialog)
						: dialog);
				View view = View.inflate(this, R.layout.share_dialog, null);
				GridView mGV = (GridView) view
						.findViewById(R.id.share_dialog_gv);
				ShareAdapter adapter = new ShareAdapter(this, list);
				mGV.setAdapter(adapter);
				dialog.setContentView(view);
				dialog.show();
				mGV.setOnItemClickListener(this);
			}
			break;
		}
		case R.id.recommend_friends_copy_ib:
			break;
		}
	}

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
