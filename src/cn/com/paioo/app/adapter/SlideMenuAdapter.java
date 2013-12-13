package cn.com.paioo.app.adapter;


import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.MainActivity;
import cn.com.paioo.app.util.MyToast;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideMenuAdapter extends BaseAdapter {
	private Context context;
	private int[] icons;
	private int[] names;

	public SlideMenuAdapter(Context context, int[] icons, int[] names) {
		this.context = context;
		this.icons = icons;
		this.names = names;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return icons.length;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.slidemenu_item, null);
			holder.iv = (ImageView) convertView
					.findViewById(R.id.slidemenu_item_icon_iv);
			holder.tv = (TextView) convertView
					.findViewById(R.id.slidemenu_item_name_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.iv.setImageResource(icons[position]);

		 holder.tv.setText(names[position]);
    
		return convertView;
	}

	private static class ViewHolder {
		ImageView iv;
		TextView tv;
	}
}
