package cn.com.paioo.app.view;

 
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
/**
 * 该自定类主要用于，一般的tab导航。该tab功能如下：
 * 1.可以自由设定tabitem中的图片或者文字大小
 * 2.tab有多种状态.比如：正常状态，点击状态，选中状态等
 * @author lee
 *
 */
public class CustomImageButton extends RadioButton {
	String tag = "CustomImageButton";

	public CustomImageButton(Context context) {
		super(context);
	}

	public CustomImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	 
	}

	 

	 

}
