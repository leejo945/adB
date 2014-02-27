 
package cn.com.paioo.app.adapter;

 

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.util.LogManager;
import android.content.Context;
 
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
    private Context context;
    private String[] urls ;
    public GalleryAdapter(Context context,Product product){
    	this.context = context;
    	this.urls = product.urls;
 
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
		
		LogManager.e("paioo", "------"+urls[position]);
		//ImageManager.getInstance().displayImage(urls[position], holder.iv,ImageManager.getImageOptions());
		return convertView;
	}
     private static class ViewHolder{
    	 ImageView iv;
     }
}
