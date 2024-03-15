package com.example.android_roomdatabase_usage.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android_roomdatabase_usage.database.User
import com.example.android_roomdatabase_usage.database.UserDAO
import com.example.android_roomdatabase_usage.database.UserDatabase
import com.example.android_roomdatabase_usage.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    //Read All Data Reference to the databases datal, must be initalized by read all function

    val readAllData:LiveData<List<User>>

    //A reference to the repository for User Database
    private val repository: UserRepository

    init {
        //initialize DAO to view model
        val userDAO = UserDatabase.getDatabase(application).userDAOCon()

        //initialize repo to DAO
        repository = UserRepository(userDAO)

        readAllData = repository.readAllData


    }


    fun addNewUser(user: User){

        viewModelScope.launch(Dispatchers.IO){
            repository.addNewUser(user)
        }

    }



    fun updateUser(updatedUser: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(updatedUser)
        }
    }


    fun deleteUser(toDeleteUser: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(toDeleteUser)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }









}