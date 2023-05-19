package com.vivac.proyectofinal.ui.salas

import android.app.Activity
import android.content.Context
import android.util.Log
import com.vivac.proyectofinal.ui.reservas.Reserva
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.ui.reservas.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservaAdapter (private val reservas:List<Reserva>): RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {
    class ReservaViewHolder(itemView:View, context: Context):RecyclerView.ViewHolder(itemView){
        val nombreSalaTextView: TextView = itemView.findViewById(R.id.textViewNombreSalaR)
        val idTextView: TextView = itemView.findViewById(R.id.textViewIDReservaR)
        val nombreCliTextView: TextView = itemView.findViewById(R.id.textViewNombreR)

        val dniTextView: TextView = itemView.findViewById(R.id.textViewDniR)
        val telefonoTextView: TextView = itemView.findViewById(R.id.textViewTelR)
        val emailTextView: TextView = itemView.findViewById(R.id.textViewEmailR)
        val fechaIniTextView: TextView = itemView.findViewById(R.id.textViewFechaIniR)
        val fechaFinTextView: TextView = itemView.findViewById(R.id.textViewFechaFinR)
        val numPersonasTextView: TextView = itemView.findViewById(R.id.textViewNumPersonasR)
        val comentarioTextView: TextView = itemView.findViewById(R.id.textViewComentarioR)
        val verMas: Button = itemView.findViewById(R.id.verMas)
        val camposExtras: LinearLayout = itemView.findViewById(R.id.camposExtras)
        val eliminar: Button = itemView.findViewById(R.id.botonelimina)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_reserva,parent,false)
        val contexto = parent.context

        return ReservaViewHolder(itemView, contexto)
    }

    override fun onBindViewHolder(
        holder: ReservaViewHolder,
        position: Int,
    ) {
        val currentReserva = reservas[position]
        holder.nombreSalaTextView.text = currentReserva.lugar
        holder.idTextView.text = "ID de la reserva: " + currentReserva._id
        holder.nombreCliTextView.text = "Cliente: " + currentReserva.nombre

        holder.dniTextView.text = "DNI: " + currentReserva.dni
        holder.telefonoTextView.text = "Teléfono: " + currentReserva.telefono
        holder.emailTextView.text = "Email: " + currentReserva.email
        holder.fechaIniTextView.text = "Fecha de inicio: " + currentReserva.hora_inicio
        holder.fechaFinTextView.text = "Fecha de fin: " + currentReserva.hora_fin
        holder.numPersonasTextView.text = "Número de personas: " + currentReserva.num_personas
        holder.comentarioTextView.text = "Comentario: " + currentReserva.comentario

        val verMasButton: Button = holder.verMas
        val eliminar: Button = holder.eliminar

        verMasButton.setOnClickListener {
            val fieldsLayout: LinearLayout = holder.camposExtras
            val isVisible = fieldsLayout.visibility == View.VISIBLE

            // Si los campos adicionales están visibles, los oculta; de lo contrario, los muestra
            fieldsLayout.visibility = if (isVisible) View.GONE else View.VISIBLE

            // Cambiar la visibilidad de cada elemento dentro del LinearLayout camposExtras
            for (i in 0 until fieldsLayout.childCount) {
                val child: View = fieldsLayout.getChildAt(i)
                child.visibility = if (isVisible) View.GONE else View.VISIBLE
            }
        }

        eliminar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val respuesta = eliminaReserva(currentReserva._id)

                withContext(Dispatchers.Main) {
                    val contexto = holder.itemView.context
                    if (respuesta == "0") {
                        Toast.makeText(holder.itemView.context, "Ha habido un error, ese id no existe", Toast.LENGTH_SHORT).show()
                        (contexto as Activity).recreate()
                    }
                    else {
                        Toast.makeText(holder.itemView.context, "Reserva eliminada correctamente", Toast.LENGTH_SHORT).show()
                        (contexto as Activity).recreate()
                    }
                }
            }
        }

    }

    suspend private fun eliminaReserva(id: String): String{
        Thread.sleep(2000)
        Log.d("FETCH", "Función ejecutada");
        val service = ApiService()
        var respuesta = service.eliminaReserva(id)
        return respuesta
    }

    override fun getItemCount(): Int {
        return reservas.size
    }
}