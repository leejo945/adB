package cn.com.paioo.app.ui;

import java.util.ArrayList;

import com.google.zxing.oned.rss.FinderPattern;

import cn.com.paioo.app.App;
import cn.com.paioo.app.LoadData;
import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.ChartBean;
import cn.com.paioo.app.util.MyToast;
import cn.com.paioo.app.view.ChartView;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TabHomeFragment extends BaseFragment {
	String tag = "NavHomeActivity";
	private TextView mTOPLeft, mTOPRight, mMiddleLeft, mMiddleRight,
			mBottomLeft, mBottomRight;
  
	private ChartView mChart;

	private ArrayList<ChartBean> charts = new ArrayList<ChartBean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("paioo", "TabFinanceFragment   主页，，，，创建");
		return inflater.inflate(R.layout.nav_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        
		mTOPLeft = (TextView) fa.findViewById(R.id.nav_home_top_left_tv);
		mTOPRight = (TextView) fa.findViewById(R.id.nav_home_top_right_tv);
		mMiddleLeft = (TextView) fa.findViewById(R.id.nav_home_middle_left_tv);
		mMiddleRight = (TextView) fa
				.findViewById(R.id.nav_home_middle_right_tv);
		mBottomLeft = (TextView) fa.findViewById(R.id.nav_home_bottom_left_tv);
		mBottomRight = (TextView) fa
				.findViewById(R.id.nav_home_bottom_right_tv);
		mChart = (ChartView) fa.findViewById(R.id.nav_home_chart);

		init();
		super.onActivityCreated(savedInstanceState);
	}

	private void init() {
	    mTOPLeft.setText("￥3658.05");
		mTOPRight.setText("￥7.05");
		mMiddleLeft.setText("247");
		mMiddleRight.setText("3,830");
		mBottomLeft.setText("6.44%");
		mBottomRight.setText("￥0.30");

		initChart();
		mChart.setData(charts);

	}

	private void initChart() {

		charts.add(new ChartBean(0, 0));

		charts.add(new ChartBean(120, 50.50));

		charts.add(new ChartBean(618, 300.35));

		charts.add(new ChartBean(541, 645.32));

		charts.add(new ChartBean(250, 124.20));

		charts.add(new ChartBean(770, 30.00));

	}

	@Override
	public void onDestroy() {

		Log.e("paioo", "TabHomeFragment 主界面，，，，，销毁");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
