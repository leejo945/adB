package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.engine.NetCallBackIml;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.UIManager;
import cn.com.paioo.app.view.AutoLoadMoreGridView;
import cn.com.paioo.app.view.PullToRefreshView;
import cn.com.paioo.app.view.PullToRefreshView.OnContainerRefreshListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import cn.com.paioo.app.util.LogManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;

public class PreviewPushADFragment extends PreViewBaseFragment implements
		OnItemClickListener, OnContainerRefreshListener {
	private int pageNum;

	public PullToRefreshView mPullRefresh;
	public ProgressBar mTitleBarPB;
	private PreviewAdapter adAdapter;
	private AutoLoadMoreGridView mAdGV;
	private Dialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LogManager.e("paioo", "创建        推送广告Fragment");
		return inflater.inflate(R.layout.layout_preview_ad_push, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdGV = (AutoLoadMoreGridView) fa.findViewById(R.id.push_ad_lv);
		mAdGV.setContext(this);

		mTitleBarPB = (ProgressBar) fa.findViewById(R.id.title_bar_load_pb);
		dialog = UIManager.getLoadingDialog(fa, R.string.warn_dialog_login);

		mPullRefresh = (PullToRefreshView) fa
				.findViewById(R.id.push_ad_pullrefresh);

		onContainerRefresh();
		mAdGV.setOnItemClickListener(this);
		mPullRefresh.setOnRefreshListener(this);
	}

	/**
	 * 数据的分页获取
	 */
	public void pageLoad() {
		if (pageNum != 0) {
			mTitleBarPB.setVisibility(View.VISIBLE);
		} else {
			dialog.show();
			mTitleBarPB.setVisibility(View.VISIBLE);
		}
		DataService.getPushOrDeskAd(ConstantManager.URL_LOGIN, fa, ++pageNum,
				new NetCallBackIml() {
					@Override
					public void netCallBack(Object response) {
						closeShow();
						ArrayList<Product> list = (ArrayList<Product>) response;
						if (list.size() > 0) {
							if (adAdapter == null) {
								adAdapter = new PreviewAdapter(fa, list, 0);
								mAdGV.setAdapter(adAdapter);
							} else {
								adAdapter.setData(list);
								adAdapter.notifyDataSetChanged();
							}
						}

						super.netCallBack(response);
					}

					@Override
					public void netErrorCallBack(Context context,
							String errorReason) {
						closeShow();
						super.netErrorCallBack(context, errorReason);
					}

				});

		// App.pool.addTask(new Thread() {
		// @Override
		// public void run() {
		//
		// ArrayList<Product> list = DataService
		// .getPushOrDeskAdList(++pageNum);
		// LogManager.e("paioo", "pageNum--------"+pageNum);
		// if (list.size() > 0) {
		// Message msg = handler.obtainMessage(
		// Constant.PUSH_FILL_DATA_SUCCESS, list);
		// handler.sendMessage(msg);
		// } else {
		// handler.sendEmptyMessage(Constant.PUSH_FILL_DATA_FAIL);
		// }
		// }
		// });
	}

	private void closeShow() {
		dialog.dismiss();
		mTitleBarPB.setVisibility(View.GONE);
		mPullRefresh.onComplete(new Date().toLocaleString());
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		UIManager.switcher(fa, PreviewDetailedActivity.class);

	}

	@Override
	public void onContainerRefresh() {
		pageNum = 0;
		adAdapter = null;
		pageLoad();

	}

	@Override
	public void onDestroy() {
		LogManager.e("paioo", "销毁push广告");
		super.onDestroy();
	}
}
