package cn.com.paioo.app.entity;

import java.io.Serializable;

/**
 * HTTP��Ӧ״̬��
 * 
 * @author jinli.yuan
 * @since 13-12-5 ����3:49
 */
public class WebResult implements Serializable {
	// ------------------------------ ���� ------------------------------

	// ״̬��
	private int status;

	// ��ʾ��Ϣ
	private String message;

	// �ص�����
	private String callbackType;

	// --------------------------- ���캯�� ---------------------------

	public WebResult() {
	}

	public WebResult(int status, String message, String callbackType) {
		this.status = status;
		this.message = message;
		this.callbackType = callbackType;
	}

	// --------------------- GETTER / SETTER ���� ---------------------

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// ------------------------ ���ط��� ------------------------

	@Override
	public String toString() {
		return "WebResult{" + "status=" + status + ", message='" + message
				+ '\'' + ", callbackType='" + callbackType + '\'' + '}';
	}
}
