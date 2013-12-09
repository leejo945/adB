package cn.com.paioo.app.entity;

import java.io.Serializable;

/**
 * HTTP响应状态码
 * 
 * @author jinli.yuan
 * @since 13-12-5 下午3:49
 */
public class WebResult implements Serializable {
	// ------------------------------ 属性 ------------------------------

	// 状态码
	private int status;

	// 提示信息
	private String message;

	// 回调函数
	private String callbackType;

	// --------------------------- 构造函数 ---------------------------

	public WebResult() {
	}

	public WebResult(int status, String message, String callbackType) {
		this.status = status;
		this.message = message;
		this.callbackType = callbackType;
	}

	// --------------------- GETTER / SETTER 方法 ---------------------

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

	// ------------------------ 重载方法 ------------------------

	@Override
	public String toString() {
		return "WebResult{" + "status=" + status + ", message='" + message
				+ '\'' + ", callbackType='" + callbackType + '\'' + '}';
	}
}
