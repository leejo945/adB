package cn.com.paioo.app.ui;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.GalleryAdapter;
import cn.com.paioo.app.adapter.ShareAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.TitleUtil;
import cn.com.paioo.app.util.UIHelper;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PreviewDetailedActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener, OnItemSelectedListener {
	private static ArrayList<ShareInfo> list;
	private static Dialog dialog;
	private RadioButton[] mPointsRB = new RadioButton[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.preview_detailed);
		super.onCreate(savedInstanceState);
		int titleResId = getIntent().getIntExtra("title", 0);
		TitleUtil.show(this, new int[] { TitleUtil.BACK }, titleResId);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		TextView title = (TextView) findViewById(R.id.title_bar_title_tv);
		
		ImageButton mTitleShare = (ImageButton) findViewById(R.id.title_bar_share_ib);
		Gallery mGallery = (Gallery) findViewById(R.id.preview_detailed_product_imgs_gallery);
		mPointsRB[0] = (RadioButton) findViewById(R.id.preview_detailed_point0_rb);
		mPointsRB[1] = (RadioButton) findViewById(R.id.preview_detailed_point1_rb);
		mPointsRB[2] = (RadioButton) findViewById(R.id.preview_detailed_point2_rb);
		 
		
		
		
		
		title.setText(R.string.title_bar_title_preview_detailed);
		mGallery.setOnItemClickListener(this);
		Product product = new Product();
		mGallery.setAdapter(new GalleryAdapter(this, product));
		mTitleShare.setVisibility(View.VISIBLE);
		mTitleShare.setOnClickListener(this);
		mGallery.setOnItemSelectedListener(this);

		super.init();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_share_ib: {
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
		case R.id.preview_detailed_bus:{
			MyToast.show(this, "公交信息");
			break;
		}
		case R.id.preview_detailed_cbd:{
			MyToast.show(this, "商业中心");
			break;
		}
		case R.id.preview_detailed_business:{
			MyToast.show(this, "用户信息");
			break;
		}
		case R.id.preview_detailed_time:{
			MyToast.show(this, "时间。。。");
			break;
		}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		
		
		
		if(parent instanceof GridView){

			if (position == list.size()-1) {
				ClipboardManager cmb = (ClipboardManager)  getSystemService(Context.CLIPBOARD_SERVICE);
				cmb.setText("http://www.gooogle.com");
				MyToast.show(this, R.string.warn_toast_already);
				return;
			}

			String packName = list.get(position).packName;
			if (StringUtils.isEmpty(packName)) {
				MyToast.show(this, R.string.warn_toast_app_uninstall);
				return;
			}
			Intent it = new Intent(Intent.ACTION_SEND);
			it.setPackage(packName);
			it.setType("text/plain");
			it.putExtra(Intent.EXTRA_TEXT, "这是分享中，测试的文本内容");
			it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(it, "分享到**"));
			dialog.cancel();
		}else{
			UIHelper.switcher(this, ChangePicsActivity.class);
		}
		
		

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		mPointsRB[position].setChecked(true);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}
