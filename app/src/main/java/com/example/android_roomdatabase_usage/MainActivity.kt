package com.example.android_roomdatabase_usage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //nav controller causes crashes, tech has changed
        //Video at 5:57 at crash
//        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))



    }


}