package cn.com.paioo.app;

import java.util.ArrayList;

import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.PreferencesManager;
import cn.com.paioo.app.util.StringManager;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;

public class App extends Application {
	public static AppUpdateInfo appUpdateInfo;
	// ��Ŀ���õ����̳߳ء�����
	private static ArrayList<Activity> activitys;
	private User user;

	@Override
	public void onCreate() {
		activitys = new ArrayList<Activity>();
		super.onCreate();
	}

	public static void addActivity(Activity activity) {
		activitys.add(activity);
	}

	public static void removeActivity(Activity activity) {
		// ���һ������onDestroy �е��õģ�����activity�����Ѿ�finish
		activitys.remove(activity);
	}

	/**
	 * �˳�����app
	 */
	public   void exit() {
		for (Activity activity : activitys) {
			activity.finish();
		}
	}

	public void setUser(User _user) {
		user = _user;
	}

	public User getUser() {
		if (user == null) {
			// ���ȿ��ڴ�����û�б���user��Ϣ��
			String userName = PreferencesManager.getString(this,
					ConstantManager.SP_USER_NAME);
			if (!StringManager.isEmpty(userName)) {
				user = new User();
				user.userName = userName;
			}
		}
		return user;
	}

	private ProgressDialog pd;

	public void showLoadingDialog() {
		pd = new ProgressDialog(this);
		pd.setMessage("������");
		pd.show();
	}

	public void dismissLoadingDialog() {
		if (pd != null) {
			pd.dismiss();
		}
	}
}
