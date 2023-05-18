package com.vivac.proyectofinal.ui.reservas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.ui.salas.Sala

class ReservasViewModel : ViewModel() {
    private val reservasLiveData = MutableLiveData<List<Reserva>>()

    init {
        val reservas = listOf(
            Reserva("Acebedo", "Para grupos grandes", "Si", "No", "Si", "Si", "Ubicado en medio de un frondoso bosque, este campamento te brinda la oportunidad de acampar rodeado de árboles majestuosos y respirar aire fresco.", R.drawable.sala1),
            Reserva("Cerromulera", "Un campamento virgen", "Si", "No", "No", "Si", "Si prefieres algo más cómodo, este glamping en la pradera es perfecto para ti. Acampa junto a tu coche disfrutas de la belleza de la naturaleza que te rodea.", R.drawable.sala2),
            Reserva("Ordesa", "Un lugar de ensueño", "Si", "Si", "Si", "Si", "Si estás buscando un lugar para alejarte de la civilización, este campamento en el bosque es perfecto para ti. Disfruta de la serenidad del bosque mientras acampas bajo un cielo lleno de estrellas.", R.drawable.sala3),
            Reserva("Bueida", "Llévate un chubasquero", "Si", "Si", "No", "Si", "Nuestro camping ofrece una vista impresionante de la puesta de sol. Disfrute de la belleza natural mientras se relaja en su tienda de campaña o caravana.", R.drawable.sala4),
            Reserva("Covão D'Ametade", "Perfecto para ver las estrellas", "Si", "No", "No", "Si", "Situado en las alturas, este refugio de montaña te ofrece una experiencia única de acampada en un entorno natural, con vistas espectaculares de las montañas circundantes.", R.drawable.sala5),
            Reserva("Navaleno", "Repleto de recursos a su alrededor", "No", "No", "Si", "Si", "Este campamento se encuentra en las orillas de un río tranquilo y te permite disfrutar de la naturaleza y el agua al mismo tiempo. Además, puedes pescar y hacer otras actividades acuáticas.", R.drawable.sala6)
        )
        reservasLiveData.value = reservas
    }

    fun getReservas(): LiveData<List<Reserva>> {
        return reservasLiveData
    }
}