package com.vivac.proyectofinal.ui.reservas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui podras ver las reservas"
    }
    val text: LiveData<String> = _text
}