 
package cn.com.paioo.app.engine;

import android.content.Context;

/**
 * 用于网络访问的结果回调处理
 * @author lee
 *
 */
public interface NetCallBack {
	/**
	 * 返回类型有很多种：string,jsonarray,jsonobject,bitmap等
	 * @param response
	 */
    void netCallBack(Object response);
    /**
     * 当网络错误以后回调函数，对于没有正常获取到数据的情况，前端应该统一提示“网络不给力，....”
     */
    void netErrorCallBack(Context context,String errorReason);
}
