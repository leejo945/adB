package cn.com.paioo.app;

import java.util.ArrayList;
 

 

import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.StringUtils;
import cn.com.paioo.app.util.ThreadPool;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class App extends Application {
	public static AppUpdateInfo appUpdateInfo;
	

	// ��Ŀ���õ����̳߳ء�����
	public static ThreadPool pool = ThreadPool.getInstance();
	
	
	
	
	
	
	
	private static final ArrayList<Activity> ACTIVITYS = new ArrayList<Activity>();
    private static User user;
	
	  
	public static void addActivity(Activity activity) {
		ACTIVITYS.add(activity);
	}

	public static void removeActivity(Activity activity) {
		//���һ������onDestroy �е��õģ�����activity�����Ѿ�finish
		ACTIVITYS.remove(activity);
	}

	/**
	 * �˳�����app
	 */
	public static void exit() {
		for (Activity activity : ACTIVITYS) {
			 activity.finish();
		}
	}

	public static void saveUser(){
		
	}
	public static User getUser(){
		return null;
	}
	 
	
	

}
