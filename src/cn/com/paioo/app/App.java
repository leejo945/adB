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
   
	// ��Ŀ���õ����̳߳ء�����
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
		// ���һ������onDestroy �е��õģ�����activity�����Ѿ�finish
		activitys.remove(activity);
	}

	/**
	 * �˳�����app
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
		pd.setMessage("������");
		pd.show();
	}
	public void dismissLoadingDialog(){
		if(pd!=null){
			pd.dismiss();
		}
	}
}
