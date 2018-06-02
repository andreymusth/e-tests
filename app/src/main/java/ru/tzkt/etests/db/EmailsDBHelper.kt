package ru.tzkt.etests.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class EmailsDBHelper(context: Context): SQLiteOpenHelper(context, ANSWERS_DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        const val ANSWERS_DATABASE_NAME = "emails.db"
        const val DATABASE_VERSION = 1
        const val CREATE_DATABASE_QUERY = "CREATE TABLE IF NOT EXISTS " + EmailsContract.TABLE_NAME + " (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${EmailsContract.EMAIL} TEXT " +
                ")"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_DATABASE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }


    class EmailsContract : BaseColumns {

        companion object {

            const val CONTENT_URI = "content://ru.tzkt.etests.EMAILS_CONTENT_URI"

            const val TABLE_NAME = "emails"
            const val EMAIL = "email"
        }

    }

}