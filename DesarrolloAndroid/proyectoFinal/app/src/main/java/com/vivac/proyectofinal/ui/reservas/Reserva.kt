package com.vivac.proyectofinal.ui.reservas

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Reserva(var _id: String, var lugar: String, var dni: String, var nombre: String, var email: String, var telefono: String,  var dia: String, var hora_inicio: String,
                   var hora_fin: String, var fecha_inicio: String, var fecha_fin: String, var num_personas: String, var comentario: String)
