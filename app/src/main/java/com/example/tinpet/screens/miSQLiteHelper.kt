package com.example.tinpet.screens

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper(context: Context) :SQLiteOpenHelper(

    context, "Tinpet.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE  Tinpet" + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "phone TEXT, password TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS Tinpet"
        db!!.execSQL(ordenBorrado)
        onCreate(db)

    }

    fun addUser(phone: String, password: String, name :String) {
        val data = ContentValues()
        data.put("phone", phone)
        data.put("password", password)
        data.put("name", name)
        val db = this.writableDatabase
        db.insert("Tinpet", null, data)
        db.close()

    }

    fun getPhone(phone: String) : String? {
        val db = this.writableDatabase
        var user: Int = 0
        var userPhone: String? = null
        val phone = db.rawQuery("SELECT * FROM Tinpet WHERE phone = '$phone'", null)

        if (phone.moveToFirst()){
            do{
                userPhone =phone.getString(0)
            }while (phone.moveToNext())
        }
        phone.close()
        return  userPhone
    }

    fun getName(name: String) : String? {
        val db = this.writableDatabase
        var user: Int = 0
        var userName: String? = null
        val name = db.rawQuery("SELECT * FROM Tinpet WHERE name = '$name'", null)

        if (name.moveToFirst()){
            do{
                userName =name.getString(0)
            }while (name.moveToNext())
        }
        name.close()
        return  userName
    }
    fun getPassword(password: String) : String? {
        val db = this.writableDatabase
        var user: Int = 0
        var userPassword: String? = null
        val password = db.rawQuery("SELECT * FROM Tinpet WHERE password = '$password'", null)

        if (password.moveToFirst()){
            do{
                userPassword =password.getString(0)
            }while (password.moveToNext())
        }
        password.close()
        return  userPassword
    }





}
