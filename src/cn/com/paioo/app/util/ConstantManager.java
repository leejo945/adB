 
package cn.com.paioo.app.util;

public class ConstantManager {
	//-------------网络访问url-------------start-----------------
	/**
	 * 基础ip
	 */
	//http://113.105.222.72/adbizappservice/
	public static final String URL_BASE = "http://113.105.222.72/";
	 //adbizappservice
	public static final String URL_SERVICE = "adbizappservice/";
	
	/**
	 * 登录
	 * 
	 * http://192.168.13.5:8080/adbizservice/admin/security/login.do
	 */
	public static final String URL_LOGIN = URL_BASE+URL_SERVICE+"admin/security/login.do";
	/**
	 * 发送扫描二维码扫描结果
	 */ 
	 
	public static final String URL_SENDQRCODE = URL_BASE+URL_SERVICE+"admin/qrcode/QrCode.do";
	 /**
	  * 检查app是否需要更新
	  * "http://192.168.13.5:8080/adbizservice/admin/update/getApp.do"
	  */
	public static final String URL_CHECK_APP_VERSION = URL_BASE+URL_SERVICE+"admin/update/getApp.do";//URL_BASE+URL_SERVICE;
	
	
	
	//-------------网络访问url-------------end-----------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * splash 
	 */
	public static final int SHOW = 0;
	public static final int SHOW_LOGO_TIME = 2000;//spalsh 展示logo多少时间后开始判断
	public static final int FROM_SPLASH = 1;
	public static final String SP_USER_NAME = "username";
	public static final String SP_USER_PWD = "userpwd";
	public static final String SP_USER_ADVERTISEID = "useradvertiseid";
     /**
      * MainActivity中界面切换的索引
      */
	public static final int SLIDEMENU_INDEX_HOME = 0;
	public static final int SLIDEMENU_INDEX_FINANCE = 1;
	public static final int SLIDEMENU_INDEX_PREVIEW = 2;
	public static final int SLIDEMENU_INDEX_RECHARGE = 3;
	public static final int SLIDEMENU_INDEX_FRIENDS = 4;
	public static final int SLIDEMENU_INDEX_TRANSFER = 5;
	public static final int SLIDEMENU_INDEX_TICKET = 6;
	public static final int SLIDEMENU_INDEX_SETUP = 7;
	
	
	
	
	
	
	//网络连接请求超时时间
	public static final int REQUSE_TTIMEOUT = 3000;
	//网络连接响应超时时间
	public static final int RESPONSE_TIMEOUT = 3000;
	
	
	//-------intent 传递--------------
	public static final String ZXING_SCANRESULT = "scanresult";
	
	//------------------------------
	
	
	
	
	
	/**
	 * handler中用到的what
	 */
	public static final int PUSH_FILL_DATA_SUCCESS = 0;
	public static final int PUSH_FILL_DATA_FAIL = 1;
	public static final int PUSH_FILL_DATA_EXCEPTION = 2;
	public static final int DESK_FILL_DATA_SUCCESS = 3;
	public static final int DESK_FILL_DATA_FAIL = 4;
	public static final int DESK_FILL_DATA_EXCEPTION = 5;
	
	public static final int FILL_DATA_SUCCESS = 6;
	public static final int FILL_DATA_FAIL = 7;
	public static final int UPDATE_APP_INIT  = 8;
	public static final int UPDATE_APP_MSG  = 9;
}
