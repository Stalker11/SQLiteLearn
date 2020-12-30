package com.example.slqitelearning.open_helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.slqitelearning.open_helper.Constants.DATABASE_NAME
import com.example.slqitelearning.open_helper.Constants.DATABASE_VERSION
import com.example.slqitelearning.open_helper.NoteModelFields.DESCRIPTION
import com.example.slqitelearning.open_helper.NoteModelFields.PUB_YEAR
import com.example.slqitelearning.open_helper.NoteModelFields.TABLE_NAME
import com.example.slqitelearning.open_helper.NoteModelFields.TITLE

class CustomOpenHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query: String
        //creating table
        query =
            "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (ID INTEGER PRIMARY KEY, ${TITLE} TEXT, ${DESCRIPTION} TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion == 2 && oldVersion == 1){
            db?.execSQL("ALTER TABLE ${TABLE_NAME} ADD COLUMN ${PUB_YEAR} INTEGER")
        }
    }

    fun saveNote(noteModel: NoteModel) {
        val contentValues = ContentValues()
        contentValues.apply {
            put(TITLE, noteModel.title)
            put(DESCRIPTION, noteModel.description)
        }
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    fun getNotes(): MutableList<NoteModel> {
        val list = mutableListOf<NoteModel>()
        val select_query = "SELECT *FROM ${TABLE_NAME}"
        val db = this.writableDatabase
        val cursor = db.rawQuery(select_query, null)
        if (cursor.moveToFirst()) {
            do {
                val noteModel = NoteModel(
                    cursor.getString(0), cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(noteModel)
            } while (cursor.moveToNext())
        }
        db.close()
    return list}
}