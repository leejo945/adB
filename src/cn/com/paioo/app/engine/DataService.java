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
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.PresenterIncome;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.entity.Record;
import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.util.Constant;
import cn.com.paioo.app.util.MyToast;

public class DataService {
	
	
	
	
	
	public static ArrayList<PresenterIncome> getPresenterIncomeList(int pageNum) {
		ArrayList<PresenterIncome> list = new ArrayList<PresenterIncome>();
		for (int i = 0; i < 10; i++) {
			list.add(new PresenterIncome());
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 记录，包括广告消费，充值，转账等记录
	 * 
	 * @param pageNum
	 * @return
	 */
	public static ArrayList<Record> getRecordList(int pageNum) {
		ArrayList<Record> list = new ArrayList<Record>();
		for (int i = 0; i < 10; i++) {
			list.add(new Record());
		}
		return list;
	}

	public static ArrayList<Product> getPushOrDeskAdList(int pageNum) {
		ArrayList<Product> list = new ArrayList<Product>();
		for (int i = 0; i < 10; i++) {
			list.add(new Product());
		}
		return list;
	}

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

	public static void updateAPK(final String serverPath, final Context context) {

		App.pool.addTask(new Thread() {
			public void run() {
				File file = getFile(serverPath);
				if (file != null) {
					install(file, context);
				} else {
					Looper.prepare();
					MyToast.show(context,
							R.string.warn_toast_updateapp_exception);
					Looper.loop();
				}
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
	private static File getFile(String serverPath) {
		FileOutputStream fos = null;
		InputStream is = null;
		File file = null;
		try {
			URL url = new URL(serverPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(Constant.REQUSE_TTIMEOUT);
			if (conn.getResponseCode() == HttpStatus.SC_OK) {
				is = conn.getInputStream();
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					file = new File(Environment.getExternalStorageDirectory()
							.getAbsolutePath()
							+ "/"
							+ serverPath.substring(serverPath.lastIndexOf("/")));
					fos = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
						Log.e("paioo", len + "");
					}
				} else {
					// 没有sd卡
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return file;
	}

	// 下载app 完成后 自动的提示去安装
	private static void install(File file, Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
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
		// 新浪微博，朋友圈，微信好友，QQ好友，腾讯微博，QQ空间，发送短信，复制链接
		Intent it = new Intent(Intent.ACTION_SEND);
		it.setType("text/plain");
		PackageManager packManager = context.getPackageManager();
		List<ResolveInfo> resInfos = packManager.queryIntentActivities(it, 0);
		ArrayList<ShareInfo> list = new ArrayList<ShareInfo>();

		ShareInfo sina = new ShareInfo(R.drawable.share_sina, "新浪微博", "");
		ShareInfo friends = new ShareInfo(R.drawable.share_friends, "朋友圈", "");
		ShareInfo weixin = new ShareInfo(R.drawable.share_weixin, "微信好友", "");
		ShareInfo qq = new ShareInfo(R.drawable.share_qq, "QQ好友", "");
		ShareInfo txweibo = new ShareInfo(R.drawable.share_txweibo, "腾讯微博", "");
		ShareInfo qzone = new ShareInfo(R.drawable.share_qzone, "QQ空间", "");
		ShareInfo sms = new ShareInfo(R.drawable.share_sms, "发送短信", "");
		ShareInfo link = new ShareInfo(R.drawable.recommend_copy_link, "复制链接",
				"");
		if (!resInfos.isEmpty()) {
			for (ResolveInfo resInfo : resInfos) {
				ActivityInfo activityInfo = resInfo.activityInfo;
				String packName = activityInfo.packageName;

				Log.e("paioo", activityInfo.loadLabel(packManager) + "----"
						+ activityInfo.name + "-------" + packName);

				if (packName.contains("sina")) {// 如果在手机中有该app，那么就将该包加入，如果没有点击的时候会给一个提示。请求安装
                      sina.packName = packName;
				}
				if (packName.contains("tencent.mm")) {
					friends.packName = packName;
				}
				if (packName.contains("tencent.mm")) {
					weixin.packName = packName;
				}
				if (packName.contains("mobileqq")) {
					qq.packName = packName;
				}
				if (packName.contains("tencent.WBlog")) {
					txweibo.packName = packName;
				}
				if (packName.contains("qzone")) {
					qzone.packName = packName;
				}
				if (packName.contains("mms")) {
					sms.packName = packName;
				}
				 
			}
		}

		list.add(sina);
		list.add(friends);
		list.add(weixin);
		list.add(qq);
		list.add(txweibo);
		list.add(qzone);
		list.add(sms);
		list.add(link);
		
		return list;

	}
}
