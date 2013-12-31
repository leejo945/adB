package cn.com.paioo.app;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.ThreadPool;
import android.app.Activity;
import android.app.Application;
 

public class App extends Application {
	public static AppUpdateInfo appUpdateInfo;

	// 项目中用到的线程池。。。
	public static ThreadPool pool;
	private static ArrayList<Activity> activitys;
	private static User user;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		pool = ThreadPool.getInstance();
		activitys = new ArrayList<Activity>();
		initImageLoader();
		super.onCreate();
	}

	//初始化全局的图片加载器
	private void initImageLoader() {
		// 图片加载，设置一个默认全局配置
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
		  
		.build();
		ImageLoader.getInstance().init(config);
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

}
