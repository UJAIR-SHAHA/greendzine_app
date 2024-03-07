package com.example.greendzine_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class UsersAdapter(private val context: Context) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>(), Filterable {

    private var userList: List<User> = ArrayList()
    private var filteredUserList: List<User> = ArrayList()

    fun setData(users: List<User>) {
        userList = users
        filteredUserList = users
        notifyDataSetChanged()
    }

    // Implement onCreateViewHolder, onBindViewHolder, getItemCount, and ViewHolder methods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = filteredUserList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return filteredUserList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val employeeIdTextView: TextView = itemView.findViewById(R.id.employeeIdTextView)
        private val dobTextView: TextView = itemView.findViewById(R.id.dobTextView)
        private val roleTextView: TextView = itemView.findViewById(R.id.roleTextView)
        // Add other views for dob, role, etc.

        fun bind(user: User) {
            nameTextView.text = "Name: ${user.name}"
            employeeIdTextView.text = "EmployeeId: ${user.employeeId}"
            dobTextView.text = "DOB: ${user.dob}"
            roleTextView.text = "Role: ${user.role}"
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val filteredList = mutableListOf<User>()

                if (constraint.isNullOrEmpty()) {
                    filteredList.addAll(userList)
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()

                    for (user in userList) {
                        if (user.name.lowercase(Locale.getDefault()).contains(filterPattern)) {
                            filteredList.add(user)
                        }
                    }
                }

                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredUserList = results?.values as List<User>
                notifyDataSetChanged()
            }
        }
    }
}
