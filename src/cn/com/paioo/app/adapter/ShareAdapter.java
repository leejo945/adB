package cn.com.paioo.app.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.ShareInfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ShareInfo> list;

	public ShareAdapter(Context context, ArrayList<ShareInfo> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.share_item, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.share_item_icon_iv);
			holder.name = (TextView) convertView.findViewById(R.id.share_item_name_tv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		final ShareInfo info = list.get(position);
		 holder.icon.setImageDrawable(info.icon);
		 holder.name.setText(info.name);
		return convertView;
	}

	private static class ViewHolder {
		ImageView icon;
		TextView name;
	}
}
