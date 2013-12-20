package cn.com.paioo.app.entity;

/**
 * WebResult״̬��
 * 
 * @author jinli.yuan
 * @since 13-12-5 ����4:49
 */
public final class ResultStatus {

	private ResultStatus() {
	}

	/**
	 * �ɹ�
	 */
	public static final int SUCCESS = 100;

	/**
	 * δ֪
	 */
	public static final int UNKNOWN = 101;

	/**
	 * �쳣
	 */
	public static final int ERROR = 102;

	/**
	 * ���ݵĲ�������ȷ
	 */
	public static final int PARAM_ERROR = 103;

	/**
	 * δ��¼
	 */
	public static final int UNLOGIN = 104;

	/**
	 * δ�ҵ���¼
	 */
	public static final int NOT_FOUND = 105;

	/**
	 * ״̬������
	 */
	public static final int LOCKED = 106;

	/**
	 * ��¼�û���ʵ�ʲ����û����û�ID��һ��(��ͬ����һ�£�
	 */
	public static final int UNIDENTIFIED = 107;

	/**
	 * ʧ��
	 */
	public static final int FAIL = 108;

	/**
	 * �ظ��ύ���ظ�������
	 */
	public static final int REPEAT = 109;

}
