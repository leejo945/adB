 
package cn.com.paioo.app.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

 

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

 

import cn.com.paioo.app.App;

 
 

public class NetManager {
//	private static RequestQueue queue;
//	public void addRequest(Context context){
//		if(queue==null){
//			queue = Volley.newRequestQueue(context);
//		}
//		queue.add(null);
//	}
	   
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ���url
	 * @param p_url    ����url
	 * @param params   urlҪ���ݵĲ���
	 * @return
	 */
	private static String MakeURL(String p_url, Map<String, Object> params) {
		StringBuilder url = new StringBuilder(p_url);
		if(url.indexOf("?")<0)
			url.append('?');

		for(String name : params.keySet()){
			url.append('&');
			url.append(name);
			url.append('=');
			url.append(String.valueOf(params.get(name)));
			//����URLEncoder����
			//url.append(URLEncoder.encode(String.valueOf(params.get(name)), UTF_8));
		}

		return url.toString().replace("?&", "?");
	}
	
	 
	public static String _post(App appContext, String url, Map<String, Object> params){
		 String result = null;
		 
		 
		 return result;
	}
	
	
	
}
