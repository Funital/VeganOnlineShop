package com.example.veganonlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperMemo extends SQLiteOpenHelper {

    static final String DATABASE_NAME =  "Memo.db";
    static  final  String TABLE_NAME = "MemoTABLE";
    static  final  int DATABASE_VERSION = 1;

    static  final String KEY_ID = "id";
    static  final String KEY_DATE = "date";
    static  final String KEY_TITLE = "title";
    static  final String KEY_CONTENT = "content";


    public DatabaseHelperMemo(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =  ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_DATE + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_CONTENT + " TEXT " + ")");

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    boolean insertData(String date, String title, String content){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE,date);
        cv.put(KEY_TITLE,title);
        cv.put(KEY_CONTENT,content);

        long result = db.insert(TABLE_NAME,null,cv);
        db.close();
        if (result != -1) return true;
        else return false;
    }

    public Cursor readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean updateData(String date, String  title, String content, int id) {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE, date);;
        cv.put(KEY_TITLE, title);
        cv.put(KEY_CONTENT, content);
        int result = db.update(TABLE_NAME,
                cv,
                KEY_ID+"=? ",
                new String[]{ String.valueOf(id) });

        db.close();
        if (result != -1) return true;
        else return false;
    }

    boolean deleteData(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME,    KEY_ID+"=? ", new String[]{ String.valueOf(userid) });
        db.close();
        if (result != -1) return true;
        else return false;
    }

    public Cursor searchReadData(String content){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_CONTENT+" LIKE ?", new String[]{"%"+ content +"%"},
                null, null, null );
        return cursor;
    }
}
