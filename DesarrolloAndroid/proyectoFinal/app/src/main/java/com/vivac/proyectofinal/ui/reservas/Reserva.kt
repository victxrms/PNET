package com.vivac.proyectofinal.ui.reservas

import android.provider.ContactsContract.CommonDataKinds.Email
import java.util.Date

data class Reserva(val nombreSala: String, val nombreCli: String, val dni: String,
                val email: Email, val telefono: String, val fechaIni: Date,
                val fechaFin: Date, val numPersonas: Int, val comentario: String)
