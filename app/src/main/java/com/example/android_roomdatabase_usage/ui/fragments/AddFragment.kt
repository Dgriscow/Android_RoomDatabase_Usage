package com.example.android_roomdatabase_usage.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_roomdatabase_usage.R
import com.example.android_roomdatabase_usage.database.User
import com.example.android_roomdatabase_usage.databinding.FragmentAddBinding
import com.example.android_roomdatabase_usage.ui.UserViewModel


class AddFragment : Fragment() {

    private lateinit var AddFragBinding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AddFragBinding = FragmentAddBinding.inflate(inflater, container, false)

        val view = AddFragBinding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        AddFragBinding.addUserButton.setOnClickListener{
            insertDataToDatabase()
        }


        // Inflate the layout for this fragment
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = AddFragBinding.firstNameInpt.text.toString()
        val lastName = AddFragBinding.lastNameInpt.text.toString()

        val ageVal = AddFragBinding.ageInpt.text


        if(inputCheck(firstName, lastName, ageVal)){

            //Create a NEW USER
            //regardless of primary key, still NEEDS A VALUE, so just pass 0
            val user = User(0, firstName, lastName, Integer.parseInt(ageVal.toString()))
            //take the age val and use the integer value and parse the string entry through (this works for now, but should be handled better in the future,
            // if the user inputs something other than a num, so it might not work with other values)
            mUserViewModel.addNewUser(user)

            //Toast a message informing user
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()

            //IF CAUSING ISSUES, REMOVE
            findNavController().navigate(R.id.action_addFragment_to_listFragment)






        }else{
            Toast.makeText(requireContext(), "Please fill out all the fields.", Toast.LENGTH_LONG).show()

        }

    }

    //Input Check, check if the user entered all the data
    private fun inputCheck(firstName:String, lastName:String, age:Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
        //Check if the text in both name and last name and AGE is all present
    }


}