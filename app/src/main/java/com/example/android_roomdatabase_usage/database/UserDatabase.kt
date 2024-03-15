package com.example.android_roomdatabase_usage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Tells the Database how to enter/
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {


    //A connection to the user DAO
    abstract fun userDAOCon():UserDAO



    companion object{

        @Volatile
        private var INSTANCE: UserDatabase? = null



        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database" //name for the Database
                ).build()
                INSTANCE = instance
                return instance
            }


        }




    }

}