package com.example.android.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: hourlyWeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView =findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchData()
        adapter= hourlyWeatherAdapter()
        recyclerView.adapter=adapter
    }

    private fun fetchData(){
        val editText : EditText =findViewById(R.id.cityName)
        val city=editText.text
        val url = "http://api.weatherapi.com/v1/forecast.json?key=538ed99be26146b7b6673406211507&q='$city'&days=1&aqi=no&alerts=no"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val forecastObject
                val weatherJsonArray=it.getJSONArray("forecast")
                val weatherArray= ArrayList<Weather>()
                for(i in 0 until weatherJsonArray.length()) {
                    val weatherJsonObject=weatherJsonArray.getJSONObject(i)
                    val weather= Weather(
                        weatherJsonObject.getString("title"),
                        weatherJsonObject.getString("author"),
                        weatherJsonObject.getString("url"),
                        weatherJsonObject.getString("imageUrl")
                    )
                    weatherArray.add(weather)
                }
                adapter.updateWeather(weatherArray)
            }, {

            })
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    }
