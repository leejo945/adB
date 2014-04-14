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
 * ���󲿷��������Ӧ���ݶ���JsonObject
 * 
 * @author lee
 * 
 */
public class DataService   {
	private static final String tag = "DataService";
	private static RequestQueue mRequestQueue;
	/**
	 * 1��������˳���󣬺�̨��ӡ����˴�����Ϣ��ǰ̨��ʾ���粿���� 2�������粻���źţ������޷����ӵ�internet��ʱ��Ҳ��������ʾ
	 */
	private static final String SERVER_ERROR_TOAST = "���粻���������Ժ�����!";
	private static final String NET_UNABLE_TOAST = "���粻���������Ժ�����...";

  

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
						LogManager.e(tag, "������ʵĽ��--" + arg0.toString());
						if (isNormal(arg0)) {
							 LogManager.e(tag, "״̬���Ƿ���ȷ��");
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
						LogManager.e(tag, "����˳�������----" + arg0.toString());
						callBack.netErrorCallBack(context, SERVER_ERROR_TOAST);
					}
				});

		addRequest(request, context);

	}

	/**
	 * ��ά����֤
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
						// ��֤�ɹ��ˡ�(�����Ż�ȯ����/������)

						Product p = new Product();
						p.couponStatus = arg0.optString("Error");
						String data = arg0.optString("Data");
						if (!StringManager.isEmpty(data)) {// û�����ݷ�����
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
						LogManager.e(tag, "����˳�������----" + arg0.toString());
						callBack.netErrorCallBack(context, SERVER_ERROR_TOAST);
					}
				});
 
		addRequest(request, context);
	}

	

	/**
	 * ע��
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
	 * ���Ӹ��ӵĹ�˾��Ϣ
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

	// ------------- Ԥ������-----------start--------------
	/**
	 * ������ͻ�����������
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
						// ���ص��ǲ�Ʒ�б�
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

	// ------------- Ԥ������----------end-----------------

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
	 * ��¼������������ѣ���ֵ��ת�˵ȼ�¼
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
	 * app�Ƿ��и��� ���AppUpdateInfo ��Ϊ�գ� ����Ҫ������
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
							LogManager.e(tag, "����" + arg0.toString());
							JSONObject obj = arg0.optJSONObject("Data");
							if (obj!=null) {
								int newVersion = obj.optInt("VersionCode");
								AppUpdateInfo appInfo = null;
								if (newVersion > info.versionCode) {// ��ǰ�汾�ͷ���˵İ汾�Ƚ�
									appInfo = new AppUpdateInfo();
									// "1���޸�XXXXX\n2���޸�XXX\n3���޸�XXX\n4 ���޸ģأأ�\n";//
									appInfo.description = obj
											.optString("Version_Note");// ��������
									// "http://app.paioo.com.cn/apk/pzm.apk"
									appInfo.apkurl = 
											obj.optString("VersionUrl"); // ������apk
								}
								((NetCallBack) context).netCallBack(appInfo);
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError arg0) {
							LogManager.e(tag, "��������쳣����������������.....");
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
	 * ����apk
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
	 * ���ط������е��°汾app
	 * 
	 * @param serverPath
	 *            ���ط�����������app�ĵ�ַ
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
				// ��������ļ����ܴ�С
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
 				mNotification.setLatestEventInfo(context, "app����", "�������� 0%",
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
					if (!list.contains(newRatio)) {// ����list��ʱ��
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
	// // û��sd��
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
	 * ����app ��ɺ� �Զ�����ʾȥ��װ
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
	 * ��÷�����app�б�
	 * 
	 * @param context
	 * @return ���ǰ���Ѿ�ָ�������̶���app�����������; 1.ָ����app��û�а�װ�ڵ�ǰ�豸�С�ֻҪ�����б���
	 *         2.ָ����appһ����û��
	 * 
	 */
	public static ArrayList<ShareInfo> getShareInfoList(Context context) {
		// ����΢��������Ȧ��΢�ź��ѣ�QQ���ѣ���Ѷ΢����QQ�ռ䣬���Ͷ��ţ���������
		Intent it = new Intent(Intent.ACTION_SEND);
		it.setType("text/plain");
		PackageManager packManager = context.getPackageManager();
		List<ResolveInfo> resInfos = packManager.queryIntentActivities(it, 0);
		ArrayList<ShareInfo> list = new ArrayList<ShareInfo>();

		ShareInfo sina = new ShareInfo(R.drawable.share_sina, "����΢��", "");
		ShareInfo friends = new ShareInfo(R.drawable.share_friends, "����Ȧ", "");
		ShareInfo weixin = new ShareInfo(R.drawable.share_weixin, "΢�ź���", "");
		ShareInfo qq = new ShareInfo(R.drawable.share_qq, "QQ����", "");
		ShareInfo txweibo = new ShareInfo(R.drawable.share_txweibo, "��Ѷ΢��", "");
		ShareInfo qzone = new ShareInfo(R.drawable.share_qzone, "QQ�ռ�", "");
		ShareInfo sms = new ShareInfo(R.drawable.share_sms, "���Ͷ���", "");
		ShareInfo link = new ShareInfo(R.drawable.recommend_copy_link, "��������",
				"");
		if (!resInfos.isEmpty()) {
			for (ResolveInfo resInfo : resInfos) {
				ActivityInfo activityInfo = resInfo.activityInfo;
				String packName = activityInfo.packageName;

				LogManager.e("paioo", activityInfo.loadLabel(packManager)
						+ "----" + activityInfo.name + "-------" + packName);

				if (packName.contains("sina")) {// ������ֻ����и�app����ô�ͽ��ð����룬���û�е����ʱ����һ����ʾ������װ
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
	 * ���ڼ���ͼƬ
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
		 LogManager.e(tag, "�������������"+mRequestQueue.getSequenceNumber());
		mRequestQueue.add(request);
	}

	/**
	 * ��װurl
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
		LogManager.e(tag, "�������url----" + sb == null ? url : sb.toString());
		return sb == null ? "" : sb.toString();
	}

	/**
	 * �жϷ��ص�json�����Ƿ�������ֻ��ΪResultStatus.SUCCESS��ʱ���Ϊ���ص�����������
	 * 
	 * @param arg0
	 * @return
	 */
	private static boolean isNormal(JSONObject arg0) {

		return arg0.optInt("Result") == ResultStatus.SUCCESS;

	}
	
	

	/**
	 * ��������Ƿ����
	 * 
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * ��ȡ��ǰ��������(�ֻ�ֻ����������wifi��mobile;mobile����2g/3g)
	 * 
	 * @return 0��û������ 1��WIFI���� 2��WAP���� 3��NET����
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
		// ��ֹ��������
		String tempUrl = url.substring(0, url.indexOf(".do"));
		// �����ж�����
		if (!DataService.isNetworkConnected(context)) {
			result = "{\"Result\":1,\"Error\":\"�����쳣�����Ժ�����...\",\"Data\":\"\"}";
		} else if (urls.contains(tempUrl)) {// ���������ʱ�򣬿����ϴε������Ƿ񷵻�.Ϊture
			result = "{\"Result\":1,\"Error\":\"�벻Ҫ�ظ�����...\",\"Data\":\"\"}";
		} else {// ��ʼ��ʽ����
			// Ҫ���ݵ�����
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
				// �ϴ����ݸ�ʽ
				urlStr = url + "?" + theQuery;

			} else {
				urlStr = url;
			}

			// urlStr Ϊget�����ʱ��ƴ���������ַ���
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// ����ʱ
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT,  TIMEOUT);
			// ��Ӧ��ʱ
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT,  TIMEOUT);
			HttpGet httpGet = new HttpGet(urlStr);
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					result = EntityUtils.toString(entity);
				} else {
					// ���ӷ����ʧ�ܺ�(���˷���200״̬�룬���Ƿ��������ʧ��)
					result = "{\"Result\":1,\"Error\":\"����������쳣�����Ժ�����...\",\"Data\":\"\"}";
				}
			} catch (ClientProtocolException e) {

				e.printStackTrace();
				result = "{\"Result\":1,\"Error\":\"����������쳣�����Ժ�����...\",\"Data\":\"\"}";
			} catch (ParseException e) {
				e.printStackTrace();
				result = "{\"Result\":1,\"Error\":\"��������ʧ�ܣ����Ժ�����...\",\"Data\":\"\"}";
			} catch (IOException e) {
				e.printStackTrace();
				result = "{\"Result\":1,\"Error\":\"���ݻ�ȡ�쳣�����Ժ�����...\",\"Data\":\"\"}";
			}
			// һ���������,����˵���������������������ɾ��
			urls.remove(tempUrl);
		}
		// �������result �Ͳ�����Ϊ���ַ�����

		return result;
	}
	
	
	
}
