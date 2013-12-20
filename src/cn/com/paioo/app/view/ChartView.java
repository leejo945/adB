package cn.com.paioo.app.view;

import java.util.ArrayList;

import cn.com.paioo.app.entity.ChartBean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class ChartView extends View{
	private ArrayList<ChartBean> listChart = null; //数据列表
	private Paint paint; //笔画对象
	private float screenW, screenH; //屏幕宽高
	private float thirdH; //自定义view的高度，为屏幕三分之一高
	private float unitW, unitH; //走势图每个单元格的宽高
	private float titleSize, priceSize; //字体大小

	public ChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ChartView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		paint = new Paint();
		
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		screenW = dm.widthPixels;
		screenH = dm.heightPixels;
		
		thirdH = screenH/3;
		unitW = screenW/8;
		unitH = thirdH/6;
		titleSize = unitW/3;
		priceSize = unitW/4;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		/**画单元格*/
		paint.setColor(Color.argb(255, 187, 187, 187));
		paint.setStyle(Style.FILL); //设置笔画未实心
		paint.setStrokeWidth(2); //笔画的厚度
		canvas.drawLine(unitW, 0, unitW, unitH*5, paint);
		canvas.drawLine(unitW*2, unitH, unitW*2, unitH*5, paint);
		canvas.drawLine(unitW*3, unitH, unitW*3, unitH*5, paint);
		canvas.drawLine(unitW*4, unitH, unitW*4, unitH*5, paint);
		canvas.drawLine(unitW*5, unitH, unitW*5, unitH*5, paint);
		canvas.drawLine(unitW*6, unitH, unitW*6, unitH*5, paint);
		canvas.drawLine(unitW*7, unitH, unitW*7, unitH*5, paint);
		canvas.drawLine(unitW, unitH, screenW-unitW/2, unitH, paint);
		canvas.drawLine(unitW, unitH*2, screenW-unitW/2, unitH*2, paint);
		canvas.drawLine(unitW, unitH*3, screenW-unitW/2, unitH*3, paint);
		canvas.drawLine(unitW, unitH*4, screenW-unitW/2, unitH*4, paint);
		canvas.drawLine(unitW, unitH*5, screenW-unitW/2, unitH*5, paint);
		
		/**标记XY轴的单位数值*/
		paint.setTextSize(titleSize);
		paint.setStrokeWidth(3);
		paint.setAntiAlias(true); //消除锯齿,画文字时容易出现锯齿
		canvas.drawText("一", unitW-titleSize/2, unitH*5+titleSize, paint);
		canvas.drawText("二", unitW*2-titleSize/2, unitH*5+titleSize, paint);
		canvas.drawText("三", unitW*3-titleSize/2, unitH*5+titleSize, paint);
		canvas.drawText("四", unitW*4-titleSize/2, unitH*5+titleSize, paint);
		canvas.drawText("五", unitW*5-titleSize/2, unitH*5+titleSize, paint);
		canvas.drawText("六", unitW*6-titleSize/2, unitH*5+titleSize, paint);
		canvas.drawText("七", unitW*7-titleSize/2, unitH*5+titleSize, paint);
		
		if(listChart != null && listChart.size() > 0){
			int maxClicks = 0;
			int quater = 0;
			for (ChartBean chart : listChart) {
				if(chart.clicks>maxClicks)
					maxClicks = chart.clicks;
			}
			//Y轴的单位数据需根据列表中最高的点击量计算得出
			if(maxClicks <= 20){
				quater = 5;
			}else if(maxClicks >20 && maxClicks <=40){
				quater = 10;
			}else if(maxClicks > 40 && maxClicks <=80){
				quater = 20;
			}else if(maxClicks >100){
				String str = String.valueOf(maxClicks);
				int length = str.length();
				int level = (int)(maxClicks / Math.pow(10, length-2));
				quater = (int)(level / 3 * Math.pow(10, length-2));
			}
			canvas.drawText(String.valueOf(quater*4), (unitW-paint.measureText(String.valueOf(quater*4)))/2, unitH+titleSize/2, paint);
			canvas.drawText(String.valueOf(quater*3), (unitW-paint.measureText(String.valueOf(quater*3)))/2, unitH*2+titleSize/2, paint);
			canvas.drawText(String.valueOf(quater*2), (unitW-paint.measureText(String.valueOf(quater*2)))/2, unitH*3+titleSize/2, paint);
			canvas.drawText(String.valueOf(quater), (unitW-paint.measureText(String.valueOf(quater)))/2, unitH*4+titleSize/2, paint);
			
			/**画走势线段*/
			double cx = 0;
			double cy = 0;
			for (int i = 0; i < listChart.size(); i++) {
				paint.setColor(Color.argb(255, 255, 135, 0));
				paint.setStyle(Style.STROKE);
				double ratio = (double)listChart.get(i).clicks/(quater*4);
				double cx0 = unitW*(i+1);
				double cy0 = unitH*5 - ratio*unitH*4;
				canvas.drawCircle((float)cx0, (float)cy0, 3, paint); //画坐标
				if(i >= 1){
					canvas.drawLine((float)cx, (float)cy, (float)cx0, (float)cy0, paint); //相邻两个坐标画一条直线相连接
					paint.setColor(Color.argb(50, 255, 135, 0));
					paint.setStyle(Style.FILL);
					Path path = new Path();
					path.moveTo((float)cx, (float)cy);
					path.lineTo((float)cx, unitH*5);
					path.lineTo((float)cx0, unitH*5);
					path.lineTo((float)cx0, (float)cy0);
					path.close();
					canvas.drawPath(path, paint); //走势线下方区域以逐个梯形填充颜色
				}
				cx = cx0;
				cy = cy0;
				//最后一个坐标画一个气泡显示各个数值
				if(i == listChart.size()-1){
					String clicksText = listChart.get(i).clicks+"";
					String priceText = "￥"+listChart.get(i).prices;
					paint.setTextSize(priceSize);
					float clicksW = paint.measureText(clicksText);
					float priceW = paint.measureText(priceText);
					paint.setColor(Color.argb(200, 34, 34, 34));
					paint.setStyle(Style.FILL);
					paint.setAntiAlias(true);
					Path path1 = new Path();
					path1.moveTo((float)cx, (float)cy-5);
					path1.lineTo((float)cx-8, (float)cy-5-8);
					path1.lineTo((float)cx+8, (float)cy-5-8);
					path1.close();
					canvas.drawPath(path1, paint);
					
					float left = 0;
					if(i==0){
						left = (float)cx - 10 -2.5f - 5;
					}else if(i==6){
						left = (float)cx - (clicksW + priceW) - 10 -2.5f + 5;
					}else{
						left = (float)cx - (clicksW + priceW)/2 - 10 -2.5f;
					}
					float right = left + clicksW + priceW + 20 +2.5f;
					float bottom = (float)cy - 5 - 8 ;
					float top = bottom - unitH*3/4;
					RectF rectF = new RectF(left, top, right, bottom);
					canvas.drawRoundRect(rectF, 7, 7, paint);
					float clicksX = left + 10;
					float clicksY = (top + bottom)/2 + priceSize/2;
					paint.setColor(Color.argb(255, 255, 255, 255));
					canvas.drawText(clicksText, clicksX, clicksY, paint);
					paint.setColor(Color.argb(255, 255, 135, 0));
					canvas.drawText(priceText, clicksX + clicksW+5, clicksY, paint);
				}
				
			}
		}
	}
	/**
	 * 设置数据列表，刷新视图
	 * @param charts 走势图各个坐标的数据列表
	 */
	public void setData(ArrayList<ChartBean> charts){
		this.listChart = charts;
		invalidate();
	}
}
