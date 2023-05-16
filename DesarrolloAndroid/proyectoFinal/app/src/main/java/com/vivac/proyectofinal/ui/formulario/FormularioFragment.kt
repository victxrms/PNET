package com.vivac.proyectofinal.ui.formulario

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.widget.Button
import com.vivac.proyectofinal.R
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText


class FormularioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este Fragmento
        val rootView = inflater.inflate(R.layout.fragment_formulario, container, false)

        // Buscar el botón en la vista inflada y asignar un ClickListener
        val btnValidar = rootView.findViewById<Button>(R.id.btnValidar)
        btnValidar.setOnClickListener {
            val nombre = rootView.findViewById<EditText>(R.id.textViewNombreF)
            val dni = rootView.findViewById<EditText>(R.id.textViewDniF)
            val email = rootView.findViewById<EditText>(R.id.textViewEmailF)
            val telefono = rootView.findViewById<EditText>(R.id.textViewTelF)
            val fechaini = rootView.findViewById<DatePicker>(R.id.textViewFechaIniF)
            val fechafin = rootView.findViewById<DatePicker>(R.id.textViewFechaFinF)

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