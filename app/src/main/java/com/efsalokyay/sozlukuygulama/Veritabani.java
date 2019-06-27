package com.efsalokyay.sozlukuygulama;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(Context context) {
        super(context, "sozluk.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS kelimeler (" +
                " kelime_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ingilizce TEXT," +
                " turkce TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS kelimeler");
        onCreate(db);
    }
}
