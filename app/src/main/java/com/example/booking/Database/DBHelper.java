package com.example.booking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    String create_table = "create table user(name,email,age Integer,gender,password text not null)";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if Exists user");
    }

    public boolean insert (String name,String email,String age,String gender,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("age",age);
        cv.put("gender",gender);
        cv.put("password",password);
        long ins = db.insert("user",null,cv);
        if(ins==-1)return false;
        else return true;
    }
    public Boolean checkid (String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if (cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean emailpass(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?",new String[] {email,password} );
        if (cursor.getCount() > 0) return false;
        else return true;
    }
}
