package com.vivac.proyectofinal.ui.reservas

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
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
            val response: HttpResponse = client.get("http://192.168.3.58:8080/reservas")
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

    suspend fun getReservaPorId(nuevaReservaId: String): Reserva? {
        try {
            val url = "http://192.168.3.58:8080/reservas/$nuevaReservaId"
            val response: HttpResponse = client.get(url)
            val jsonReserva = response.bodyAsText()
            Log.d("LOG GETCALL", response.body())
            return gson.fromJson(jsonReserva, Reserva::class.java)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
    }

    suspend fun eliminaReserva(nuevaReservaId: String): String {
        try {
            val url = "http://192.168.3.58:8080/reservas/$nuevaReservaId"
            val response: HttpResponse = client.delete(url)
            val jsonReserva = response.bodyAsText()
            Log.d("LOG GETCALL", response.body())
            return jsonReserva
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "0"
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun nuevaReserva(lugar: String, nombre: String, dni: String, correo: String, telefono: String, numPersonas: String, comentario: String, fInicio: String, fFin: String, hInicio: String, hFin: String): String {
        try {
            val url = "http://192.168.3.58:8080/reservas"
            val requestcuerpo = JsonObject().apply {
                addProperty("lugar", lugar)
                addProperty("dni", dni)
                addProperty("nombre", nombre)
                addProperty("email", correo)
                addProperty("telefono", telefono)
                addProperty("fecha_inicio", fInicio)
                addProperty("fecha_fin", fFin)
                addProperty("hora_inicio", hInicio)
                addProperty("hora_fin", hFin)
                addProperty("num_personas", numPersonas)
                addProperty("comentario", comentario)
            }

            val gson = Gson()
            val requestBody = gson.toJson(requestcuerpo)

            val response: HttpResponse = client.request(url) {
                method = HttpMethod.Post
                headers.append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                body = TextContent(requestBody, ContentType.Application.Json)
            }

            val jsonReserva = response.bodyAsText()
            Log.d("LOG GETCALL", response.body())
            return jsonReserva
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "0"
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun editaReserva(lugar: String, nombre: String, dni: String, correo: String, telefono: String, numPersonas: String, comentario: String, fInicio: String, fFin: String, hInicio: String, hFin: String): String {
        try {
            val url = "http://192.168.3.58:8080/reservas"
            val requestcuerpo = JsonObject().apply {
                addProperty("lugar", lugar)
                addProperty("dni", dni)
                addProperty("nombre", nombre)
                addProperty("email", correo)
                addProperty("telefono", telefono)
                addProperty("fecha_inicio", fInicio)
                addProperty("fecha_fin", fFin)
                addProperty("hora_inicio", hInicio)
                addProperty("hora_fin", hFin)
                addProperty("num_personas", numPersonas)
                addProperty("comentario", comentario)
            }

            val gson = Gson()
            val requestBody = gson.toJson(requestcuerpo)

            val response: HttpResponse = client.request(url) {
                method = HttpMethod.Put
                headers.append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                body = TextContent(requestBody, ContentType.Application.Json)
            }

            val jsonReserva = response.bodyAsText()
            Log.d("LOG GETCALL", response.body())
            return jsonReserva
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "0"
        }
    }
}