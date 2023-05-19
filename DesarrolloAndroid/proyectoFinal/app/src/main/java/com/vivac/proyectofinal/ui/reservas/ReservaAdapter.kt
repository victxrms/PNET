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
        val nombreCliTextView: TextView = itemView.findViewById(R.id.textViewNombreR)
        val dniTextView: TextView = itemView.findViewById(R.id.textViewDniR)
        val telefonoTextView: TextView = itemView.findViewById(R.id.textViewTelR)
        val emailTextView: TextView = itemView.findViewById(R.id.textViewEmailR)
        val fechaIniTextView: TextView = itemView.findViewById(R.id.textViewFechaIniR)
        val fechaFinTextView: TextView = itemView.findViewById(R.id.textViewFechaFinR)
        val numPersonasTextView: TextView = itemView.findViewById(R.id.textViewNumPersonasR)
        val comentarioTextView: TextView = itemView.findViewById(R.id.textViewComentarioR)
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
        holder.nombreCliTextView.text = currentReserva.dia
        //holder.dniTextView.text = currentReserva.dni
        //holder.telefonoTextView.text = currentReserva.telefono
        //holder.emailTextView.text = currentReserva.email.toString()
        holder.fechaIniTextView.text = currentReserva.hora_inicio
        holder.fechaFinTextView.text = currentReserva.hora_fin
        holder.numPersonasTextView.text = currentReserva.num_personas.toString()
        //holder.comentarioTextView.text = currentReserva.comentario
    }

    override fun getItemCount(): Int {
        return reservas.size
    }
}