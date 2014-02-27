package cn.com.paioo.app.ui;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import cn.com.paioo.app.App;
import cn.com.paioo.app.adapter.PreviewAdapter;
import cn.com.paioo.app.engine.DataService;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.view.AutoLoadMoreGridView;
import cn.com.paioo.app.view.PullToRefreshView;

public class PreViewBaseFragment extends BaseFragment {
	public FragmentActivity fa;

	public void pageLoad() {};
     @Override
    public void onAttach(Activity activity) {
    	// TODO Auto-generated method stub
    	 fa = getActivity();
    	super.onAttach(activity);
    }
	
}
