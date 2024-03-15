package com.example.android_roomdatabase_usage.database

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {

    val readAllData:LiveData<List<User>> = userDAO.readAllUsers()

    //Adds a new user entry to the datase
    suspend fun addNewUser(newUser:User){
        userDAO.addUser(newUser)
    }



    //Update a chosen/passed User
    suspend fun updateUser(updatedUser: User){
        userDAO.updateUser(updatedUser)
    }


    suspend fun deleteUser(userToRemove: User){
        userDAO.deleteUser(userToRemove)
    }

    suspend fun deleteAllUsers(){
        userDAO.deleteAllUsers()
        //ALso here CLEAR the primary key
        userDAO.clearPrimaryKeyIndex()
    }





}

