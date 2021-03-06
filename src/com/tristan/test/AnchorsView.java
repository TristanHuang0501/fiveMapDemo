package com.tristan.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.Log;

import com.tristan.mapview.PointView;

public class AnchorsView extends PointView {
	private ArrayList<Point> anchorPoints;

	public AnchorsView(Context context) {
		super(context);
	}
	
	public AnchorsView(Context context, ArrayList<Point> anchorPoints){
		super(context);
		this.anchorPoints = (ArrayList<Point>) anchorPoints;
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 设置图层的背景色
		canvas.drawColor(Color.TRANSPARENT);
		// 添加画笔
		Paint paint_Point = new Paint();
		paint_Point.setAntiAlias(true); // 抗锯齿
		paint_Point.setStrokeWidth(8); 
		paint_Point.setStyle(Style.STROKE);
		paint_Point.setColor(Color.RED); 
		paint_Point.setStrokeCap(Cap.ROUND);//圆头的画笔头
		
		Paint paint_anchor = new Paint();
		paint_anchor.setAntiAlias(true); // 抗锯齿
		paint_anchor.setStrokeWidth(30); 
		paint_anchor.setStyle(Style.STROKE);
		paint_anchor.setColor(Color.RED); 
		paint_anchor.setStrokeCap(Cap.ROUND);//圆头的画笔头
		
		//画出几个信标节点
		drawDpPoint(canvas, paint_anchor, anchorPoints);
		Log.i("1212", "在AnchorsView类中："+anchorPoints.get(3));
	}
}
