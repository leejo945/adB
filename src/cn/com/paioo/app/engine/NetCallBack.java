 
package cn.com.paioo.app.engine;

import android.content.Context;

/**
 * ����������ʵĽ���ص�����
 * @author lee
 *
 */
public interface NetCallBack {
	/**
	 * ���������кܶ��֣�string,jsonarray,jsonobject,bitmap��
	 * @param response
	 */
    void netCallBack(Object response);
    /**
     * ����������Ժ�ص�����������û��������ȡ�����ݵ������ǰ��Ӧ��ͳһ��ʾ�����粻������....��
     */
    void netErrorCallBack(Context context,String errorReason);
}
