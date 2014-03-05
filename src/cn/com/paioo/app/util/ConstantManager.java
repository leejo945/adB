 
package cn.com.paioo.app.util;

public class ConstantManager {
	//-------------网络访问url-------------start-----------------
	public static final String URL_LOGIN = "http://192.168.13.244:8080/adservice/member/locate/addMemLocate.do";
	
	
	
	
	
	//-------------网络访问url-------------end-----------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * splash 
	 */
	public static final int SHOW = 0;
	public static final int SHOW_LOGO_TIME = 2000;//spalsh 展示logo多少时间后开始判断
	public static final int FROM_SPLASH = 1;
	public static final String SP_USER_NAME = "username";
	public static final String SP_USER_PWD = "userpwd";
	
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
	
	
	
	
	
	
	
	
	//handler中用到的what
	public static final int PUSH_FILL_DATA_SUCCESS = 0;
	public static final int PUSH_FILL_DATA_FAIL = 1;
	public static final int PUSH_FILL_DATA_EXCEPTION = 2;
	public static final int DESK_FILL_DATA_SUCCESS = 3;
	public static final int DESK_FILL_DATA_FAIL = 4;
	public static final int DESK_FILL_DATA_EXCEPTION = 5;
	
	public static final int FILL_DATA_SUCCESS = 6;
	public static final int FILL_DATA_FAIL = 7;
	

}
