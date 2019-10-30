package com.example.booking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    String create_table = "create table user(name,email,age Integer,number Integer,password text not null)";
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

    public boolean insert (String name,String email,String number,String age,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("number",number);
        cv.put("age",age);
        cv.put("password",password);
        long ins = db.insert("user",null,cv);
        if(ins==-1)return false;
        else return true;
    }
    public Boolean checkid (String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        int a = cursor.getCount();
        db.close();
        cursor.close();
        if (a>0) return true;
        else return false;
    }
    public Boolean emailpass(String email, String password) {
        System.out.println(email + password);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?",new String[] {email,password} );
        int a = cursor.getCount();
        db.close();
        cursor.close();
        if (a > 0) return true;
        else return false;
    }

    public ArrayList getuserinfo(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList s = new ArrayList();
        Cursor cursor = db.rawQuery("select name,age,number from user where email=?",new String[] {email});
        if(cursor.moveToFirst()){
            do{
                s.add(cursor.getString(0));
                s.add(cursor.getString(1));
                s.add(cursor.getString(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s;
    }
}
