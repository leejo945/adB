package cn.com.paioo.app.entity;

/**
 * WebResult状态码
 * 
 * @author jinli.yuan
 * @since 13-12-5 下午4:49
 */
public final class ResultStatus {

	private ResultStatus() {
	}

	/**
	 * 成功
	 */
	public static final int SUCCESS = 100;

	/**
	 * 未知
	 */
	public static final int UNKNOWN = 101;

	/**
	 * 异常
	 */
	public static final int ERROR = 102;

	/**
	 * 传递的参数不正确
	 */
	public static final int PARAM_ERROR = 103;

	/**
	 * 未登录
	 */
	public static final int UNLOGIN = 104;

	/**
	 * 未找到记录
	 */
	public static final int NOT_FOUND = 105;

	/**
	 * 状态被锁定
	 */
	public static final int LOCKED = 106;

	/**
	 * 登录用户与实际操作用户，用户ID不一致(不同，不一致）
	 */
	public static final int UNIDENTIFIED = 107;

	/**
	 * 失败
	 */
	public static final int FAIL = 108;

	/**
	 * 重复提交，重复的请求
	 */
	public static final int REPEAT = 109;

}
