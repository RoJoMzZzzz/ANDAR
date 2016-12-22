package com.research.andrade.andar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrade on 12/3/2016.
 */

public class Database extends SQLiteOpenHelper {

    public static final String dbName = "AndarDB";
    public static final String QRTableName = "QRTbl";
    public static final String EmContTableName = "ContTbl";
    public static final String QRCol1 = "qr";
    public static final String EmContCol1 = "name";
    public static final String EmContCol2 = "number";

    public Database(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+QRTableName+" (qr BLOB)");
        db.execSQL("create table "+EmContTableName+" (name TEXT, number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+QRTableName);
        db.execSQL("DROP TABLE IF EXISTS "+EmContTableName);
        onCreate(db);
    }

    public boolean insertQR(byte[] qr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put(QRCol1, qr);
        long res = db.insert(QRTableName, null, cont);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getMyQR(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+QRTableName, null);
        return res;
    }

    public void deleteMyQr(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(QRTableName, null, null);
    }

    public boolean insertContact(String name, String num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put(EmContCol1, name);
        cont.put(EmContCol2, num);
        long res = db.insert(EmContTableName, null, cont);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllContact(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+EmContTableName, null);
        return res;
    }


}
