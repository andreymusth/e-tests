package ru.tzkt.etests.db

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import ru.tzkt.etests.db.EmailsDBHelper.EmailsContract.Companion.TABLE_NAME

class EmailsContentProvider : ContentProvider() {

    private var emailsDBHelper: EmailsDBHelper? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implement this to handle requests to delete one or more rows.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val db = emailsDBHelper?.writableDatabase
        val id = db?.insert(TABLE_NAME, null, values)

        if (id != null && id > 0) {
            return ContentUris.withAppendedId(uri, id)
        } else {
            throw android.database.SQLException("Failed to insert row into $uri")
        }
    }

    override fun onCreate(): Boolean {
        emailsDBHelper = EmailsDBHelper(context)
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val db = emailsDBHelper?.readableDatabase
        return db?.query(TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        val db = emailsDBHelper?.writableDatabase
        return db?.update(TABLE_NAME, values, selection, selectionArgs) ?: -1

    }
}
