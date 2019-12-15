package com.example.logreg;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdatbazisSegito extends SQLiteOpenHelper {

    private static final int DBversion = 1;
    private static final String DBname = "reg";

    private static final String TABLE_NAME = "felhasznalo";

    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHNEV = "felhnev";
    private static final String COL_JELSZO = "jelszo";
    private static final String COL_TELJESNEV = "teljesnev";

    private String bejelentkezettadat;


    public AdatbazisSegito(Context context){
        super(context,DBname,null,DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id PRIMARY KEY not null unique, email text, felhnev text, jelszo text, teljesnev text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }

    public boolean regisztracio(String email, String felhnev, String jelszo, String teljesNev){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesNev);


        //return db.insert(TABLE_NAME, null, values) != -1;
        long erintettSorok = db.insert(TABLE_NAME, null, values);
        if (erintettSorok == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where email=?",new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }
    public Boolean felhnevjelszo(String felhnev, String jelszo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ TABLE_NAME + " where felhnev=? or email=? and jelszo=?",new String[]{felhnev,jelszo});
        if (cursor.getCount() > 0) {
            bejelentkezettadat = felhnev;
            return true;

        }
        else return false;
    }
    public Cursor adatLekerdezes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE felhnev=? or email=?",new String[]{bejelentkezettadat});
    }



}
