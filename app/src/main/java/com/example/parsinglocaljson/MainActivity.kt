package com.example.parsinglocaljson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parsinglocaljson.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    lateinit var array : ArrayList<DataItem>
    lateinit var binding : ActivityMainBinding
    lateinit var adpter: RecycleViewAdpter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        array = arrayListOf()
        adpter = RecycleViewAdpter(array, this)
        binding.rv.adapter = adpter
        binding.rv.layoutManager = LinearLayoutManager(this)

        jsonFormat()

    }

    private fun jsonFormat() {
        var data : DataItem
        try {
            val jsonArray = JSONArray(loadJSONFromAsset())
            for (i in 0 until jsonArray.length()) {
                val userDetail = jsonArray.getJSONObject(i)
                val title = userDetail.getString("title")
                val url = userDetail.getString("url")
                data = DataItem(title,url)
                array.add(data)
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        adpter.update(array)
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}