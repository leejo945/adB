package cn.com.paioo.app.adapter;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.SlideMenuItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideMenuAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<SlideMenuItem> list;

	public SlideMenuAdapter(Context context, ArrayList<SlideMenuItem> list) {
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.slidemenu_item, null);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.slidemenu_item_icon_iv);
			holder.name = (TextView) convertView
					.findViewById(R.id.slidemenu_item_name_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SlideMenuItem item = list.get(position);
	   //holder.icon.setImageResource(item.icon);
		holder.name.setText(item.name);

		return convertView;
	}

	private static class ViewHolder {
		ImageView icon;
		TextView name;
	}

}
