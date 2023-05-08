package com.vivac.proyectofinal.ui.localizacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocalizacionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui podras ver la localizacion y como llegar"
    }
    val text: LiveData<String> = _text
}