package com.vivac.proyectofinal.ui.reservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.databinding.FragmentReservasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.gson.Gson

class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este Fragmento
        val rootView = inflater.inflate(R.layout.fragment_reservas, container, false)

        lifecycleScope.launch {
            val name = "María" // Reemplaza "John" con el nombre que desees

            try {
                val response = HttpClient(Android) {
                    // Configuración del cliente
                }.get<String>("http://127.0.0.1:8080/reservas"
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    text.text = e.message
                }

            }

            return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}