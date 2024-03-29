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


class SalaAdapter(private var salas: List<Sala>) :


    RecyclerView.Adapter<SalaAdapter.SalaViewHolder>() {

    // Esta clase almacena las referencias de los elementos de la vista
    // (los widgets o views) que se muestran en cada elemento de la lista.
    inner class SalaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombreSala)
        val SalaSubtituloView: TextView = itemView.findViewById(R.id.textViewSubtitulo)
        val SalaDescView: TextView = itemView.findViewById(R.id.textViewDescSala)
        val tablaAgua: TextView = itemView.findViewById(R.id.textTablaAgua)
        val tablaLuz: TextView = itemView.findViewById(R.id.textTablaLuz)
        val tablaPiscina: TextView = itemView.findViewById(R.id.textTablaPiscina)
        val tablaSombra: TextView = itemView.findViewById(R.id.textTablaSombra)
        val imagenSala: ImageView = itemView.findViewById(R.id.imagenViewSala)
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
        holder.SalaSubtituloView.text = currentSala.subtitulo
        holder.SalaDescView.text = currentSala.desc
        holder.tablaAgua.text = currentSala.agua
        holder.tablaLuz.text = currentSala.luz
        holder.tablaPiscina.text = currentSala.piscina
        holder.tablaSombra.text = currentSala.sombra
        holder.imagenSala.setImageResource(currentSala.imagen)


    }
    override fun getItemCount() = salas.size
    fun setSalas(mascotas: List<Sala>) {
        this.salas = mascotas
    }

}