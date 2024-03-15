package com.example.android_roomdatabase_usage.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android_roomdatabase_usage.R
import com.example.android_roomdatabase_usage.database.User
import com.example.android_roomdatabase_usage.databinding.FragmentUpdateUserBinding
import com.example.android_roomdatabase_usage.ui.UserViewModel


class UpdateUserFragment : Fragment() {


    private val args by navArgs<UpdateUserFragmentArgs>()
                        //uf = update fragment
    private lateinit var ufUpdateViewModel:UserViewModel

    private lateinit var UpdateAUserFragmentBinding:FragmentUpdateUserBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


                //set tupdateAUSerFragment to the binder
        UpdateAUserFragmentBinding = FragmentUpdateUserBinding.inflate(inflater, container, false)

        val view = UpdateAUserFragmentBinding.root


        //set the user view model
        ufUpdateViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //set the text to the texts
        UpdateAUserFragmentBinding.firstNameNewInpt.setText(args.currentUser.firstName)
        UpdateAUserFragmentBinding.lastNameNewInpt.setText(args.currentUser.lastName)
        UpdateAUserFragmentBinding.newAgeInpt.setText(args.currentUser.age.toString())


        UpdateAUserFragmentBinding.updateUserButton.setOnClickListener{

            updateUserItem()

        }

        setHasOptionsMenu(true)


        // Inflate the layout for this fragment
        return view
    }


    private fun updateUserItem(){
        //take the new values and update the user for them
        val newFirstName = UpdateAUserFragmentBinding.firstNameNewInpt.text.toString()
        val newLastName = UpdateAUserFragmentBinding.lastNameNewInpt.text.toString()
        val newAge = Integer.parseInt(UpdateAUserFragmentBinding.newAgeInpt.text.toString())


        //check if the user has entered all inputs
        if(inputCheck(newFirstName, newLastName, UpdateAUserFragmentBinding.newAgeInpt.text)){

            //Create a updated user object to update to
            val updatedUser = User(args.currentUser.id, newFirstName, newLastName, newAge)

            //Update the current Database user with This new user
            ufUpdateViewModel.updateUser(updatedUser)

            //Toast a successful message
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_LONG).show()
            //Navigate back up a view
            findNavController().navigate(R.id.action_updateUserFragment_to_listFragment)


        }else{
            Toast.makeText(requireContext(), "Please Enter Info For Every Spot", Toast.LENGTH_LONG).show()

        }


    }


    //Check if User Input is VALID
    //Input Check, check if the user entered all the data
    private fun inputCheck(firstName:String, lastName:String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
        //Check if the text in both name and last name and AGE is all present
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }

        if (item.itemId == R.id.menu_delete_all){
            deleteAllUsers()
        }


        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){

        //create a dialog box to give the user a chance to decide if they REALLY want to delete the user


        //delete the selected user based on the selected options pressed
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){ _, _ ->

            //when the user presses YES, delete them
            ufUpdateViewModel.deleteUser(args.currentUser)

            //make a toast message to notify the user
            Toast.makeText(requireContext(), "Successfully removed ${args.currentUser.firstName} From the Database", Toast.LENGTH_LONG).show()


            findNavController().navigate(R.id.action_updateUserFragment_to_listFragment)

        }

        builder.setNegativeButton("No"){ _, _ ->

        }

        //setting title for the
                        //args is the user component mentioned/referenced inside the nav group
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")

        //build the dialog and make it appear
        builder.show()
    }

    private fun deleteAllUsers(){
        //create a dialog box to give the user a chance to decide if they REALLY want to delete the user


        //delete the selected user based on the selected options pressed
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){ _, _ ->

            //when the user presses YES, delete them
            ufUpdateViewModel.deleteAllUsers()

            //make a toast message to notify the user
            Toast.makeText(requireContext(), "Successfully removed all entries", Toast.LENGTH_LONG).show()


            findNavController().navigate(R.id.action_updateUserFragment_to_listFragment)

        }

        builder.setNegativeButton("No"){ _, _ ->

        }

        //setting title for the
        //args is the user component mentioned/referenced inside the nav group
        builder.setTitle("Delete all Users?")
        builder.setMessage("Are you sure you want to delete Every User?")

        //build the dialog and make it appear
        builder.show()
    }


}