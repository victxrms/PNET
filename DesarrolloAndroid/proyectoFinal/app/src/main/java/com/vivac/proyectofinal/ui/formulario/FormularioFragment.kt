package com.vivac.proyectofinal.ui.formulario

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FormularioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este Fragmento
        val rootView = inflater.inflate(com.vivac.proyectofinal.R.layout.fragment_formulario, container, false)

        val btnMostrarDatePicker: Button = rootView.findViewById(com.vivac.proyectofinal.R.id.btnMostrarDatePicker)
        val datePicker: DatePicker = rootView.findViewById(com.vivac.proyectofinal.R.id.EditFechaIniF)

        btnMostrarDatePicker.setOnClickListener {
            if (datePicker.visibility == View.VISIBLE) {
                datePicker.visibility = View.GONE
            } else {
                datePicker.visibility = View.VISIBLE
            }
        }

        val btnMostrarDatePickerFin: Button = rootView.findViewById(com.vivac.proyectofinal.R.id.btnDatePickerFin)
        val datePickerFin: DatePicker = rootView.findViewById(com.vivac.proyectofinal.R.id.EditFechaFinF)

        btnMostrarDatePickerFin.setOnClickListener {
            if (datePickerFin.visibility == View.VISIBLE) {
                datePickerFin.visibility = View.GONE
            } else {
                datePickerFin.visibility = View.VISIBLE
            }
        }


        val btnMostrarTimePickerLlegada: Button = rootView.findViewById(com.vivac.proyectofinal.R.id.btnTimePickerLlegada)
        val TimePickerLlegada: TimePicker = rootView.findViewById(com.vivac.proyectofinal.R.id.EditHoraLlegadaF)

        btnMostrarTimePickerLlegada.setOnClickListener {
            if (TimePickerLlegada.visibility == View.VISIBLE) {
                TimePickerLlegada.visibility = View.GONE
            } else {
                TimePickerLlegada.visibility = View.VISIBLE
            }
        }

        val btnMostrarTimePickerSalida: Button = rootView.findViewById(com.vivac.proyectofinal.R.id.btnTimePickerSalida)
        val TimePickerSalida: TimePicker = rootView.findViewById(com.vivac.proyectofinal.R.id.EditHoraSalida)

        btnMostrarTimePickerSalida.setOnClickListener {
            if (TimePickerSalida.visibility == View.VISIBLE) {
                TimePickerSalida.visibility = View.GONE
            } else {
                TimePickerSalida.visibility = View.VISIBLE
            }
        }

        // Buscar el botón en la vista inflada y asignar un ClickListener
        val btnValidar = rootView.findViewById<Button>(com.vivac.proyectofinal.R.id.btnValidar)
        btnValidar.setOnClickListener {
            val nombre = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.textViewNombreF)
            val dni = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.textViewDniF)
            val email = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.textViewEmailF)
            val telefono = rootView.findViewById<EditText>(com.vivac.proyectofinal.R.id.textViewTelF)
            val fechaini = rootView.findViewById<DatePicker>(com.vivac.proyectofinal.R.id.textViewFechaIniF)
            val fechafin = rootView.findViewById<DatePicker>(com.vivac.proyectofinal.R.id.textViewFechaFinF)

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
                Toast.makeText(requireActivity(), "Introduzca su nombre", Toast.LENGTH_SHORT).show()
            } else if (dni.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Introduzca su dni", Toast.LENGTH_SHORT).show()
            } else if (email.text.isEmpty()) {
                Toast.makeText(requireActivity(), "¡Introduzca su email", Toast.LENGTH_SHORT).show()
            } else if (telefono.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Introduzca su teléfono", Toast.LENGTH_SHORT).show()
            } else if (fechaini.year == 0 || fechaini.month == 0 || fechaini.dayOfMonth == 0) {
                Toast.makeText(requireActivity(), "Introduzca una fecha de inicio", Toast.LENGTH_SHORT).show()
            } else if (fechafin.year == 0 || fechafin.month == 0 || fechafin.dayOfMonth == 0) {
                Toast.makeText(requireActivity(), "Introduzca una fecha de fin", Toast.LENGTH_SHORT).show()
            }

        }

        return rootView
    }
}

