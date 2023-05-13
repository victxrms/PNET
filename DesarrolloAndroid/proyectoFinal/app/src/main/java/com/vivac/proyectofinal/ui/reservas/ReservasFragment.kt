package com.vivac.proyectofinal.ui.reservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vivac.proyectofinal.databinding.FragmentReservasBinding

class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val reservasViewModel =
            ViewModelProvider(this).get(ReservasViewModel::class.java)

        _binding = FragmentReservasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReservas
        reservasViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}