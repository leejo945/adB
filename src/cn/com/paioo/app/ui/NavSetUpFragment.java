package cn.com.paioo.app.ui;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.UIHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NavSetUpFragment extends BaseFragment {
	private FragmentManager fm;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		fm = getFragmentManager();
		return inflater.inflate(R.layout.nav_setup, container, false);
	}
    @Override
    public void onStart() {
    	// TODO Auto-generated method stub
    	 
    	super.onStart();
    }	
	
//	public void onClick(View v) {
//		
//		
//		Toast.makeText(getActivity(), "aaa", 0).show();
//		switch (v.getId()) {
//
//		// ���ý���
//		case R.id.setup_modify_contact_way_rl:
//			// �޸���ϵ��ʽ
//			 UIHelper.switcher(getActivity(), ModifyContactWayActivity.class);
//			break;
//
//		case R.id.setup_modify_password_rl:
//			 UIHelper.switcher(getActivity(), ModifyPassword.class);
//			// �޸�����
//			break;
//		case R.id.setup_suggest_rl:
//			 UIHelper.switcher(getActivity(), SuggestActivity.class);
//			// �������
//			break;
//		case R.id.setup_about_us_rl:
//			// ��������
//			//UIHelper.switcher(this, AboutUs.class);
//			break;
//		case R.id.setup_safe_exit_bt:
//			// �˳��˺�
//
//			//UIHelper.switcher(this, LoginActivity.class);
//
//			break;
//		}
//	}

}
