package com.vivac.proyectofinal.ui.salas


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SalasViewModel : ViewModel() {
    private val salasLiveData = MutableLiveData<List<Sala>>()

    init {
        val salas = listOf(
            Sala("Acebedo", "Ubicado en medio de un frondoso bosque, este campamento te brinda la oportunidad de acampar rodeado de árboles majestuosos y respirar aire fresco."),
            Sala("Cerromulera", "Si prefieres algo más cómodo, este glamping en la pradera es perfecto para ti. Acampa junto a tu coche disfrutas de la belleza de la naturaleza que te rodea."),
            Sala("Ordesa", "Si estás buscando un lugar para alejarte de la civilización, este campamento en el bosque es perfecto para ti. Disfruta de la serenidad del bosque mientras acampas bajo un cielo lleno de estrellas."),
            Sala("Bueida", "Nuestro camping ofrece una vista impresionante de la puesta de sol. Disfrute de la belleza natural mientras se relaja en su tienda de campaña o caravana."),
            Sala("Covão D'Ametade", "Situado en las alturas, este refugio de montaña te ofrece una experiencia única de acampada en un entorno natural, con vistas espectaculares de las montañas circundantes."),
            Sala("Navaleno", "Este campamento se encuentra en las orillas de un río tranquilo y te permite disfrutar de la naturaleza y el agua al mismo tiempo. Además, puedes pescar y hacer otras actividades acuáticas.")
        )
        salasLiveData.value = salas
    }

    fun getSalas(): LiveData<List<Sala>> {
        return salasLiveData
    }
}