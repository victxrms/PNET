package com.vivac.proyectofinal.ui.reservas

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.serialization.kotlinx.json.json
import java.lang.Exception
import java.lang.reflect.Type


class ApiService {
    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json()
        }
    }

    private val gson = Gson()

    suspend fun getReservas(): List<Reserva> {
        try {
            val response: HttpResponse = client.get("http://10.182.104.84:8080/reservas")
            val jsonReservas = response.bodyAsText()
            Log.d("LOG GETCALL", response.body())
            val type: Type = object : TypeToken<List<Reserva>>() {}.type
            val reservas: List<Reserva> = gson.fromJson(jsonReservas, type)
            return reservas
        } catch (ex: Exception) {
            ex.printStackTrace()
            return emptyList()
        }
    }
}