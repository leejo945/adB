package cn.com.paioo.app;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
 

 
import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.User;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
 
 

public class App extends Application {
	public static AppUpdateInfo appUpdateInfo;
   
	// 项目中用到的线程池。。。
	private static ArrayList<Activity> activitys;
	private static User user;
    
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
	public static void exit() {
		for (Activity activity : activitys) {
			activity.finish();
		}
	}

	public static void saveUser() {

	}

	public static User getUser() {
		return null;
	}
	 
	private ProgressDialog pd;
	public void showLoadingDialog() {
		pd = new ProgressDialog(this);
		pd.setMessage("加载中");
		pd.show();
	}
	public void dismissLoadingDialog(){
		if(pd!=null){
			pd.dismiss();
		}
	}
}
