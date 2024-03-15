package com.example.android_roomdatabase_usage.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDAO {
    //Insert a new user on the users table
    @Insert(onConflict = OnConflictStrategy.IGNORE) //On a Conflict, IGNORE any potential issues
    suspend fun addUser(user: User)
    //To use in tandom with COroutines


    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user:User) //Delete 1 specific user from the database


    //Delete ALL users from the database - as apposed to the video putting it on main users view,
    //Insert it AS A NEW OPTION for the already existing options
    @Query("Delete From users_table")
    suspend fun deleteAllUsers()

    //This function/Query operates not on the usrers table but the sqlite_sequence
    //The purpose of this is to clear the PRIMARY KEYING index from the database, and stop incrementing
    //on numbers that no longer exist.
    @Query("DELETE FROM sqlite_sequence")
    suspend fun clearPrimaryKeyIndex()

    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun readAllUsers(): LiveData<List<User>> //Return a LIVE DATA list of all the users in the database

    //THESE ARE LOCAL DATABASES AND QUERIES*

}