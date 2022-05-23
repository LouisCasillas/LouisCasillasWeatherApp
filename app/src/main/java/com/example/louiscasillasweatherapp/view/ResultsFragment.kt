package com.example.louiscasillasweatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.louiscasillasweatherapp.MainActivity
import com.example.louiscasillasweatherapp.R
import com.example.louiscasillasweatherapp.databinding.FragmentScrollBinding
import com.example.louiscasillasweatherapp.model.WeatherListItem
import com.example.louiscasillasweatherapp.repository.WeatherRepoImpl
import com.example.louiscasillasweatherapp.viewmodel.WeatherViewModel
import java.util.*

class ResultsFragment(private var city: String?) : Fragment() {
    private var _binding: FragmentScrollBinding? = null
    private val binding: FragmentScrollBinding get() = _binding!!

    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

    private val viewModel: WeatherViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepoImpl()) as T
            }
        }.create(WeatherViewModel::class.java)
    }

    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it ->
        it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScrollBinding.inflate(layoutInflater)

        city = city?.capitalizeWords()

        binding.tvCityTitle.text = city

        binding.vwTitleBar.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        viewModel.getForecast(city)
        configureObserver()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureObserver() {
        weatherRecyclerAdapter = WeatherRecyclerAdapter(openDetails = ::openDetails)

        viewModel.forecast.observe(viewLifecycleOwner) { response ->
            if (response.list.isNotEmpty()) {
                weatherRecyclerAdapter.setForecast(response.list)
                binding.rvForecastList.adapter = weatherRecyclerAdapter
            }
        }
    }

    private fun openDetails(weatherListItem: WeatherListItem) {
        parentFragmentManager.beginTransaction()
            .replace(com.example.louiscasillasweatherapp.R.id.fr_container, DetailsFragment.newInstance(weatherListItem, city))
            .addToBackStack(null)
            .commit()
    }
}