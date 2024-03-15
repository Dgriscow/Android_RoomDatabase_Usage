package com.example.android_roomdatabase_usage.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize //Tag to actually tell the User data it can be Parcelized
@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName:String,
    val lastName:String,
    val age:Int

):Parcelable //Parse the user object value through to much easier data to use and manipulate
