package cn.com.paioo.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TrendView extends View {
     private static final int spac = 30;
	public TrendView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public TrendView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public TrendView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		Log.e("view", "目前画布大小"+canvas.getWidth()+"--"+canvas.getHeight());
		
		
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setColor(Color.rgb(187, 187, 187));
		
		
		paint.setAntiAlias(true);
		paint.setDither(true);
		//paint.setStyle(Paint.s)
		
		
		canvas.drawLine(20,  50, 600,  50, paint);
		canvas.drawLine(20,  70, 600,  70, paint);
		canvas.drawLine(20,  90, 600,  90, paint);
		canvas.drawLine(20,  110, 600,  110, paint);
		canvas.drawLine(20,  130, 600,  130, paint);
		canvas.drawLine(20,  150, 600,  150, paint);
//		
//		
		canvas.drawLine(20,  50, 20,  500, paint);
		canvas.drawLine(50,  50, 50,  500, paint);
		canvas.drawLine(80,  50, 80,  500, paint);
		canvas.drawLine(110,  50, 110,  500, paint);
		canvas.drawLine(140,  50, 140,  500, paint);
		canvas.drawLine(170,  50, 170,  500, paint);
		
		
		super.onDraw(canvas);
	}
	
	
}
