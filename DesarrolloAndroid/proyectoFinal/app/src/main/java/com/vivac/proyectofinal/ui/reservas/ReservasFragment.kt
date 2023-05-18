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
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.databinding.FragmentReservasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.gson.Gson
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReservaAdapter
    private lateinit var viewModel: ReservasViewModel

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
            val response = client.get<Reserva>("https://ejemplo.com/api/data")

            val json = Json { ignoreUnknownKeys = true }
            // Define el objeto JSON como una cadena de texto
            val jsonString = """{"id":1,"name":"John Doe","age":30}"""
            // Deserializa el objeto JSON en una instancia de la clase MyData
            val myData = json.decodeFromString<Reserva>(jsonString)
            // Ahora puedes acceder a las propiedades de la instancia de MyData
            println("ID: ${myData.nombreSala}, Name: ${myData.name}, Age: ${myData.age}")


        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel = ViewModelProvider(this).get(ReservasViewModel::class.java)

            viewModel.getReservas().observe(viewLifecycleOwner) { reservas ->
                adapter.setReservas(reservas)
                adapter.notifyDataSetChanged()
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}