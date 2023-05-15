package com.vivac.proyectofinal.ui.salas


import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.ui.reservas.Reserva


class ReservaAdapter(private var reservas: List<Reserva>) :


    RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {
    inner class ReservaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    constructor() : this(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_sala, parent, false)
        return ReservaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservaAdapter.ReservaViewHolder, position: Int) {
        val currentReserva = reservas[position]
        holder.nombreSalaTextView.text = currentReserva.nombreSala
        holder.nombreCliTextView.text = currentReserva.nombreCli
        holder.dniTextView.text = currentReserva.dni
        holder.telefonoTextView.text = currentReserva.telefono
        holder.emailTextView.text = currentReserva.email.toString()
        holder.fechaIniTextView.text = currentReserva.fechaIni.toString()
        holder.fechaFinTextView.text = currentReserva.fechaFin.toString()
        holder.numPersonasTextView.text = currentReserva.numPersonas.toString()
        holder.comentarioTextView.text = currentReserva.comentario



    }
    override fun getItemCount() = reservas.size
    fun setReservas(reservas: List<Reserva>) {
        this.reservas = reservas
    }

}