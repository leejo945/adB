 
package cn.com.paioo.app.adapter;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.Product;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> list;
    public PreviewAdapter(Context context, ArrayList<Product> list){
    	this.context = context;
    	this.list = list;
    	
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
		if(convertView==null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.preview_lv_item, null);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		return convertView;
	}
   private static class ViewHolder{
	   ImageView iv;
	   TextView tv;
   }
}
