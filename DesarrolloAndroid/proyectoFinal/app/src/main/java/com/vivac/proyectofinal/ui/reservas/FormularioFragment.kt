package com.vivac.proyectofinal.ui.reservas.formulario

import android.R
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vivac.proyectofinal.ui.reservas.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class FormularioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este Fragmento
        val rootView =
            inflater.inflate(com.vivac.proyectofinal.R.layout.fragment_formulario, container, false)

        val btnMostrarDatePicker: Button =
            rootView.findViewById(com.vivac.proyectofinal.R.id.btnMostrarDatePicker)
        val datePicker: DatePicker =
            rootView.findViewById(com.vivac.proyectofinal.R.id.EditFechaIniF)
        val valorPicker: TextView =
            rootView.findViewById(com.vivac.proyectofinal.R.id.valorFechaIniF)

        btnMostrarDatePicker.setOnClickListener {
            if (datePicker.visibility == View.VISIBLE) {
                datePicker.visibility = View.GONE
            } else {
                datePicker.visibility = View.VISIBLE
            }
        }

        datePicker.init(datePicker.year, datePicker.month, datePicker.dayOfMonth) { _, year, monthOfYear, dayOfMonth ->
            datePicker.visibility = View.GONE

            // Obtener los componentes del datePicker
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateStr = dateFormat.format(calendar.time)

            // Asignar el valor al texto de valorPicker
            valorPicker.text = dateStr
        }

        val btnMostrarDatePickerFin: Button =
            rootView.findViewById(com.vivac.proyectofinal.R.id.btnDatePickerFin)
        val datePickerFin: DatePicker =
            rootView.findViewById(com.vivac.proyectofinal.R.id.EditFechaFinF)
        val valorPickerFin: TextView =
            rootView.findViewById(com.vivac.proyectofinal.R.id.valorFechaFinF)

        btnMostrarDatePickerFin.setOnClickListener {
            if (datePickerFin.visibility == View.VISIBLE) {
                datePickerFin.visibility = View.GONE
            } else {
                datePickerFin.visibility = View.VISIBLE
            }
        }

        datePickerFin.init(datePickerFin.year, datePickerFin.month, datePickerFin.dayOfMonth) { _, year, monthOfYear, dayOfMonth ->
            datePickerFin.visibility = View.GONE

            // Obtener los componentes del datePickerFin
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateStr = dateFormat.format(calendar.time)

            // Asignar el valor al texto de valorPickerFin
            valorPickerFin.text = dateStr
        }



        val btnMostrarTimePickerLlegada: Button =
            rootView.findViewById(com.vivac.proyectofinal.R.id.btnTimePickerLlegada)
        val TimePickerLlegada: TimePicker =
            rootView.findViewById(com.vivac.proyectofinal.R.id.EditHoraLlegadaF)
        val valorHoraLlegada: TextView =
            rootView.findViewById(com.vivac.proyectofinal.R.id.valorHoraEntrada)

        btnMostrarTimePickerLlegada.setOnClickListener {
            if (TimePickerLlegada.visibility == View.VISIBLE) {
                TimePickerLlegada.visibility = View.GONE
            } else {
                TimePickerLlegada.visibility = View.VISIBLE
            }
        }

        TimePickerLlegada.setOnTimeChangedListener { _, hourOfDay, minute ->
            // Formatear la hora seleccionada
            val timeStr = String.format("%02d:%02d", hourOfDay, minute)
            // Asignar el valor al texto de valorHoraLlegada
            valorHoraLlegada.text = timeStr
        }


        val btnMostrarTimePickerSalida: Button =
            rootView.findViewById(com.vivac.proyectofinal.R.id.btnTimePickerSalida)
        val TimePickerSalida: TimePicker =
            rootView.findViewById(com.vivac.proyectofinal.R.id.EditHoraSalida)
        val valorHoraSalida: TextView =
            rootView.findViewById(com.vivac.proyectofinal.R.id.valorHoraSalida)

        btnMostrarTimePickerSalida.setOnClickListener {
            if (TimePickerSalida.visibility == View.VISIBLE) {
                TimePickerSalida.visibility = View.GONE
            } else {
                TimePickerSalida.visibility = View.VISIBLE
            }
        }

        // Obtener el valor seleccionado del TimePicker de salida
        TimePickerSalida.setOnTimeChangedListener { _, hourOfDay, minute ->
            // Formatear la hora seleccionada
            val timeStr = String.format("%02d:%02d", hourOfDay, minute)
            // Asignar el valor al texto de valorHoraSalida
            valorHoraSalida.text = timeStr
        }



        // Buscar el botón en la vista inflada y asignar un ClickListener
        val btnValidar = rootView.findViewById<Button>(com.vivac.proyectofinal.R.id.btnValidar)
        btnValidar.setOnClickListener {
            val lugar = rootView.findViewById<Spinner>(com.vivac.proyectofinal.R.id.spinner)
            val nombre =
                rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditNombreF)
            val dni = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditDniF)
            val email = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditEmailF)
            val telefono =
                rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditTelefonoF)
            val fechaini =
                rootView.findViewById<DatePicker>(com.vivac.proyectofinal.R.id.EditFechaIniF)
            val fechafin =
                rootView.findViewById<DatePicker>(com.vivac.proyectofinal.R.id.EditFechaFinF)
            val horaini =
                rootView.findViewById<TimePicker>(com.vivac.proyectofinal.R.id.EditHoraLlegadaF)
            val horafin =
                rootView.findViewById<TimePicker>(com.vivac.proyectofinal.R.id.EditHoraSalida)
            val numPersonas =
                rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditNumPersonasF)
            val comentario =
                rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditComentarioF)


            val lugarValue = lugar.selectedItem.toString()
            val nombreValue = nombre.text.toString()
            val dniValue = dni.text.toString()
            val emailValue = email.text.toString()
            val telefonoValue = telefono.text.toString()
            val numPersonasValue = numPersonas.text.toString()
            val comentarioValue = comentario.text.toString()

            val fInicioValue = "${fechaini.dayOfMonth}/${fechaini.month + 1}/${fechaini.year}"
            val fFinValue = "${fechafin.dayOfMonth}/${fechafin.month + 1}/${fechafin.year}"
            val hInicioValue = "${horaini.hour}:${horaini.minute}"
            val hFinValue = "${horafin.hour}:${horafin.minute}"

            fechaini.init(
                fechaini.year,
                fechaini.month,
                fechaini.dayOfMonth
            ) { view, year, monthOfYear, dayOfMonth ->
                Toast.makeText(
                    requireActivity(),
                    "Fecha de inicio seleccionada: $dayOfMonth/${monthOfYear + 1}/$year",
                    Toast.LENGTH_SHORT
                ).show()
            }

            fechafin.init(
                fechafin.year,
                fechafin.month,
                fechafin.dayOfMonth
            ) { view, yearfin, monthOfYearfin, dayOfMonthfin ->
                Toast.makeText(
                    requireActivity(),
                    "Fecha de fin seleccionada: $dayOfMonthfin/${monthOfYearfin + 1}/$yearfin",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (nombre.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Introduzca su nombre", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (dni.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Introduzca su dni", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (email.text.isEmpty()) {
                Toast.makeText(requireActivity(), "¡Introduzca su email", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (telefono.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Introduzca su teléfono", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (fechaini.year == 0 || fechaini.month == 0 || fechaini.dayOfMonth == 0) {
                Toast.makeText(
                    requireActivity(),
                    "Introduzca una fecha de inicio",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (fechafin.year == 0 || fechafin.month == 0 || fechafin.dayOfMonth == 0) {
                Toast.makeText(
                    requireActivity(),
                    "Introduzca una fecha de fin",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val fechaIni = Calendar.getInstance()
            fechaIni.set(fechaini.year, fechaini.month, fechaini.dayOfMonth)

            val fechaFin = Calendar.getInstance()
            fechaFin.set(fechafin.year, fechafin.month, fechafin.dayOfMonth)

            val horaIni = Calendar.getInstance()
            horaIni.set(Calendar.HOUR_OF_DAY, horaini.hour)
            horaIni.set(Calendar.MINUTE, horaini.minute)

            val horaFin = Calendar.getInstance()
            horaFin.set(Calendar.HOUR_OF_DAY, horafin.hour)
            horaFin.set(Calendar.MINUTE, horafin.minute)

            // Verificar si las fechas son iguales
            if (fechaIni == fechaFin && horaIni >= horaFin) {
                Toast.makeText(
                    requireActivity(),
                    "La hora de salida debe ser posterior a la hora de llegada",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Verificar si la fecha de salida es anterior a la fecha de entrada
            if (fechaFin.before(fechaIni)) {
                Toast.makeText(
                    requireActivity(),
                    "La fecha de salida no puede ser anterior a la fecha de entrada",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            Toast.makeText(rootView.context, "Se esta creando tu reserva", Toast.LENGTH_SHORT)
                .show()
            GlobalScope.launch(Dispatchers.IO) {
                val respuesta = nuevaReserva(
                    lugarValue,
                    nombreValue,
                    dniValue,
                    emailValue,
                    telefonoValue,
                    numPersonasValue,
                    comentarioValue,
                    fInicioValue,
                    fFinValue,
                    hInicioValue,
                    hFinValue
                )

                withContext(Dispatchers.Main) {
                    val contexto = rootView.context
                    if (respuesta == "0") {
                        Toast.makeText(
                            rootView.context,
                            "Ha habido un error",
                            Toast.LENGTH_SHORT
                        ).show()
                        (contexto as Activity).recreate()
                    } else {
                        Toast.makeText(
                            rootView.context,
                            "Reserva creada correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        (contexto as Activity).recreate()
                    }
                }
            }

        }

        return rootView

    }


    suspend private fun nuevaReserva(
        lugar: String,
        nombre: String,
        dni: String,
        correo: String,
        telefono: String,
        numPersonas: String,
        comentario: String,
        fInicio: String,
        fFin: String,
        hInicio: String,
        hFin: String
    ): String {
        Thread.sleep(2000)
        Log.d("FETCH", "Función ejecutada");
        val service = ApiService()
        var respuesta = service.nuevaReserva(
            lugar,
            nombre,
            dni,
            correo,
            telefono,
            numPersonas,
            comentario,
            fInicio,
            fFin,
            hInicio,
            hFin
        )
        return respuesta
    }


    private fun validateFields(rootView: View): Boolean {
        val lugar = rootView.findViewById<Spinner>(com.vivac.proyectofinal.R.id.spinner)
        val nombre = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditNombreF)
        val dni = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditDniF)
        val email = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditEmailF)
        val telefono =
            rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditTelefonoF)
        val fechaini =
            rootView.findViewById<DatePicker>(com.vivac.proyectofinal.R.id.EditFechaIniF)
        val fechafin =
            rootView.findViewById<DatePicker>(com.vivac.proyectofinal.R.id.EditFechaFinF)
        val horaini =
            rootView.findViewById<TimePicker>(com.vivac.proyectofinal.R.id.EditHoraLlegadaF)
        val horafin =
            rootView.findViewById<TimePicker>(com.vivac.proyectofinal.R.id.EditHoraSalida)
        val numPersonas =
            rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.EditNumPersonasF)

        val fechaIni = Calendar.getInstance()
        fechaIni.set(fechaini.year, fechaini.month, fechaini.dayOfMonth)

        val fechaFin = Calendar.getInstance()
        fechaFin.set(fechafin.year, fechafin.month, fechafin.dayOfMonth)

        val horaIni = Calendar.getInstance()
        horaIni.set(Calendar.HOUR_OF_DAY, horaini.hour)
        horaIni.set(Calendar.MINUTE, horaini.minute)

        val horaFin = Calendar.getInstance()
        horaFin.set(Calendar.HOUR_OF_DAY, horafin.hour)
        horaFin.set(Calendar.MINUTE, horafin.minute)

        val isNPersonasValid = numPersonas.getText().toString() != "0";
        val isLugarValid = lugar.selectedItem.toString().isNotEmpty()
        val isNombreValid = nombre.text.isNotEmpty()
        val isDniValid = dni.text.isNotEmpty()
        val isEmailValid = email.text.isNotEmpty()
        val isTelefonoValid = telefono.text.isNotEmpty()
        val isFechaIniValid =
            fechaini.year != 0 && fechaini.month != 0 && fechaini.dayOfMonth != 0
        var isHoraValid = true
        if (fechaIni == fechaFin && horaIni >= horaFin) {
            isHoraValid = false
        }
        val isFechaFinValid =
            fechafin.year != 0 && fechafin.month != 0 && fechafin.dayOfMonth != 0 && fechaFin.before(
                fechaIni
            )

        return isNPersonasValid && isLugarValid && isNombreValid && isDniValid && isEmailValid && isTelefonoValid && isFechaIniValid && isFechaFinValid && isHoraValid;
    }

}


