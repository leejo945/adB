 
package cn.com.paioo.app.adapter;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.Product;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
    private Context context;
    private int[] urls = new int[3];
    public GalleryAdapter(Context context,Product product){
    	this.context = context;
    	//this.urls = product.urls;
    	urls[0] = R.drawable.temp3;
    	urls[1] = R.drawable.temp1;
    	urls[2] = R.drawable.temp2;
    }
	@Override
	public int getCount() {
		 
		return  urls.length;
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
		if(convertView==null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.preview_detailed_gallery_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.gallery_item_iv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();	
		}
		holder.iv.setImageResource(urls[position]);
		return convertView;
	}
     private static class ViewHolder{
    	 ImageView iv;
     }
}
