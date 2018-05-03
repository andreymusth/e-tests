package ru.tzkt.etests.db

/**
 * Created by andrey on 16/04/2018.
 */
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.tzkt.etests.Task

class TestsDatabaseAccess
/**
 * Private constructor to aboid object creation from outside classes.
 *
 * @param context
 */
private constructor(context: Context) {

    private val openHelper: SQLiteOpenHelper
    private var database: SQLiteDatabase? = null


    fun getTestsArray(): ArrayList<Task>? {

        open()

        val verbQuery = "SELECT * FROM tests WHERE _id in (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)"
        val cursor = database?.rawQuery(verbQuery, null)

        val tests =  ArrayList<Task>()

        while (cursor!!.moveToNext()) {
            fillTests(cursor, tests)
        }

        cursor.close()

        close()

        return tests
    }

    private fun fillTests(cursor: Cursor, tests: ArrayList<Task>) {

        val task = cursor.getString(cursor.getColumnIndex(TestsDBHelper.TestsColumns.TASK))
        val variants = cursor.getString(cursor.getColumnIndex(TestsDBHelper.TestsColumns.VARIANTS))
        val number = cursor.getInt(cursor.getColumnIndex(TestsDBHelper.TestsColumns.NUMBER))

        tests.add(Task(task, variants.split(";"), number))

    }

    init {
        this.openHelper = TestsDBHelper(context)
    }

    /**
     * Open the database connection.
     */
    fun open() {
        this.database = openHelper.writableDatabase
    }

    /**
     * Close the database connection.
     */
    fun close() {
        if (database != null) {
            this.database!!.close()
        }
    }

    companion object {
        private var instance: TestsDatabaseAccess? = null

        /**
         * Return a singleton instance of TestsDatabaseAccess.
         *
         * @param context the Context
         * @return the instance of DabaseAccess
         */
        fun getInstance(context: Context): TestsDatabaseAccess {
            if (instance == null) {
                instance = TestsDatabaseAccess(context)
            }
            return instance!!
        }
    }
}