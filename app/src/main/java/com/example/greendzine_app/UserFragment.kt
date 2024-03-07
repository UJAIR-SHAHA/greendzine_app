package com.example.greendzine_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import androidx.appcompat.widget.SearchView
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class UserFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var userList: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)

        // Initialize RecyclerView and Adapter
        recyclerView = rootView.findViewById(R.id.recyclerView)
        usersAdapter = UsersAdapter(requireContext())

        // Initialize SearchView
        searchView = rootView.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                usersAdapter.filter.filter(newText)
                return false
            }
        })

        // Load JSON data from assets
        val jsonData = loadJsonDataFromAssets()

        // Parse JSON into a list of User objects
        userList = parseJsonToUserList(jsonData)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = usersAdapter
        usersAdapter.setData(userList)

        return rootView
    }

    private fun loadJsonDataFromAssets(): String? {
        return try {
            val inputStream: InputStream = requireActivity().assets.open("user_data.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun parseJsonToUserList(json: String?): List<User> {
        val typeToken = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(json, typeToken)
    }
}
