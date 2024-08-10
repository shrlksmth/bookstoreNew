package com.example.test

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.test.model.BookListModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "bookstore.db"
        private val DB_VERSION = 1
        private val TABLE_NAME = "book_list"
        private val ID = "id"
        private val BOOK_NAME = "book_name"
        private val BOOK_AUTHOR = "book_author"
        private val BOOK_NOTES = "book_notes"
        private val BOOK_IMAGE = "book_image"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val create_table_query =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $BOOK_NAME TEXT, $BOOK_AUTHOR TEXT, $BOOK_NOTES TEXT)"
        p0?.execSQL(create_table_query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val drop_table_query = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(drop_table_query)
        onCreate(p0)
    }


    fun insertBook(book: BookListModel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(BOOK_NAME, book.name)
            put(BOOK_AUTHOR, book.author)
            put(BOOK_NOTES, book.notes)
            put(BOOK_IMAGE, book.imageUrl)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }


}
