package cn.com.paioo.app.entity;

import java.io.Serializable;

/**
 * ��ʵ���࣬���������е�ʵ���ֶ�
 * 
 * @author Administrator
 * 
 */
public class User implements Serializable{

	// ---------------��ֵ��¼item�ֶ�-----------------------------//
	/**
	 * ��ֵ�·� 10 �� 11�µ�
	 */
	public String month;
	/**
	 * ��ֵ���� 10-1 11-11
	 */
	public String date;
	/**
	 * ����
	 */
	public String cardid;
	/**
	 * ���׶�
	 */
	public int money;
	/**
	 * ����״̬ �����׳ɹ�������ʧ�ܵȵȣ�
	 */
	public String status;
	// ---------------��ֵ��¼item�ֶ�-----------------------------//
//-----------------------
	public String userName;
	
	/**
	 * �̻�id
	 */
	public int advertiseid;
	
}
