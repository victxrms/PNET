package com.vivac.proyectofinal.ui.reservas

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.get
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import io.ktor.client.request.get

class ApiService {
        private val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    // Creamos una función suspend para hacer la petición GET que utiliza ktor-client para hacer una solicitud GET a una API RESTful
    suspend fun getExample() {
        // Creamos una instancia del cliente HTTP
        val client = HttpClient(Android) {
            // Agregamos el módulo de serialización JSON
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
        // Hacemos la solicitud GET utilizando el cliente
        val response = client.get<MyData>("https://ejemplo.com/api/data")
        // Procesamos la respuesta
        // ...
    }
    // Creamos una data class para deserializar la respuesta JSON
    data class MyData(val id: Int, val name: String, val age: Int)
}