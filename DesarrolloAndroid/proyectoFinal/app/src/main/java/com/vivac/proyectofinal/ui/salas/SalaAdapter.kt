package com.vivac.proyectofinal.ui.salas


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vivac.proyectofinal.R


class SalaAdapter(private var salas: List<Sala>) :


    RecyclerView.Adapter<SalaAdapter.SalaViewHolder>() {

    // Esta clase almacena las referencias de los elementos de la vista
    // (los widgets o views) que se muestran en cada elemento de la lista.
    inner class SalaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombreSala)
        val SalaDescView: TextView = itemView.findViewById(R.id.textViewDescSala)
    }

    constructor() : this(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_sala, parent, false)
        return SalaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SalaAdapter.SalaViewHolder, position: Int) {
        val currentSala = salas[position]
        holder.nombreTextView.text = currentSala.nombre
        holder.SalaDescView.text = currentSala.desc

    }
    override fun getItemCount() = salas.size
    fun setSalas(mascotas: List<Sala>) {
        this.salas = mascotas
    }

}