 
package cn.com.paioo.app.entity;

public class RechargeRecord {
	public static final int ITEM = 0;
	public static final int SECTION = 1;

	public final int type;
	public final String text;

	private boolean isSuccess;//交易成功还是失败
	
	public int sectionPosition;
	public int listPosition;

	public RechargeRecord(int type, String text) {
		this.type = type;
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
