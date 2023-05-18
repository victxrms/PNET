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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.databinding.FragmentReservasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.vivac.proyectofinal.ui.salas.ReservaAdapter
import com.vivac.proyectofinal.ui.salas.SalaAdapter
import com.vivac.proyectofinal.ui.salas.SalasViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.http.ContentType.Application.Json
import kotlinx.serialization.json.Json

class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ReservasViewModel
    private lateinit var recyclerView: RecyclerView

    private val reservasList: MutableLiveData<List<Reserva>> = MutableLiveData<List<Reserva>>().apply {
        value = emptyList()
    }
    private var listaReservas: List<Reserva> = emptyList()
    private var adapter =  ReservaAdapter(listaReservas)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este Fragmento
        val rootView = inflater.inflate(R.layout.fragment_reservas, container, false)

        lifecycleScope.launch {

            val client = HttpClient(Android) {
                // Agregamos el módulo de serialización JSON
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
            // Hacemos la solicitud GET utilizando el cliente

            val response: List<Reserva> =  client.get("http://127.0.0.1:8080/reservas")

            withContext(Dispatchers.Main) {
                listaReservas = response
                adapter = ReservaAdapter(listaReservas)
                adapter.setReservas(listaReservas)
                reservasList.value = listaReservas
                viewModel.getlist(reservasList)
            }

        }
        return rootView
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel = ViewModelProvider(this).get(ReservasViewModel::class.java)
            adapter.setReservas(listaReservas) //no está inicializada
            reservasList.value = listaReservas
            viewModel.getlist(reservasList)



            viewModel.getReservas().observe(viewLifecycleOwner) { listaReservas ->
                adapter.setReservas(listaReservas)
                adapter.notifyDataSetChanged()

            }

            recyclerView = view.findViewById(R.id.recyclerViewR)
            adapter = ReservaAdapter()

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}