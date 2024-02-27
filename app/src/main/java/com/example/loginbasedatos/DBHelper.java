package com.example.loginbasedatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class  DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="login";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB){
        DB.execSQL("create table users(usuario TEXT primary key,contraseña TEXT)");
        DB.execSQL("create table datosUsuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, genero TEXT, edad TEXT, cancion TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop table if exists users");
        DB.execSQL("drop table if exists datosUsuarios");

    }

    public Boolean insertarInfoEncuesta(String genero, String edad, String cancion) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("genero", genero);
        values.put("edad", edad);
        values.put("cancion", cancion);


        long result = DB.insert("datosUsuarios", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertarInfo(String usuario, String contraseña){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("usuario",usuario);
        values.put("contraseña",contraseña);

        long result= DB.insert("users",null,values);
        if(result==-1) {
            return false;
        }else
            return true;
    }

    @SuppressLint("Range")
    public String consultarCancionExistente(String cancion) {
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("select * from datosUsuarios where cancion=?",new String[]{cancion});
        String nombreCancion = null;
        if (cursor.moveToFirst()) {
            nombreCancion = cursor.getString(cursor.getColumnIndex("cancion"));
        }
        cursor.close();
        return nombreCancion;
    }

    public Boolean checkUsername(String usuario){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("select * from users where usuario=?", new String[] {usuario});
        if(cursor.getCount()>0) {
            return true;
        }else{
            return false;}
    }

    public Boolean checkUsernamePassword(String usuario, String contraseña) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from users where usuario=? and contraseña=?", new String[]{usuario, contraseña});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }


    }
}