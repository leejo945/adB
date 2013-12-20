 
package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ChangePicsActivity extends  Activity   {
    private ImageView iv;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setContentView(R.layout.change_pic);
    	super.onCreate(savedInstanceState);
    	
    	
    	
    	iv = (ImageView) findViewById(R.id.change_pic_iv);
    	 iv.setImageResource(R.drawable.temp2);
    	
    	
    }
   
//    @Override
//    public void init() {
////    	   Gallery mGallery = (Gallery) findViewById(R.id.chage_pic_gallery);
////    	   mGallery.setAdapter(new AA(this));
//    	 
//    }
	 
}
//class AA extends BaseAdapter{
//    private Context context;
//    private int[] urls = new int[3];
// 
//	public AA(Context context){
//		this.context = context;
//		   urls[0] = R.drawable.temp3;
//			urls[1] = R.drawable.temp1;
//			urls[2] = R.drawable.temp2;
//	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return urls.length;
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder holder;
//		if(convertView==null){
//			holder = new ViewHolder();
//			convertView = View.inflate(context, R.layout.change_pic_gallery_item, null);
//			holder.iv = (ImageView) convertView.findViewById(R.id.change_pic_gallery_item_gi);
//			convertView.setTag(holder);
//		}else{
//			holder = (ViewHolder) convertView.getTag();
//		}
//		holder.iv.setImageResource(urls[position]);
//		// TODO Auto-generated method stub
//		return convertView;
//	}
//	private static class ViewHolder{
//		ImageView iv;
//	}
//}