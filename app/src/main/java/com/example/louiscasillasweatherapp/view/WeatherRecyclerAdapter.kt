package com.example.louiscasillasweatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.louiscasillasweatherapp.databinding.ForecastListItemBinding
import com.example.louiscasillasweatherapp.model.WeatherListItem

class WeatherRecyclerAdapter(private val forecast: MutableList<WeatherListItem> = mutableListOf(),
                             private val openDetails: (WeatherListItem) -> Unit
                             ): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

        fun setForecast(newList: List<WeatherListItem>) {
            forecast.clear()
            forecast.addAll(newList)
            notifyDataSetChanged()
        }

        inner class WeatherViewHolder(private val binding: ForecastListItemBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(forecastItem: WeatherListItem) {
                binding.tvDescription.text = forecastItem.getDescription()
                binding.tvTemperature.text = forecastItem.getFullTemp()

                binding.root.setOnClickListener {
                    openDetails(forecastItem)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ForecastListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}