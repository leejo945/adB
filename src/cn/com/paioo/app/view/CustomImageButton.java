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
 * ���Զ�����Ҫ���ڣ�һ���tab��������tab�������£�
 * 1.���������趨tabitem�е�ͼƬ�������ִ�С
 * 2.tab�ж���״̬.���磺����״̬�����״̬��ѡ��״̬��
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
