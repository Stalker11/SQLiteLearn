package com.example.slqitelearning.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.TEXT
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    @ColumnInfo(name = "user_name", typeAffinity = TEXT)
    val name:String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val phone:String,
    @Embedded
    val address: Address)
