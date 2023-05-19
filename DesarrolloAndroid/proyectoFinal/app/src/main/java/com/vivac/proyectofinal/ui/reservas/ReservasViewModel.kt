package com.vivac.proyectofinal.ui.reservas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

<<<<<<< HEAD
class ReservasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Reservas Fragment"
    }
    val text: LiveData<String> = _text
=======
class ReservasViewModel() : ViewModel() {
    private lateinit var reservasLiveData: MutableLiveData<List<Reserva>>

    fun getlist(lista: MutableLiveData<List<Reserva>>){
        reservasLiveData = lista
    }

    fun getReservas(): LiveData<List<Reserva>> {
        return reservasLiveData
    }

>>>>>>> ae9bf2f2cf13b83f6835a672a3d2fe5492b9fd59
}