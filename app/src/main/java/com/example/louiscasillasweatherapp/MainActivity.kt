package com.example.louiscasillasweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.louiscasillasweatherapp.view.SearchFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        var tempButton: ExtendedFloatingActionButton? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempButton = this.findViewById(R.id.add_fab)

        tempButton!!.setOnClickListener {
            val currentTemp = tempButton!!.text.toString()

            tempButton!!.text = when (currentTemp) {
                "°F" -> "°C"
                "°C" -> "°K"
                else -> "°F"
            }

            val currentFragment = supportFragmentManager.fragments.last()

            supportFragmentManager.beginTransaction().let {
                it.detach(currentFragment)
                it.commit()
            }
            supportFragmentManager.executePendingTransactions()
            supportFragmentManager.beginTransaction().let {
                it.attach(currentFragment)
                it.commit()
            }

        }

        supportFragmentManager.beginTransaction().replace(R.id.fr_container, SearchFragment(), "SEARCH_FRAG").commit()
    }
}

