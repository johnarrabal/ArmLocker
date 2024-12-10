package com.example.projetoarmario

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "clientes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "clientes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_BIRTH_DATE = "birth_date"
        private const val COLUMN_CPF = "cpf"
        private const val COLUMN_CEP = "cep"
        private const val COLUMN_STREET = "street"
        private const val COLUMN_CITY = "city"
        private const val COLUMN_STATE = "state"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_FIRST_NAME TEXT, "
                + "$COLUMN_LAST_NAME TEXT, "
                + "$COLUMN_EMAIL TEXT, "
                + "$COLUMN_PASSWORD TEXT, "
                + "$COLUMN_BIRTH_DATE TEXT, "
                + "$COLUMN_CPF TEXT, "
                + "$COLUMN_CEP TEXT, "
                + "$COLUMN_STREET TEXT, "
                + "$COLUMN_CITY TEXT, "
                + "$COLUMN_STATE TEXT, "
                + "$COLUMN_PHONE TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(firstName: String, lastName: String, email: String, password: String, birthDate: String, cpf: String, cep: String, street: String, city: String, state: String, phone: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_FIRST_NAME, firstName)
        values.put(COLUMN_LAST_NAME, lastName)
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)
        values.put(COLUMN_BIRTH_DATE, birthDate)
        values.put(COLUMN_CPF, cpf)
        values.put(COLUMN_CEP, cep)
        values.put(COLUMN_STREET, street)
        values.put(COLUMN_CITY, city)
        values.put(COLUMN_STATE, state)
        values.put(COLUMN_PHONE, phone)
        return db.insert(TABLE_NAME, null, values)
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_ID), "$COLUMN_EMAIL=? AND $COLUMN_PASSWORD=?", arrayOf(email, password), null, null, null)
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun getUserDetails(email: String): Cursor {
        val db = this.readableDatabase
        return db.query(TABLE_NAME, null, "$COLUMN_EMAIL=?", arrayOf(email), null, null, null)
    }
}