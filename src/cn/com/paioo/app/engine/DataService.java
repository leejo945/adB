package cn.com.paioo.app.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
 

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import cn.com.paioo.app.App;
import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.Constant;

public class DataService {
	/**
	 * app是否有更新 如果AppUpdateInfo 不为空， 就是要更新了
	 */
	public static AppUpdateInfo getAppUpdateInfo(Context context) {
		AppUpdateInfo appInfo = null;
		try {
			String data = "";// HttpUtil.postDataByGet(AppConfig.URL_APP_UPDATE,
			// null, context);
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			// app当前版本
			String oldVersion = info.versionName;

			// JSONObject obj = new JSONObject(data).optJSONObject("Data");
			String newVersion = "2.1";// obj.optInt("version") + "";
			if (!newVersion.trim().equals(oldVersion.trim())) {// 版本要升级
				appInfo = new AppUpdateInfo();
				appInfo.version = newVersion;
				appInfo.description = "1、修改XXXXX\n2、修改XXX\n3、修改XXX\n4 、修改ＸＸＸ\n";// obj.optString("log");//升级描述
				appInfo.apkurl = "http://fwh.paioo.com.cn/fuservice/fuwenhua.apk";// obj.getString("describe");//升级的apk
																					// url地址
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return appInfo;
	}

	/*
	 * 更新apk
	 */

	public static void updateAPK(final Context context,
			final String serverPath, final Handler handler) {

		App.pool.addTask(new Thread() {
			public void run() {
				File file = getFile(serverPath, context, handler);
				// if (file != null) {
				// install(file, context);
				// } else {
				// Looper.prepare();
				// Toast.makeText(context,
				// R.string.warn_dialog_update_exception, 0).show();
				// Looper.loop();
				// }
			};
		});
	}

	/**
	 * 下载服务器中的新版本app
	 * 
	 * @param serverPath
	 *            下载服务器中最新app的地址
	 * @param context
	 * @return
	 */
	private static File getFile(String serverPath, Context context,
			Handler handler) {
		FileOutputStream fos = null;
		InputStream is = null;
		File file = null;
		try {
			URL url = new URL(serverPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(Constant.REQUSE_TTIMEOUT);
			if (conn.getResponseCode() == HttpStatus.SC_OK) {
				is = conn.getInputStream();
				 file = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ "/"
						+ serverPath.substring(serverPath.lastIndexOf("/")));
				fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				return file;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 获得分享到的app列表
	 * 
	 * @param context
	 * @return 如果前端已经指定分享到固定的app。有如下情况; 1.指定的app并没有安装在当前设备中。只要分享列表中
	 *         2.指定的app一个都没有
	 * 
	 */
	public static ArrayList<ShareInfo> getShareInfoList(Context context) {
		Intent it = new Intent(Intent.ACTION_SEND);
		it.setType("text/plain");
		PackageManager packManager = context.getPackageManager();
		List<ResolveInfo> resInfos = packManager.queryIntentActivities(it, 0);
		ArrayList<ShareInfo> list = new ArrayList<ShareInfo>();
		if (!resInfos.isEmpty()) {
			ShareInfo shareInfo = null;
			for (ResolveInfo resInfo : resInfos) {
				ActivityInfo activityInfo = resInfo.activityInfo;
				String packName = activityInfo.packageName;
				// if (packName.contains("sina") ||
				// packName.contains("tencent")||packName.contains("qihoo")
				// ||packName.contains("")) {
				shareInfo = new ShareInfo();
				shareInfo.packName = packName;
				shareInfo.icon = activityInfo.loadIcon(packManager);
				shareInfo.name = activityInfo.loadLabel(packManager).toString();
				list.add(shareInfo);
				// }
			}
		} else {
			// 没有社交应用分享,如果本身已经
		}

		return list;

	}

	public static ArrayList<User> getRechargeRecordList() {
		return null;
	}

}
