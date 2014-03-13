package cn.com.paioo.app.entity;

/**
 * 广告产品
 * 
 * @author Administrator
 * 
 */
public class Product {
	/**
	 * id
	 */
	public int id;
	/**
	 * 产品名称
	 */
	public String name;
	/**
	 * 产品图片路径
	 */
	public String[] urls;
	/**
	 * /产品描述
	 */
	public String describe;
	/**
	 * 消费码
	 */
	public String xiaofeima;
	/**
	 * 用于二维码扫描结果界面提示(优惠券状态)
	 */
	public String couponStatus;
}
