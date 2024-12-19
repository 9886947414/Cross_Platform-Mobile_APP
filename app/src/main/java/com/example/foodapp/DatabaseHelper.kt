package com.example.foodapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Cafeteria.db"
        private const val DATABASE_VERSION = 1

        // Employee table
        const val TABLE_EMPLOYEE = "Employee"
        const val COL_EMP_ID = "emp_id"
        const val COL_EMP_NAME = "name"
        const val COL_WALLET = "wallet"
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_EMPLOYEE (
                $COL_EMP_ID INTEGER PRIMARY KEY,
                $COL_EMP_NAME TEXT NOT NULL,
                $COL_WALLET REAL NOT NULL
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLOYEE")
        //db.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD")
        onCreate(db)
    }

    // Insert an employee
    fun insertEmployee(empId: Int, name: String, wallet: Double): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_EMP_ID, empId)
        values.put(COL_EMP_NAME, name)
        values.put(COL_WALLET, wallet)
        return db.insert(TABLE_EMPLOYEE, null, values)
    }

    // Get employee by ID
    fun getEmployee(empId: Int): Triple<String, Double, Int>? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COL_EMP_NAME, $COL_WALLET FROM $TABLE_EMPLOYEE WHERE $COL_EMP_ID = ?", arrayOf(empId.toString()))
        return if (cursor.moveToFirst()) {
            val name = cursor.getString(0)
            val wallet = cursor.getDouble(1)
            cursor.close()
            Triple(name, wallet, empId)
        } else {
            cursor.close()
            null
        }
    }

}