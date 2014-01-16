package cn.com.paioo.app.ui;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.adapter.ViewPagerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import cn.com.paioo.app.util.LogManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChangePicsActivity extends Activity implements
		OnPageChangeListener {

	private ViewPager mVP;
	private TextView mNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.change_pic);
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		int position = i.getIntExtra("position", -1);
		String[] urls = i.getStringArrayExtra("urls");
		mVP = (ViewPager) findViewById(R.id.change_pic_vp);
		mNum = (TextView) findViewById(R.id.change_pic_tv);
		ArrayList<View> vs = new ArrayList<View>();

		View item0 = View.inflate(this, R.layout.change_vp_item, null);
		View item1 = View.inflate(this, R.layout.change_vp_item, null);
		View item2 = View.inflate(this, R.layout.change_vp_item, null);
		
		((ImageView) item0.findViewById(R.id.change_pic_iv))
				.setImageResource(R.drawable.temp1);
		((ImageView) item1.findViewById(R.id.change_pic_iv))
				.setImageResource(R.drawable.temp2);
		((ImageView) item2.findViewById(R.id.change_pic_iv))
				.setImageResource(R.drawable.temp3);

		vs.add(item0);
		vs.add(item1);
		vs.add(item2);
		ViewPagerAdapter adapter = new ViewPagerAdapter(vs);
		mVP.setAdapter(adapter);

		mVP.setOnPageChangeListener(this);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		mNum.setText(1 + arg0 + "/3");

	}

}
