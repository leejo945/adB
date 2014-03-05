package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import cn.com.paioo.app.util.UIManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import cn.com.paioo.app.util.LogManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class TabSetUpFragment extends BaseFragment implements OnClickListener {
	 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 LogManager.e("paioo", "TabSetUpFragment   设置，，，，，创建");
		return inflater.inflate(R.layout.layout_nav_setup, container, false);
	}
     @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	 LogManager.e("paioo", "TabSetUpFragment   设置，，，，，onActivityCreated");
    	 
    		fa.findViewById(R.id.setup_modify_contact_way_rl).setOnClickListener(
    				this);
    		fa.findViewById(R.id.setup_modify_password_rl).setOnClickListener(this);
    		fa.findViewById(R.id.setup_suggest_rl).setOnClickListener(this);
    		fa.findViewById(R.id.setup_about_us_rl).setOnClickListener(this);

    		fa.findViewById(R.id.setup_check_update_rl).setOnClickListener(this);
    		fa.findViewById(R.id.setup_safe_exit_bt).setOnClickListener(this);
    	super.onActivityCreated(savedInstanceState);
    }
	
	
	
	 
	@Override
	public void onDestroy() {
		 LogManager.e("paioo", "TabSetUpFragment   设置，，，，，销毁");
		 
		
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// 设置界面
		case R.id.setup_modify_contact_way_rl:
			// 修改联系方式
			UIManager.switcher(fa, ModifyContactWayActivity.class);
			break;

		case R.id.setup_modify_password_rl:
			UIManager.switcher(fa, ModifyPassword.class);
			// 修改密码
			break;
		case R.id.setup_suggest_rl:
			UIManager.switcher(fa, SuggestActivity.class);
			// 意见反馈
			break;
		case R.id.setup_about_us_rl:
			// 关于我们
			UIManager.switcher(fa, AboutUs.class);
			break;
		case R.id.setup_check_update_rl:
			// 版本检测
			ToastManager.show(fa, "版本檢測中。。。。。");
			break;
		case R.id.setup_safe_exit_bt:
			// 退出账号
			UIManager.switcher(fa, LoginActivity.class);

			break;
		}
	}

}
