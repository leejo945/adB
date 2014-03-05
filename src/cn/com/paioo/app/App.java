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
	// 项目中用到的线程池。。。
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
		// 这个一般是在onDestroy 中调用的，所以activity本身已经finish
		activitys.remove(activity);
	}

	/**
	 * 退出整个app
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
			// 首先看内存中有没有保留user信息，
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
		pd.setMessage("加载中");
		pd.show();
	}

	public void dismissLoadingDialog() {
		if (pd != null) {
			pd.dismiss();
		}
	}
}
