package com.example.android.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class hourlyWeatherAdapter : RecyclerView.Adapter<WeatherViewHolder>() {

    private val items : ArrayList<Weather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view :View =LayoutInflater.from(parent.context).inflate(R.layout.hourly_weather,parent,false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currItem = items[position]
        holder.dateView.text=currItem.date
        holder.timeView.text=currItem.time
        holder.tempView.text=currItem.temp
        Glide.with(holder.itemView.context).load(currItem.imageUrl).into(holder.iconView)

    }

    override fun getItemCount(): Int {
       return items.size
    }
    fun updateWeather(updatedWeather : ArrayList<Weather>){
        items.clear()
        items.addAll(updatedWeather)

        notifyDataSetChanged()
    }
}
class WeatherViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val dateView : TextView = itemView.findViewById(R.id.date)
    val timeView : TextView = itemView.findViewById(R.id.time)
    val tempView : TextView = itemView.findViewById(R.id.temp)
    val iconView : ImageView = itemView.findViewById(R.id.icon)
}