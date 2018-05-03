package ru.tzkt.etests.db

import android.content.Context
import android.provider.BaseColumns
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

/**
 * Created by andrey on 10/04/2018.
 */
class TestsDBHelper(ctx: Context): SQLiteAssetHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        const val DATABASE_NAME = "tests.db"
        const val DATABASE_VERSION = 1

    }

    class TestsColumns: BaseColumns {

        companion object {

            const val TASK = "task"
            const val VARIANTS = "variants"
            const val NUMBER = "number"

        }

    }

}