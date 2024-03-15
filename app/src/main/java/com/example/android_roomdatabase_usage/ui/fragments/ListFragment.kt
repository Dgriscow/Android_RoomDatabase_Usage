package com.example.android_roomdatabase_usage.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_roomdatabase_usage.R
import com.example.android_roomdatabase_usage.database.User
import com.example.android_roomdatabase_usage.databinding.FragmentListBinding
import com.example.android_roomdatabase_usage.ui.UserViewModel
import com.example.android_roomdatabase_usage.ui.UsersListAdapter


class ListFragment : Fragment() {


    private lateinit var  ListFragBinding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ListFragBinding = FragmentListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val view = ListFragBinding.root

        //on the floating action button, bind its so it has a on click listener
        ListFragBinding.floatingActionButton.setOnClickListener{
            //Navigation Controller to the add fragment
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        //Recycler View related operations

        //create a adapter variable based on the USersListAdapter
        val adapter = UsersListAdapter()

        val recyclerMainListView = ListFragBinding.recyclerView

        recyclerMainListView.adapter = adapter


        recyclerMainListView.layoutManager = LinearLayoutManager(requireContext())


        //View Model Related

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //read all the data from the user view model
        mUserViewModel.readAllData.observe(viewLifecycleOwner, {

            user ->
            adapter.setData(user) //pass the user to the Adapter


        })











        return view

    }


}