package cn.com.paioo.app.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
 
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.com.paioo.app.util.LogManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.paioo.app.App;
import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.AppUpdateInfo;
import cn.com.paioo.app.entity.PresenterIncome;
import cn.com.paioo.app.entity.Product;
import cn.com.paioo.app.entity.Record;
import cn.com.paioo.app.entity.ResultStatus;
import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.entity.User;
import cn.com.paioo.app.entity.WebResult;
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.ImageManager;
import cn.com.paioo.app.util.StringManager;
import cn.com.paioo.app.util.ToastManager;

/**
 * 绝大部分请求的响应数据都是JsonObject
 * 
 * @author lee
 * 
 */
public class DataService   {
	private static final String tag = "DataService";
	private static RequestQueue mRequestQueue;
	/**
	 * 1、当服务端出错后，后台打印服务端错误信息，前台提示网络部给力 2、当网络不有信号，但是无法连接到internet的时候也是这种提示
	 */
	private static final String SERVER_ERROR_TOAST = "网络不给力，请稍后重试!";
	private static final String NET_UNABLE_TOAST = "网络不给力，请稍后重试...";

  

	/**
	 * 
	 * @param url
	 * @param context
	 * @param callBack
	 * @return
	 */
	public static  void login(final HashMap<String, Object> map,
			final Context context, final NetCallBack callBack) {
		if (!isNetworkConnected(context)) {
			callBack.netErrorCallBack(context, NET_UNABLE_TOAST);
			return;
		}
		JsonObjectRequest request = new JsonObjectRequest(makeUrl(
				ConstantManager.URL_LOGIN, map), null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject arg0) {
						LogManager.e(tag, "网络访问的结果--" + arg0.toString());
						if (isNormal(arg0)) {
							 LogManager.e(tag, "状态码是否正确呢");
							User user = new User();
							JSONObject data = arg0.optJSONObject("Data");
							user.advertiseid = data.optInt("advertiseid");
							callBack.netCallBack(user);
						} else {
							callBack.netErrorCallBack(context,
									arg0.optString("Error"));
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						LogManager.e(tag, "服务端出问题了----" + arg0.toString());
						callBack.netErrorCallBack(context, SERVER_ERROR_TOAST);
					}
				});

		addRequest(request, context);

	}

	/**
	 * 二维码验证
	 * 
	 * @param map
	 * @param context
	 * @param callBack
	 */
	public static  void sendQrInfo(final HashMap<String, Object> map,
			final Context context, final NetCallBack callBack) {
		if (!isNetworkConnected(context)) {
			callBack.netErrorCallBack(context, NET_UNABLE_TOAST);
			return;
		}

		JsonObjectRequest request = new JsonObjectRequest(makeUrl(
				ConstantManager.URL_SENDQRCODE, map), null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject arg0) {
						LogManager.e(tag, "-------------" + arg0.toString());
						// if (isNormal(arg0)) {
						// 验证成功了。(包括优惠券可用/不可用)

						Product p = new Product();
						p.couponStatus = arg0.optString("Error");
						String data = arg0.optString("Data");
						if (!StringManager.isEmpty(data)) {// 没有数据返回了
							try {
								JSONObject jsonObject = new JSONObject(data);
								p.describe = jsonObject.optString("title");
								p.xiaofeima = jsonObject.optString("qdcode");
								p.urls = new String[] { jsonObject
										.optString("imgurl") };
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						callBack.netCallBack(p);// success

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						LogManager.e(tag, "服务端出问题了----" + arg0.toString());
						callBack.netErrorCallBack(context, SERVER_ERROR_TOAST);
					}
				});
 
		addRequest(request, context);
	}

	

	/**
	 * 注册
	 * 
	 * @param url
	 * @param context
	 * @param callBack
	 */
	public static  void signUp(String url, final Context context,
			final NetCallBack callBack) {
		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {

						callBack.netCallBack(arg0);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						callBack.netErrorCallBack(context, arg0.toString());

					}
				});

		addRequest(request, context);
	}

	/**
	 * 增加附加的公司信息
	 * 
	 * @param url
	 * @param context
	 * @param callBack
	 */

	public static  void addExtraCompanyInfo(String url, final Context context,
			final NetCallBack callBack) {
		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {

						callBack.netCallBack(arg0);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						callBack.netErrorCallBack(context, arg0.toString());

					}
				});

		addRequest(request, context);
	}

	// ------------- 预览部分-----------start--------------
	/**
	 * 获得推送或者是桌面广告
	 * 
	 * @param url
	 * @param context
	 * @param callBack
	 */
	public static  void getPushOrDeskAd(String url, final Context context,
			final int pageNum, final NetCallBack callBack) {
		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject arg0) {
						// if(isNormal(arg0)){
						ArrayList<Product> list = new ArrayList<Product>();
						for (int i = 0; i < 10; i++) {
							list.add(new Product());
						}
						// 返回的是产品列表
						callBack.netCallBack(list);
						// }else{
						// callBack.netErrorCallBack(context, arg0.toString());
						// }

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						callBack.netErrorCallBack(context, arg0.toString());

					}
				});

		addRequest(request, context);
	}

	// ------------- 预览部分----------end-----------------

	public static void logout() {

	}

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

	/**
	 * app是否有更新 如果AppUpdateInfo 不为空， 就是要更新了
	 * @throws Exception
	 */
	public static  void getAppUpdateInfo(final Context context) {
		try {
			final PackageInfo info = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			JsonObjectRequest request = new JsonObjectRequest(
					ConstantManager.URL_CHECK_APP_VERSION, null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject arg0) {
							LogManager.e(tag, "数据" + arg0.toString());
							JSONObject obj = arg0.optJSONObject("Data");
							if (obj!=null) {
								int newVersion = obj.optInt("VersionCode");
								AppUpdateInfo appInfo = null;
								if (newVersion > info.versionCode) {// 当前版本和服务端的版本比较
									appInfo = new AppUpdateInfo();
									// "1、修改XXXXX\n2、修改XXX\n3、修改XXX\n4 、修改ＸＸＸ\n";//
									appInfo.description = obj
											.optString("Version_Note");// 升级描述
									// "http://app.paioo.com.cn/apk/pzm.apk"
									appInfo.apkurl = 
											obj.optString("VersionUrl"); // 升级的apk
								}
								((NetCallBack) context).netCallBack(appInfo);
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError arg0) {
							LogManager.e(tag, "检查升级异常。。。。请检查服务端.....");
							// callBack.netErrorCallBack(context,
							// arg0.toString());
						}
					});

			addRequest(request, context);

			 
		} catch (NameNotFoundException e) {
			 
			 
			e.printStackTrace();
		}

	}

	/*
	 * 更新apk
	 */

	public static void updateAPK(final String serverPath,
			final Context context, final Handler handler) {
		new Thread() {
			public void run() {
				File file = getFile(serverPath, context, handler);
				if (file != null) {
					install(file, context);
				} else {
					Looper.prepare();
					ToastManager.show(context,
							R.string.warn_toast_updateapp_exception);
					Looper.loop();
				}
			};
		}.start();

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
		// pd.setCancelable(false);
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			URL url = new URL(serverPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// conn.setConnectTimeout(AppConfig.TIMEOUT);
			if (conn.getResponseCode() == HttpStatus.SC_OK) {
				// 获得下载文件的总大小
				int maxLength = conn.getContentLength();
				// pd.setMax(maxLength);
				is = conn.getInputStream();

				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ "/"
						+ serverPath.substring(serverPath.lastIndexOf("/")));
				fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				int process = 0;
				// ----------------------
				NotificationManager mNotifyManager = (NotificationManager) context
						.getSystemService(Context.NOTIFICATION_SERVICE);
				Notification mNotification = new Notification();
				mNotification.icon = R.drawable.app_logo;
 				mNotification.setLatestEventInfo(context, "app更新", "正在下载 0%",
 						null);
 				mNotifyManager.notify(0, mNotification);

				// ----------------------
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("maxLength", maxLength);
				map.put("mNotifyManager", mNotifyManager);
				map.put("mNotification", mNotification);
				handler.sendMessage(handler.obtainMessage(
						ConstantManager.UPDATE_APP_INIT, map));
				ArrayList<Integer> list = new ArrayList<Integer>();
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					process += len;
					int newRatio = (int) (((double) process / (double) maxLength) * 100);
					if (!list.contains(newRatio)) {// 不在list的时候，
						Message msg = handler.obtainMessage(
								ConstantManager.UPDATE_APP_MSG, newRatio);
						handler.sendMessage(msg);
						list.add(newRatio);
					}

				}
				return file;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	// private static File getFile(String serverPath) {
	// FileOutputStream fos = null;
	// InputStream is = null;
	// File file = null;
	// try {
	// URL url = new URL(serverPath);
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// conn.setConnectTimeout(ConstantManager.REQUSE_TTIMEOUT);
	// if (conn.getResponseCode() == HttpStatus.SC_OK) {
	// is = conn.getInputStream();
	// if (Environment.getExternalStorageState().equals(
	// Environment.MEDIA_MOUNTED)) {
	// file = new File(Environment.getExternalStorageDirectory()
	// .getAbsolutePath()
	// + "/"
	// + serverPath.substring(serverPath.lastIndexOf("/")));
	// fos = new FileOutputStream(file);
	// byte[] buffer = new byte[1024];
	// int len = 0;
	// while ((len = is.read(buffer)) != -1) {
	// fos.write(buffer, 0, len);
	// LogManager.e("paioo", len + "");
	// }
	// } else {
	// // 没有sd卡
	// }
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (fos != null) {
	// try {
	// fos.flush();
	// fos.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// if (is != null) {
	// try {
	// is.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// return file;
	// }

	/**
	 * 下载app 完成后 自动的提示去安装
	 * 
	 * @param file
	 * @param context
	 */
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

				LogManager.e("paioo", activityInfo.loadLabel(packManager)
						+ "----" + activityInfo.name + "-------" + packName);

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
	/**
	 * 用于加载图片
	 * 
	 * @param iv
	 * @param url
	 */
	public static void loadImage(ImageView iv, String url) {
		ImageManager.getInstance().displayImage(url, iv,
				ImageManager.getImageOptions());

	}
	
	private static  void addRequest(Request request, Context context) {
		 if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		 }
		 LogManager.e(tag, "队列中请求个数"+mRequestQueue.getSequenceNumber());
		mRequestQueue.add(request);
	}

	/**
	 * 组装url
	 * 
	 * @return
	 */
	private static String makeUrl(String url, HashMap<String, Object> map) {
		StringBuffer sb = null;
		if (map != null) {
			sb = new StringBuffer(url + "?");
			Set<String> keys = map.keySet();
			for (String key : keys) {
				sb.append(key + "=" + map.get(key) + "&");
			}
		}
		LogManager.e(tag, "网络访问url----" + sb == null ? url : sb.toString());
		return sb == null ? "" : sb.toString();
	}

	/**
	 * 判断返回的json数据是否正常，只有为ResultStatus.SUCCESS的时候才为返回的正常的数据
	 * 
	 * @param arg0
	 * @return
	 */
	private static boolean isNormal(JSONObject arg0) {

		return arg0.optInt("Result") == ResultStatus.SUCCESS;

	}
	
	

	/**
	 * 检测网络是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 获取当前网络类型(手机只有两种网络wifi，mobile;mobile就是2g/3g)
	 * 
	 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
	 */
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_MOBILE_CMWAP = 0x02;
	public static final int NETTYPE_MOBILE_CMNET = 0x03;

	public static int getNetworkType(Context context) {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (!StringManager.isEmpty(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_MOBILE_CMNET;
				} else {
					netType = NETTYPE_MOBILE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}

 

	
	
 

	//-----------------test---------------------------------
	public static ArrayList<String> urls = new ArrayList<String>();
	private static final int TIMEOUT = 5000;
	public static String postDataByGet(String url, Map<String, Object> dataMap,
			Context context) {
		String result = "";
		// 防止过度请求
		String tempUrl = url.substring(0, url.indexOf(".do"));
		// 首先判断网络
		if (!DataService.isNetworkConnected(context)) {
			result = "{\"Result\":1,\"Error\":\"网络异常，请稍后重试...\",\"Data\":\"\"}";
		} else if (urls.contains(tempUrl)) {// 请求过来的时候，看看上次的请求是否返回.为ture
			result = "{\"Result\":1,\"Error\":\"请不要重复请求...\",\"Data\":\"\"}";
		} else {// 开始正式访问
			// 要传递的数据
			StringBuilder query = new StringBuilder();
			String urlStr = "";
			if (dataMap != null) {
				Set<Entry<String, Object>> setAll = dataMap.entrySet();
				Iterator<Entry<String, Object>> itAll = setAll.iterator();
				while (itAll.hasNext()) {
					Map.Entry<String, Object> entry = (Map.Entry<String, Object>) itAll
							.next();
					String key = entry.getKey().toString();
					String value = entry.getValue().toString();
					query.append(key).append("=").append(value).append("&");
				}
				String theQuery = query.substring(0, query.lastIndexOf("&"));
				// 上传数据格式
				urlStr = url + "?" + theQuery;

			} else {
				urlStr = url;
			}

			// urlStr 为get请求的时候拼接起来的字符串
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// 请求超时
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT,  TIMEOUT);
			// 响应超时
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT,  TIMEOUT);
			HttpGet httpGet = new HttpGet(urlStr);
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					result = EntityUtils.toString(entity);
				} else {
					// 连接服务端失败后(除了返回200状态码，都是服务端连接失败)
					result = "{\"Result\":1,\"Error\":\"服务端连接异常，请稍后重试...\",\"Data\":\"\"}";
				}
			} catch (ClientProtocolException e) {

				e.printStackTrace();
				result = "{\"Result\":1,\"Error\":\"服务端连接异常，请稍后重试...\",\"Data\":\"\"}";
			} catch (ParseException e) {
				e.printStackTrace();
				result = "{\"Result\":1,\"Error\":\"解析数据失败，请稍后重试...\",\"Data\":\"\"}";
			} catch (IOException e) {
				e.printStackTrace();
				result = "{\"Result\":1,\"Error\":\"数据获取异常，请稍后重试...\",\"Data\":\"\"}";
			}
			// 一个请求完毕,无论说明结果，将这个请求的链接删除
			urls.remove(tempUrl);
		}
		// 到这里后result 就不可能为空字符串了

		return result;
	}
	
	
	
}
