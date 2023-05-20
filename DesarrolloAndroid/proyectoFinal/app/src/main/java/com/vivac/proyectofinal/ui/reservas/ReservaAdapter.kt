package com.vivac.proyectofinal.ui.salas

import android.app.Activity
import android.content.Context
import android.util.Log
import com.vivac.proyectofinal.ui.reservas.Reserva
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.ui.reservas.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        val horaIniTextView: TextView = itemView.findViewById(R.id.textViewHoraIniR)
        val horaFinTextView: TextView = itemView.findViewById(R.id.textViewHoraFinR)
        val numPersonasTextView: TextView = itemView.findViewById(R.id.textViewNumPersonasR)
        val comentarioTextView: TextView = itemView.findViewById(R.id.textViewComentarioR)
        val verMas: Button = itemView.findViewById(R.id.verMas)
        val camposExtras: LinearLayout = itemView.findViewById(R.id.camposExtras)
        val eliminar: Button = itemView.findViewById(R.id.botonelimina)
        val editar: Button = itemView.findViewById(R.id.botonedita)
        val formulario: LinearLayout = itemView.findViewById(R.id.formularioLayout)

        val spinner = itemView.findViewById<Spinner>(R.id.spinner)
        val editFechaIniF = itemView.findViewById<DatePicker>(R.id.EditFechaIniF)
        val editFechaFinF = itemView.findViewById<DatePicker>(R.id.EditFechaFinF)
        val editHoraEntrada = itemView.findViewById<TimePicker>(R.id.EditHoraLlegadaF)
        val editHoraSalida = itemView.findViewById<TimePicker>(R.id.EditHoraSalida)
        val editNombreF = itemView.findViewById<EditText>(R.id.EditNombreF)
        val editDniF = itemView.findViewById<EditText>(R.id.EditDniF)
        val editEmailF = itemView.findViewById<EditText>(R.id.EditEmailF)
        val editTelefonoF = itemView.findViewById<EditText>(R.id.EditTelefonoF)
        val editNumPersonasF = itemView.findViewById<EditText>(R.id.EditNumPersonasF)
        val editComentarioF = itemView.findViewById<EditText>(R.id.EditComentarioF)

        val valueFIni = itemView.findViewById<TextView>(R.id.valorFechaIniF)
        val valueFFin = itemView.findViewById<TextView>(R.id.valorFechaFinF)
        val valueHIni = itemView.findViewById<TextView>(R.id.valorHoraEntrada)
        val valueHFin = itemView.findViewById<TextView>(R.id.valorHoraSalida)

        val botonValidar = itemView.findViewById<Button>(R.id.btnValidar)

        val layoutprincipal = itemView.findViewById<LinearLayout>(R.id.layoutPrincipal)


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
        holder.fechaIniTextView.text = "Fecha de inicio: " + currentReserva.fecha_inicio
        holder.fechaFinTextView.text = "Fecha de fin: " + currentReserva.fecha_fin
        holder.horaIniTextView.text = "Fecha de inicio: " + currentReserva.hora_inicio
        holder.horaFinTextView.text = "Fecha de fin: " + currentReserva.hora_fin
        holder.numPersonasTextView.text = "Número de personas: " + currentReserva.num_personas
        holder.comentarioTextView.text = "Comentario: " + currentReserva.comentario

        holder.spinner.setSelection(getSelectedLugarIndex(holder, currentReserva.lugar)) //con esto asigngamos el valor a la posicion a la que pertenece el lugar de la reserva


        //con esto metemos en el DatePicker el valor de la fecha de inicio
        val fechaInicioString = currentReserva.fecha_inicio
        holder.valueFIni.text = currentReserva.fecha_inicio
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaInicio = formatoFecha.parse(fechaInicioString)

        val fechaIniCalendar = Calendar.getInstance()
        fechaIniCalendar.time = fechaInicio
        val anoIni = fechaIniCalendar.get(Calendar.YEAR)
        val mesIni = fechaIniCalendar.get(Calendar.MONTH)
        val diaIni = fechaIniCalendar.get(Calendar.DAY_OF_MONTH)
        holder.editFechaIniF.updateDate(anoIni, mesIni, diaIni)

        //con esto metemos en el DatePicker el valor de la fecha de fin
        val fechaFinString = currentReserva.fecha_fin //
        holder.valueFFin.text = currentReserva.fecha_fin
        val fechaFin = formatoFecha.parse(fechaFinString)

        val fechaFinCalendar = Calendar.getInstance()
        fechaFinCalendar.time = fechaFin
        val anoFin = fechaFinCalendar.get(Calendar.YEAR)
        val mesFin = fechaFinCalendar.get(Calendar.MONTH)
        val diaFin = fechaFinCalendar.get(Calendar.DAY_OF_MONTH)
        holder.editFechaFinF.updateDate(anoFin, mesFin, diaFin)

        //con esto metemos en el DatePicker el valor de la hora de inicio
        val horaInicioString = currentReserva.hora_inicio
        holder.valueHIni.text = currentReserva.hora_inicio
        val formatoHora = SimpleDateFormat("HH:mm", Locale.getDefault())
        val horaInicio = formatoHora.parse(horaInicioString)

        // Obtener los componentes de hora y minutos de la hora de inicio
        val horaIniCalendar = Calendar.getInstance()
        horaIniCalendar.time = horaInicio
        val horaIni = horaIniCalendar.get(Calendar.HOUR_OF_DAY)
        val minIni = horaIniCalendar.get(Calendar.MINUTE)

        // Establecer hora de inicio en el TimePicker
        holder.editHoraEntrada.hour = horaIni
        holder.editHoraEntrada.minute = minIni

        //con esto metemos en el DatePicker el valor de la hora de inicio
        val horaFinString = currentReserva.hora_fin
        holder.valueHFin.text = currentReserva.hora_fin
        val horaFinal = formatoHora.parse(horaFinString)

        val horaFinCalendar = Calendar.getInstance()
        horaFinCalendar.time = horaFinal
        val horaFin = horaFinCalendar.get(Calendar.HOUR_OF_DAY)
        val minFin = horaFinCalendar.get(Calendar.MINUTE)

        holder.editHoraSalida.hour = horaFin
        holder.editHoraSalida.minute = minFin

        holder.editNombreF.setText(currentReserva.nombre)
        holder.editDniF.setText(currentReserva.dni)
        holder.editEmailF.setText(currentReserva.email)
        holder.editTelefonoF.setText(currentReserva.telefono)
        holder.editNumPersonasF.setText(currentReserva.num_personas)
        holder.editComentarioF.setText(currentReserva.comentario)


        val verMasButton: Button = holder.verMas
        val eliminar: Button = holder.eliminar
        val editar: Button = holder.editar

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

        var isPaddingModified = false

        editar.setOnClickListener {

            val layoutPrincipal: LinearLayout = holder.layoutprincipal

            if (isPaddingModified) {
                // Si el padding ya ha sido modificado, quitarlo
                layoutPrincipal.setPadding(0, 0, 0, 0)
                isPaddingModified = false
            } else {
                val paddingInDp = 300 // Valor del padding en dp

                // Obtiene el contexto de la actividad
                val context = holder.itemView.context

                // Calcula el valor en píxeles basado en los dp
                val scale = context.resources.displayMetrics.density
                val paddingInPixels = (paddingInDp * scale + 0.5f).toInt()

                // Establece el nuevo paddingBottom en el layout
                layoutPrincipal.setPadding(0, 0, 0, paddingInPixels)
                isPaddingModified = true
            }

            val formularioLayout: LinearLayout = holder.formulario
            val isVisible = formularioLayout.visibility == View.VISIBLE

            // Si los campos adicionales están visibles, los oculta; de lo contrario, los muestra
            formularioLayout.visibility = if (isVisible) View.GONE else View.VISIBLE

            // Cambiar la visibilidad de cada elemento dentro del LinearLayout camposExtras
            for (i in 0 until formularioLayout.childCount) {
                val child: View = formularioLayout.getChildAt(i)
                child.visibility = if (isVisible) View.GONE else View.VISIBLE
            }

            holder.botonValidar.setOnClickListener {

                val contexto = holder.itemView.context
                var formularioBien = true

                if (holder.editNombreF.text.isEmpty()) {
                    Toast.makeText(contexto, "Introduzca su nombre", Toast.LENGTH_SHORT)
                        .show()
                    formularioBien = false
                    return@setOnClickListener
                } else if (holder.editDniF.text.isEmpty()) {
                    Toast.makeText(contexto, "Introduzca su dni", Toast.LENGTH_SHORT)
                        .show()
                    formularioBien = false
                    return@setOnClickListener
                } else if (holder.editNumPersonasF.text.isEmpty()) {
                    Toast.makeText(contexto, "Introduzca un número de personas", Toast.LENGTH_SHORT)
                        .show()
                    formularioBien = false
                    return@setOnClickListener
                } else if (holder.editEmailF.text.isEmpty()) {
                    Toast.makeText(contexto, "¡Introduzca su email", Toast.LENGTH_SHORT)
                        .show()
                    formularioBien = false
                    return@setOnClickListener
                } else if (holder.editTelefonoF.text.isEmpty()) {
                    Toast.makeText(contexto, "Introduzca su teléfono", Toast.LENGTH_SHORT)
                        .show()
                    formularioBien = false
                    return@setOnClickListener
                } else if (holder.editFechaIniF.year == 0 || holder.editFechaIniF.month == 0 || holder.editFechaIniF.dayOfMonth == 0) {
                    Toast.makeText(contexto, "Introduzca una fecha de inicio", Toast.LENGTH_SHORT
                    ).show()
                    formularioBien = false
                    return@setOnClickListener
                } else if (holder.editFechaFinF.year == 0 || holder.editFechaFinF.month == 0 || holder.editFechaFinF.dayOfMonth == 0) {
                    Toast.makeText(
                        contexto, "Introduzca una fecha de fin", Toast.LENGTH_SHORT
                    ).show()
                    formularioBien = false
                    return@setOnClickListener
                }

                if (fechaInicio == fechaFin && horaIni >= horaFin) {
                    Toast.makeText(
                        contexto, "La hora de salida debe ser posterior a la hora de llegada", Toast.LENGTH_SHORT
                    ).show()
                    formularioBien = false
                    return@setOnClickListener
                }

                // Verificar si la fecha de salida es anterior a la fecha de entrada
                if (fechaFin.before(fechaInicio)) {
                    Toast.makeText(contexto, "La fecha de salida no puede ser anterior a la fecha de entrada", Toast.LENGTH_SHORT
                    ).show()
                    formularioBien = false
                    return@setOnClickListener
                }

                if (formularioBien)
                {
                    Toast.makeText(holder.itemView.context, "Editando reserva", Toast.LENGTH_SHORT).show()
                    (contexto as Activity).recreate()
                    GlobalScope.launch(Dispatchers.IO) {
                        val respuesta = editaReserva(
                            currentReserva._id,
                            holder.spinner.selectedItem.toString(),
                            holder.editNombreF.text.toString(),
                            holder.editDniF.text.toString(),
                            holder.editEmailF.text.toString(),
                            holder.editTelefonoF.text.toString(),
                            holder.editNumPersonasF.text.toString(),
                            holder.editComentarioF.text.toString(),
                            obtenerFechaDesdeDatePicker(holder.editFechaIniF),
                            obtenerFechaDesdeDatePicker(holder.editFechaFinF),
                            obtenerHoraDesdeTimePicker(holder.editHoraEntrada),
                            obtenerHoraDesdeTimePicker(holder.editHoraSalida))

                        withContext(Dispatchers.Main) {
                            val contexto = holder.itemView.context
                            if (respuesta == "0") {
                                Toast.makeText(holder.itemView.context, "Ha habido un error", Toast.LENGTH_SHORT).show()
                                (contexto as Activity).recreate()
                            }
                            else {
                                Toast.makeText(holder.itemView.context, "Reserva actualizada correctamente", Toast.LENGTH_SHORT).show()
                                (contexto as Activity).recreate()
                            }
                        }
                    }
                }



            }


        }

    }

    suspend private fun eliminaReserva(id: String): String{
        Thread.sleep(2000)
        Log.d("FETCH", "Función ejecutada eliminar");
        val service = ApiService()
        var respuesta = service.eliminaReserva(id)
        return respuesta
    }

    suspend private fun editaReserva(idReserva: String, lugar: String, nombre: String, dni: String, correo: String, telefono: String, numPersonas: String, comentario: String, fInicio: String, fFin: String, hInicio: String, hFin: String): String{
        Thread.sleep(2000)
        Log.d("FETCH", "Función ejecutada editar");
        val service = ApiService()
        var respuesta = service.editaReserva(idReserva, lugar, nombre, dni, correo, telefono, numPersonas, comentario, fInicio, fFin, hInicio, hFin)
        return respuesta
    }


    //con esta funcion cogemos el indice que ocupa el lugar en el spinner
    private fun getSelectedLugarIndex(holder: ReservaViewHolder, lugar: String): Int {
        val lugaresArray = holder.itemView.context.resources.getStringArray(R.array.lugares)
        for (i in lugaresArray.indices) {
            if (lugaresArray[i] == lugar) {
                return i
            }
        }
        return 0 // Devuelve la posición predeterminada si no se encuentra el lugar en el array
    }

    //con estas funciones parseamos los datos del datepicker
    private fun obtenerFechaDesdeDatePicker(datePicker: DatePicker): String {
        val dia = datePicker.dayOfMonth
        val mes = datePicker.month + 1 // Los meses en DatePicker comienzan desde 0
        val anio = datePicker.year

        return "$dia/$mes/$anio" // Ajusta el formato según tus necesidades
    }

    private fun obtenerHoraDesdeTimePicker(timePicker: TimePicker): String {
        val hora = timePicker.hour
        val minuto = timePicker.minute

        return String.format("%02d:%02d", hora, minuto) // Formatea la hora según tus necesidades
    }

    override fun getItemCount(): Int {
        return reservas.size
    }
}