package com.example.tinpet.screens

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tinpet.User

class miSQLiteHelper(context: Context) :SQLiteOpenHelper(

    context, "amigos.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE  amigos" + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "phone TEXT, password TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS amigos"
        db!!.execSQL(ordenBorrado)
        onCreate(db)

    }

    fun addUser(phone: String, password: String) {
        val data = ContentValues()
        data.put("phone", phone)
        data.put("password", password)
        val db = this.writableDatabase
        db.insert("amigos", null, data)
        db.close()

    }

    fun getUser(phone: String) : Int {
        val db = this.writableDatabase
        var user: Int = 0
        val cursor = db.rawQuery("SELECT * FROM amigos WHERE phone = '$phone'", null)

        if (cursor.moveToFirst()){
            do{
                user =cursor.getInt(0)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return  user
    }

}
