package com.example.louiscasillasweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.louiscasillasweatherapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment()  {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.btnSearch.setOnClickListener {
            val cityInput = binding.etCityInput.text.toString()

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(com.example.louiscasillasweatherapp.R.id.fr_container, ResultsFragment(cityInput), "RESULTS_FRAG")?.addToBackStack(null)?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)?.commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}