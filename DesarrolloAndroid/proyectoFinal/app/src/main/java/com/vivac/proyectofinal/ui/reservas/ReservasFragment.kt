package com.vivac.proyectofinal.ui.reservas


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
<<<<<<< HEAD
=======
import androidx.lifecycle.lifecycleScope
>>>>>>> ae9bf2f2cf13b83f6835a672a3d2fe5492b9fd59
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivac.proyectofinal.databinding.FragmentReservasBinding
import com.vivac.proyectofinal.ui.salas.ReservaAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
<<<<<<< HEAD
=======
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
>>>>>>> ae9bf2f2cf13b83f6835a672a3d2fe5492b9fd59

class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null

<<<<<<< HEAD
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
=======
    private lateinit var viewModel: ReservasViewModel
    private lateinit var recyclerView: RecyclerView

    private val reservasList: MutableLiveData<List<Reserva>> = MutableLiveData<List<Reserva>>().apply {
        value = emptyList()
    }
    private var listaReservas: List<Reserva> = emptyList()
    private var adapter =  ReservaAdapter(listaReservas)


>>>>>>> ae9bf2f2cf13b83f6835a672a3d2fe5492b9fd59

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ReservasViewModel::class.java)

        _binding = FragmentReservasBinding.inflate(inflater, container, false)
        val root: View = binding.root

<<<<<<< HEAD
        var reservas : List<Reserva>
        val recyclerView: RecyclerView = binding.recyclerView
        val context = requireContext()
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch(Dispatchers.IO) {
            reservas = fetchBookings()
            val adapter = ReservaAdapter(reservas)
            withContext(Dispatchers.Main){
                recyclerView.adapter = adapter
=======
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

>>>>>>> ae9bf2f2cf13b83f6835a672a3d2fe5492b9fd59
            }

            recyclerView = view.findViewById(R.id.recyclerViewR)
            adapter = ReservaAdapter()

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    suspend private fun fetchBookings(): List<Reserva>{
        Thread.sleep(2000)
        Log.d("FETCH", "Función ejecutada");
        val service = ApiService()
        var reservas = service.getReservas()
        return reservas
    }
}