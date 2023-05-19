package com.vivac.proyectofinal.ui.salas

import com.vivac.proyectofinal.ui.reservas.Reserva
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vivac.proyectofinal.R

class ReservaAdapter (private val reservas:List<Reserva>): RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {
    class ReservaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nombreSalaTextView: TextView = itemView.findViewById(R.id.textViewNombreSalaR)
        val idTextView: TextView = itemView.findViewById(R.id.textViewIDReservaR)
        val nombreCliTextView: TextView = itemView.findViewById(R.id.textViewNombreR)
        /*
        val dniTextView: TextView = itemView.findViewById(R.id.textViewDniR)
        val telefonoTextView: TextView = itemView.findViewById(R.id.textViewTelR)
        val emailTextView: TextView = itemView.findViewById(R.id.textViewEmailR)
        val fechaIniTextView: TextView = itemView.findViewById(R.id.textViewFechaIniR)
        val fechaFinTextView: TextView = itemView.findViewById(R.id.textViewFechaFinR)
        val numPersonasTextView: TextView = itemView.findViewById(R.id.textViewNumPersonasR)
        val comentarioTextView: TextView = itemView.findViewById(R.id.textViewComentarioR)

         */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_reserva,parent,false)
        return ReservaViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ReservaViewHolder,
        position: Int,
    ) {
        val currentReserva = reservas[position]
        holder.nombreSalaTextView.text = currentReserva.lugar
        holder.idTextView.text = "ID de la reserva: " + currentReserva._id
        holder.nombreCliTextView.text = "Cliente: " + currentReserva.nombre
        /*
        holder.dniTextView.text = "DNI: " + currentReserva.dni
        holder.telefonoTextView.text = "Teléfono: " + currentReserva.telefono
        holder.emailTextView.text = "Email: " + currentReserva.email
        holder.fechaIniTextView.text = "Fecha de inicio: " + currentReserva.hora_inicio
        holder.fechaFinTextView.text = "Fecha de fin: " + currentReserva.hora_fin
        holder.numPersonasTextView.text = "Número de personas: " + currentReserva.num_personas
        holder.comentarioTextView.text = "Comentario: " + currentReserva.comentario

         */
    }

    override fun getItemCount(): Int {
        return reservas.size
    }
}