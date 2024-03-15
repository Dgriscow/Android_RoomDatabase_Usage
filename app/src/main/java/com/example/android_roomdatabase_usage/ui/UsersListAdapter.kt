package com.example.android_roomdatabase_usage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_roomdatabase_usage.R
import com.example.android_roomdatabase_usage.database.User
import com.example.android_roomdatabase_usage.databinding.CustomRowBinding
import com.example.android_roomdatabase_usage.ui.fragments.ListFragmentDirections

class UsersListAdapter:RecyclerView.Adapter<UsersListAdapter.ListRowBinder>() {

    private var userList = emptyList<User>()




    //Handle the inflating on view. return the binded values of the screens to the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRowBinder {

        //create a variable for item binder, and set it to hold the inflater for the custom row. Inflate it to work and bind to the values
        val itemBinder = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListRowBinder(itemBinder)

    }

    //return the count of items in the view to be displayed
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ListRowBinder, position: Int) {
        //Current item based on its position in the list view/recycler view
        val currentItem = userList[position]

        //Bind the current item to the holder
        holder.bind(currentItem)

        holder.itemBinding.customRowLayout.setOnClickListener{


            val action = ListFragmentDirections.actionListFragmentToUpdateUserFragment(currentItem)

            holder.itemView.findNavController().navigate(action) //navigate to a view with a holder and a on touch event


        }

    }


    //A Internal Class used to bind the values for the adapter to their views, to the recycler view
    class ListRowBinder(val itemBinding:CustomRowBinding):RecyclerView.ViewHolder(itemBinding.root){

        //This function takes a userItem, and binds values from the views/fragments to the user item
        fun bind(userItem:User){

            itemBinding.userIDtxt.text = userItem.id.toString()
            itemBinding.fNameTxt.text = userItem.firstName
            itemBinding.lNameTxt.text = userItem.lastName
            itemBinding.ageTxt.text = userItem.age.toString()



        }

    }


    //This function takes a list of users and sets the data in here to that list, to adapt to the view
    fun setData(usersToAdapt:List<User>){

        this.userList = usersToAdapt

        //notify the app of the change
        notifyDataSetChanged()
    }

}