package com.vivac.proyectofinal.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mainLayout: ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        mainLayout = binding.layouthome

        val dayNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (dayNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                mainLayout.setBackgroundResource(R.drawable.fondodia)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                mainLayout.setBackgroundResource(R.drawable.fondonoche)
            }
        }

        return root
    }

    private val widgetClickReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Handle widget click event here
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}