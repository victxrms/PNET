package com.vivac.proyectofinal.ui.reservas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.ui.salas.Sala

class ReservasViewModel() : ViewModel() {
    private lateinit var reservasLiveData: MutableLiveData<List<Reserva>>

    fun getlist(lista: MutableLiveData<List<Reserva>>){
        reservasLiveData = lista
    }

    fun getReservas(): LiveData<List<Reserva>> {
        return reservasLiveData
    }

}