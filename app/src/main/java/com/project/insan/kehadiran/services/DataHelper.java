package com.project.insan.kehadiran.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;
import android.util.Log;

import com.project.insan.kehadiran.java.Movie;
import com.project.insan.kehadiran.java.Tv;
import com.project.insan.kehadiran.java.Tv.ResultsBean;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;
    // Database name
    public static final String DATABASE_NAME = "Favorite";
    // table name
    private static final String TABLE_MOVIES = "Movies";
    private static final String TABLE_TVS = "Tvs";
    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_RATING = "rating";
    private static final String KEY_DETAIL = "detail";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMG = "path";
    private static final String KEY_IMGPATH = "poster";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_RATING + " REAL,"
                +  KEY_DETAIL + " TEXT,"
                + KEY_DATE + " TEXT,"
                +  KEY_IMGPATH + " TEXT,"
                +  KEY_IMG + " BLOB"
                +")";

        String CREATE_TV_TABLE = "CREATE TABLE " + TABLE_TVS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_RATING + " REAL,"
                +  KEY_DETAIL + " TEXT,"
                + KEY_DATE + " TEXT,"
                +  KEY_IMGPATH + " TEXT,"
                +  KEY_IMG + " BLOB"
                +")";


        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_TV_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TVS);
        onCreate(db);
    }
//
//
//    DATABASE FOR MOVIE
//
//
    public void addRecordMovie(Movie.ResultsBean movie,byte[] img){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, movie.getId());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_DETAIL, movie.getOverview());
        values.put(KEY_RATING, movie.getVote_average());
        values.put(KEY_DATE, movie.getRelease_date());
        values.put(KEY_IMGPATH, movie.getPoster_path());
        values.put(KEY_IMG, img);

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    public void addRecordMovie(Movie.ResultsBean movie){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, movie.getId());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_DETAIL, movie.getOverview());
        values.put(KEY_RATING, movie.getVote_average());
        values.put(KEY_DATE, movie.getRelease_date());
        values.put(KEY_IMGPATH, movie.getPoster_path());

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }


    public Movie.ResultsBean  getMovie(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Movie.ResultsBean> movies = new ArrayList<Movie.ResultsBean>();

        Movie.ResultsBean movie = new Movie.ResultsBean();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES +" WHERE "+KEY_ID+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                movie.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                movie.setVote_average(cursor.getDouble(cursor.getColumnIndex(KEY_RATING)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(KEY_DETAIL)));
                movie.setRelease_date(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                movie.setPoster_path(cursor.getString(cursor.getColumnIndex(KEY_IMGPATH)));
                movie.setImg(cursor.getBlob(cursor.getColumnIndex(KEY_IMG)));

                movies.add(movie);
            } while (cursor.moveToNext());
        }


        return movie;
    }


    public List<Movie.ResultsBean> getAllRecordMovie() {
        List<Movie.ResultsBean> movies = new ArrayList<Movie.ResultsBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Movie.ResultsBean movie = new Movie.ResultsBean();
                movie.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                movie.setVote_average(cursor.getDouble(cursor.getColumnIndex(KEY_RATING)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(KEY_DETAIL)));
                movie.setRelease_date(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                movie.setPoster_path(cursor.getString(cursor.getColumnIndex(KEY_IMGPATH)));
                movie.setImg(cursor.getBlob(cursor.getColumnIndex(KEY_IMG)));

                movies.add(movie);
            } while (cursor.moveToNext());
        }

        // return contact list
        return movies;
    }
    public int getMovieCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public void deleteMovie(Movie.ResultsBean movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getId()) });
        db.close();
    }


    //
//
//    DATABASE FOR tv
//
//
    public void addRecordTv(ResultsBean tv, byte[] img){
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

    public void addRecordTv(ResultsBean tv){
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


    public ResultsBean  getTv(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<ResultsBean> tvs = new ArrayList<ResultsBean>();

        ResultsBean tv = new ResultsBean();
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


    public List<ResultsBean> getAllRecord() {
        List<ResultsBean> tvs = new ArrayList<ResultsBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TVS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResultsBean tv = new ResultsBean();
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

    public void deleteTv(ResultsBean tv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TVS, KEY_ID + " = ?",
                new String[] { String.valueOf(tv.getId()) });
        db.close();
    }



}

