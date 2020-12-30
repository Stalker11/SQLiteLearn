package com.example.slqitelearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.slqitelearning.open_helper.CustomOpenHelper
import com.example.slqitelearning.open_helper.NoteModel
import com.example.slqitelearning.room.AppDatabase
import com.example.slqitelearning.room.DataBaseBuilder
import com.example.slqitelearning.room.UserMapper
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var openHelper: CustomOpenHelper
    private lateinit var roomBuilder: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openHelper = CustomOpenHelper(this)
        openHelper.saveNote(NoteModel(UUID.randomUUID().toString(),"Title", "Description"))
        roomBuilder = DataBaseBuilder.getInstance(this)
        roomBuilder.getUserDao().insertUser(UserMapper.createUser())
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"${openHelper.getNotes()} ${roomBuilder.getUserDao().getAll()}")
    }
}