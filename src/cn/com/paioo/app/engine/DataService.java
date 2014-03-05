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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
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
import cn.com.paioo.app.util.LogManager;
import android.util.Log;
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
import cn.com.paioo.app.util.ConstantManager;
import cn.com.paioo.app.util.ToastManager;

/**
 * ���󲿷��������Ӧ���ݶ���JsonObject
 * 
 * @author lee
 * 
 */
public class DataService {
	private static RequestQueue mRequestQueue;

	private static void addRequest(Request request, Context context) {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}
		mRequestQueue.add(request);
	}

	/**
	 * post���ݷ�ʽ�У�ʵ�岿�ִ��ݵ�����
	 * 
	 * @return
	 */
	private static JSONObject setBody() {
		JSONObject body = new JSONObject();
		return body;
	}

	/**
	 * �жϷ��ص�json�����Ƿ�������ֻ��ΪResultStatus.SUCCESS��ʱ���Ϊ���ص�����������
	 * 
	 * @param arg0
	 * @return
	 */
	private static boolean isNormal(JSONObject arg0) {

		return arg0.optInt("") == ResultStatus.SUCCESS;

	}

	/**
	 * 
	 * @param url
	 * @param context
	 * @param callBack
	 * @return
	 */
	public static void login(String url, final Context context,
			final NetCallBack callBack) {

		JsonObjectRequest request = new JsonObjectRequest(url, setBody(),
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {
						// ����ص������������˵�����Ѿ���������ʵ��JSONObject�����ˣ�������ʵ�ˡ����ǲ�һ��������ȷ�����ݡ�
						// ��Ҫͨ�����ص�״̬��ȥȷ���Ƿ�Ҫȥ����ȫ��

						User user = null;
						if (isNormal(arg0)) {
							user = new User();
							// ����ΪUser
							arg0.optString("");
							// ����user��ȥ
							callBack.netCallBack(user);
						} else {
							callBack.netErrorCallBack(context, "����״̬����");
						}

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
	 * ע��
	 * 
	 * @param url
	 * @param context
	 * @param callBack
	 */
	public static void signUp(String url, final Context context,
			final NetCallBack callBack) {
		JsonObjectRequest request = new JsonObjectRequest(url, setBody(),
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

	public static void addExtraCompanyInfo(String url, final Context context,
			final NetCallBack callBack) {
		JsonObjectRequest request = new JsonObjectRequest(url, setBody(),
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
	 * @param url
	 * @param context
	 * @param callBack
	 */
	public static void getPushOrDeskAd(String url, final Context context,final int pageNum,
			final NetCallBack callBack){
		    JsonObjectRequest request = new JsonObjectRequest(url, setBody(),
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject arg0) {
					//	if(isNormal(arg0)){
							ArrayList<Product> list = new ArrayList<Product>();
							for (int i = 0; i < 10; i++) {
								list.add(new Product());
							}
							//���ص��ǲ�Ʒ�б�
							callBack.netCallBack(list);
						//}else{
						//	callBack.netErrorCallBack(context, arg0.toString());
						//}
						
						
				
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
	 */
	public static AppUpdateInfo getAppUpdateInfo(Context context) {
		AppUpdateInfo appInfo = null;
		try {
			String data = "";// HttpUtil.postDataByGet(AppConfig.URL_APP_UPDATE,
			// null, context);
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			// app��ǰ�汾
			String oldVersion = info.versionName;

			// JSONObject obj = new JSONObject(data).optJSONObject("Data");
			String newVersion = "2.1";// obj.optInt("version") + "";
			if (!newVersion.trim().equals(oldVersion.trim())) {// �汾Ҫ����
				appInfo = new AppUpdateInfo();
				appInfo.version = newVersion;
				appInfo.description = "1���޸�XXXXX\n2���޸�XXX\n3���޸�XXX\n4 ���޸ģأأ�\n";// obj.optString("log");//��������
				appInfo.apkurl = "http://fwh.paioo.com.cn/fuservice/fuwenhua.apk";// obj.getString("describe");//������apk
																					// url��ַ
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return appInfo;
	}

	/*
	 * ����apk
	 */

	public static void updateAPK(final String serverPath, final Context context) {

		// App.pool.addTask(new Thread() {
		// public void run() {
		// File file = getFile(serverPath);
		// if (file != null) {
		// install(file, context);
		// } else {
		// Looper.prepare();
		// MyToast.show(context,
		// R.string.warn_toast_updateapp_exception);
		// Looper.loop();
		// }
		// };
		// });
	}

	/**
	 * ���ط������е��°汾app
	 * 
	 * @param serverPath
	 *            ���ط�����������app�ĵ�ַ
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
			conn.setConnectTimeout(ConstantManager.REQUSE_TTIMEOUT);
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
						LogManager.e("paioo", len + "");
					}
				} else {
					// û��sd��
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

	// ����app ��ɺ� �Զ�����ʾȥ��װ
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
}
