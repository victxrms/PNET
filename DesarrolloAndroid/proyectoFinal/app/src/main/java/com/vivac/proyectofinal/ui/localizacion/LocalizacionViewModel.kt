package com.vivac.proyectofinal.ui.localizacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocalizacionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí podrás ver la localización de la empresa y como llegar."
    }
    val text: LiveData<String> = _text




}