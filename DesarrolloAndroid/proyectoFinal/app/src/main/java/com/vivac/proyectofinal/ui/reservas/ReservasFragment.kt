package com.vivac.proyectofinal.ui.reservas




import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.vivac.proyectofinal.databinding.FragmentReservasBinding
import com.vivac.proyectofinal.ui.salas.ReservaAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ReservasViewModel::class.java)

        _binding = FragmentReservasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var reservas : List<Reserva>
        val recyclerView: RecyclerView = binding.recyclerViewR
        val context = requireContext()
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch(Dispatchers.IO) {
            reservas = fetchBookings()
            val adapter = ReservaAdapter(reservas)
            withContext(Dispatchers.Main){
                recyclerView.adapter = adapter
            }
        }

        Toast.makeText(getContext(), "Cargando reservas", Toast.LENGTH_SHORT).show()

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

    suspend private fun fetchBook(id: String): Reserva?{
        Thread.sleep(2000)
        Log.d("FETCH", "Función ejecutada");
        val service = ApiService()
        var reserva: Reserva? = service.getReservaPorId(id)
        return reserva
    }
}