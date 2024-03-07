package com.example.greendzine_app

// HomeFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Load JSON data from assets
        val jsonData = loadJsonDataFromAssets()

        // Parse JSON into a list of ProductivityData objects
        val productivityList = parseJsonToProductivityList(jsonData)

        // Update UI with productivity data
        updateUIWithProductivityData(productivityList, rootView)

        return rootView
    }

    private fun loadJsonDataFromAssets(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = requireActivity().assets.open("productivity.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    private fun parseJsonToProductivityList(json: String?): List<ProductivityData> {
        val typeToken = object : TypeToken<List<ProductivityData>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    private fun updateUIWithProductivityData(
        productivityList: List<ProductivityData>,
        rootView: View
    ) {
        val textView: TextView = rootView.findViewById(R.id.productivityTextView)
        val stringBuilder = StringBuilder()

        // Update TextView with all productivity data
        for (productivityData in productivityList) {
            stringBuilder.append("Productivity on ${productivityData.day} ${productivityData.productivity}%\n")
        }

        textView.text = stringBuilder.toString()

        // Example: Update Chart with productivity data
        // Note: You need to implement this based on your chart library and setup
        // val lineChart: LineChart = rootView.findViewById(R.id.lineChart)
        // updateChart(lineChart, productivityList)
    }

}

