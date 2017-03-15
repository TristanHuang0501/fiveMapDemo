package com.tristan.fivemapdemo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//����һ��github�Ƿ�����ɹ�
public class MainActivity extends Activity {
	//���԰�ť
	private ToggleButton btn1;
	//��ʾ�ɰ��״̬�л���ť
	private ToggleButton btn2;
	//���ڴ����л�ͼ�İ�ť
	private ToggleButton btn3;
	//��ʼ����ť
	private Button btn4;
	//��Ļ�����Ϣչʾ
	private TextView screenInfo;
	//����������ͼ��view
	private MapView draw_point;
	//�������ɰ��view
	private BoardView draw_board;
	//��������ͼ��view
	private BackgroundView draw_map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		// ��ȡ֡���ֶ��󣬲������䱳��ͼ
		final FrameLayout fl = (FrameLayout) findViewById(R.id.outside);


	   //��ȡ��Ļ�ķֱ���
	   DisplayMetrics metric = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(metric);
       
       //��Ϊmate2��Ϊ720px���߶�Ϊ1208px
       //nexus5��Ϊ1080px,�߶�Ϊ1776px
       int width = metric.widthPixels;  // ��Ļ���ȣ����أ�
       int height = metric.heightPixels;  // ��Ļ�߶ȣ����أ�
       //��Ϊ��mate2�����ܶ�Ϊ2.0
       //nexus5�����ܶ�Ϊ3.0
       float density = metric.density;  // ��Ļ�ܶȣ�0.75 / 1.0 / 1.5/ 2.0��
       int densityDpi = metric.densityDpi;  // ��Ļ�ܶ�DPI��120 / 160 / 240��
       screenInfo = (TextView) findViewById(R.id.screenInfo);
       screenInfo.setText("���ȣ�"+width+"  �߶ȣ�"+height+"  �ܶȣ�"+density+"  DPI��"+densityDpi);
		
       
       //��assets�е��ⲿdb�ļ�������data/data/databases��
		DatabaseUtil.packDataBase(this);
       
       
		btn1 = (ToggleButton) findViewById(R.id.btn_test);
		btn2 = (ToggleButton) findViewById(R.id.btn_board);
		btn3 = (ToggleButton) findViewById(R.id.btn_map);
		btn4 = (Button) findViewById(R.id.btn_reset);
		
		
		PointsData locPoint = new PointsData(this);
		List<Point> points = locPoint.getPointList();
		draw_point=new MapView(MainActivity.this,"test",points);
		System.out.println(points);
		
		
		//btn1�İ�������
		btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		    	if (isChecked) {
		            fl.addView(draw_point);
		        } else {
		            fl.removeView(draw_point);
		        }
		    }
		});
		
		//btn2�İ���״̬������������ʾ�ɰ�
		btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		    	if (isChecked) {
		    		draw_board=new  BoardView(MainActivity.this);
		            fl.addView(draw_board);
		        } else {
		            fl.removeView(draw_board);
		        }
		    }
		});
		

		//btn3�İ�������,������ʾ�ѻ������ĵ�ͼ
		btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		    	if (isChecked) {
		    		draw_map = new BackgroundView(MainActivity.this);
		    		fl.addView(draw_map);
		        } else {
		        	fl.removeView(draw_map);
		        }
		    }
		});
		
		
		//btn4�İ�������
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//�������
				Intent intent=getIntent();
				finish();
				startActivity(intent);
				
				//����ֱ���ö�̬ȥ��view�ķ��������������������
//				ll.removeView(draw_board);
//				ll.removeView(draw_point);
			}
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
	//ʵ�ִ�dip��λ��px��λ��ת��
	public  int dip2px(Context context, float dipValue){ 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int)(dipValue * scale + 0.5f); 
		} 
    
	
	//��װ����dip->px
	public  int transf(float dipValue){
		return dip2px(MainActivity.this,dipValue);
	}
}


