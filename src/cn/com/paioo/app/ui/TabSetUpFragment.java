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
		 LogManager.e("paioo", "TabSetUpFragment   ���ã�������������");
		return inflater.inflate(R.layout.layout_nav_setup, container, false);
	}
     @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	 LogManager.e("paioo", "TabSetUpFragment   ���ã���������onActivityCreated");
    	 
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
		 LogManager.e("paioo", "TabSetUpFragment   ���ã�������������");
		 
		
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// ���ý���
		case R.id.setup_modify_contact_way_rl:
			// �޸���ϵ��ʽ
			UIManager.switcher(fa, ModifyContactWayActivity.class);
			break;

		case R.id.setup_modify_password_rl:
			UIManager.switcher(fa, ModifyPassword.class);
			// �޸�����
			break;
		case R.id.setup_suggest_rl:
			UIManager.switcher(fa, SuggestActivity.class);
			// �������
			break;
		case R.id.setup_about_us_rl:
			// ��������
			UIManager.switcher(fa, AboutUs.class);
			break;
		case R.id.setup_check_update_rl:
			// �汾���
			ToastManager.show(fa, "�汾�z�y�С���������");
			break;
		case R.id.setup_safe_exit_bt:
			// �˳��˺�
			UIManager.switcher(fa, LoginActivity.class);

			break;
		}
	}

}
