package cn.com.paioo.app.entity;

/**
 * 该实体类，包含了所有的实体字段
 * 
 * @author Administrator
 * 
 */
public class User {

	// ---------------充值记录item字段-----------------------------//
	/**
	 * 充值月份 10 月 11月等
	 */
	public String month;
	/**
	 * 充值日期 10-1 11-11
	 */
	public String date;
	/**
	 * 卡号
	 */
	public String cardid;
	/**
	 * 交易额
	 */
	public int money;
	/**
	 * 交易状态 （交易成功，交易失败等等）
	 */
	public String status;
	// ---------------充值记录item字段-----------------------------//

}
