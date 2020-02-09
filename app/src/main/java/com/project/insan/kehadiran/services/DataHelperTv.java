package com.project.insan.kehadiran.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.insan.kehadiran.java.Tv;

import java.util.ArrayList;
import java.util.List;

public class DataHelperTv extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;
    // Database name
    public static final String DATABASE_NAME = "Favoritetv";
    // table name
    private static final String TABLE_TVS = "tvs";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_RATING = "rating";
    private static final String KEY_DETAIL = "detail";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMG = "path";
    private static final String KEY_IMGPATH = "poster";

    public DataHelperTv(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TVS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_RATING + " REAL,"
                +  KEY_DETAIL + " TEXT,"
                + KEY_DATE + " TEXT,"
                +  KEY_IMGPATH + " TEXT,"
                +  KEY_IMG + " BLOB"
                +")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TVS);
        onCreate(db);
    }
//
//
//    DATABASE FOR tv
//
//
    public void addRecordTv(Tv.ResultsBean tv, byte[] img){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, tv.getId());
        values.put(KEY_TITLE, tv.getName());
        values.put(KEY_DETAIL, tv.getOverview());
        values.put(KEY_RATING, tv.getVote_average());
        values.put(KEY_DATE, tv.getFirst_air_date());
        values.put(KEY_IMGPATH, tv.getPoster_path());
        values.put(KEY_IMG, img);

        db.insert(TABLE_TVS, null, values);
        db.close();
    }

    public void addRecordTv(Tv.ResultsBean tv){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, tv.getId());
        values.put(KEY_TITLE, tv.getName());
        values.put(KEY_DETAIL, tv.getOverview());
        values.put(KEY_RATING, tv.getVote_average());
        values.put(KEY_DATE, tv.getFirst_air_date());
        values.put(KEY_IMGPATH, tv.getPoster_path());

        db.insert(TABLE_TVS, null, values);
        db.close();
    }


    public Tv.ResultsBean  getTv(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Tv.ResultsBean> tvs = new ArrayList<Tv.ResultsBean>();

        Tv.ResultsBean tv = new Tv.ResultsBean();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TVS +" WHERE "+KEY_ID+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                tv.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                tv.setName(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                tv.setVote_average(cursor.getDouble(cursor.getColumnIndex(KEY_RATING)));
                tv.setOverview(cursor.getString(cursor.getColumnIndex(KEY_DETAIL)));
                tv.setName(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                tv.setPoster_path(cursor.getString(cursor.getColumnIndex(KEY_IMGPATH)));
//                tv.setPoster_path(cursor.getBlob(cursor.getColumnIndex(KEY_IMG)));

                tvs.add(tv);
            } while (cursor.moveToNext());
        }


        return tv;
    }


    public List<Tv.ResultsBean> getAllRecord() {
        List<Tv.ResultsBean> tvs = new ArrayList<Tv.ResultsBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TVS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Tv.ResultsBean tv = new Tv.ResultsBean();
                tv.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                tv.setName(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                tv.setVote_average(cursor.getDouble(cursor.getColumnIndex(KEY_RATING)));
                tv.setOverview(cursor.getString(cursor.getColumnIndex(KEY_DETAIL)));
                tv.setFirst_air_date(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                tv.setPoster_path(cursor.getString(cursor.getColumnIndex(KEY_IMGPATH)));
//                tv.setImg(cursor.getBlob(cursor.getColumnIndex(KEY_IMG)));

                tvs.add(tv);
            } while (cursor.moveToNext());
        }

        // return contact list
        return tvs;
    }
    public int getUserModelCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TVS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public void deleteTv(Tv.ResultsBean tv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TVS, KEY_ID + " = ?",
                new String[] { String.valueOf(tv.getId()) });
        db.close();
    }

}

