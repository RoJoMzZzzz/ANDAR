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
    public static final String QRCol1 = "qr";
    public static final String QRCol2 = "id";

    public Database(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+QRTableName+" (qr BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+QRTableName);
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

}
