package cn.com.paioo.app.adapter;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.ui.TabPreViewFragmet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviewAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Product> list;
	private int type;

	public PreviewAdapter(Context context, ArrayList<Product> list,int type) {
		this.context = context;
		this.list = list;
		 this.type = type;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			if(type==0){//推送广告
				convertView = View.inflate(context,
						R.layout.preview_push_item, null);
			}else{//桌面广告
				convertView = View.inflate(context,
						R.layout.preview_desk_item, null);
			}
			

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

	private static class ViewHolder {
		ImageView iv;
		TextView tv;
	}
}
