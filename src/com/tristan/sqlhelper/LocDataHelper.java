package com.tristan.sqlhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 将assets中的db文件拷贝到databases中
 * @author TristanHuang
 * 2017-5-23 上午11:47:33
 */
public class LocDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "localization.db";
    private static final int DATABASE_VERSION = 1;
	
    
    public LocDataHelper(Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
