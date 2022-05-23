package com.example.louiscasillasweatherapp.view

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.louiscasillasweatherapp.MainActivity
import com.example.louiscasillasweatherapp.databinding.FragmentSearchBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

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
            transaction?.replace(com.example.louiscasillasweatherapp.R.id.fr_container, ScrollFragment(cityInput))
                ?.addToBackStack(null)?.commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}