package com.example.diaryproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//데이터 관리 클래스
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final  int DB_VERSION = 1;
    private static final String DB_NAME = "MyDiary.db";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //테이블 생성 (최초 1회만 생성)
        db.execSQL("CREATE TABLE IF NOT EXISTS DiaryInfo(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "content TEXT NOT NULL, " +
                "weatherType INTEGER NOT NULL, " +
                "userDate TEXT NOT NULL, w" +
                "riteDate TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //데이터를 db에 저장 (insert)
    public void setInsertDiaryList(String _title, String _content, int _weatherType, String _userDate, String _userDate2, String _writeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DiaryInfo (title, content, weatherType, userDate, writeDate) VALUES('" + _title + "','" + _content + "', '" + _weatherType + "', '" + _userDate + "', '" + _userDate2 + "', '" + _writeDate + "')");

    }
    // 수정 메소드 UPDATE
    public void setUpdateDiaryList(String _title, String _content, int _weatherType, String _userDate, String _userDate2, String _writeDate, String _beforeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DiaryInfo SET title = '" + _title + "' , content = '" + _content + "', weatherType = '" + _weatherType + "', userDate = '" + _userDate + "', userDate2 = '" + _userDate2 + "', writeDate = '" + _writeDate + "' WHERE writeDate = '" + _beforeDate + "' ");
    }

    // 기존 데이터 삭제 메소드 - DLELTE
    public void setDeleteDiaryList(String _writeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DiaryInfo WHERE writeDate = '" + _writeDate + "'");
    }
}
