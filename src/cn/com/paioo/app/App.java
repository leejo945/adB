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

	// ��Ŀ���õ����̳߳ء�����
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

	//��ʼ��ȫ�ֵ�ͼƬ������
	private void initImageLoader() {
		// ͼƬ���أ�����һ��Ĭ��ȫ������
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
		  
		.build();
		ImageLoader.getInstance().init(config);
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

}
