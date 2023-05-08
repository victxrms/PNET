package com.vivac.proyectofinal.ui.salas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SalasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui encontraras todo sobre los diferentes campings"
    }
    val text: LiveData<String> = _text
}