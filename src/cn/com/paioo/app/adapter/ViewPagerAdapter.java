 
package cn.com.paioo.app.adapter;

import java.util.ArrayList;

 
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter  extends PagerAdapter{
	private ArrayList<View> vs;
	 
	public ViewPagerAdapter(ArrayList<View> vs){
		this.vs = vs;
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return vs.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position,
			Object object) {
		container.removeView(vs.get(position));

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(vs.get(position));

		return vs.get(position);
	}


}
