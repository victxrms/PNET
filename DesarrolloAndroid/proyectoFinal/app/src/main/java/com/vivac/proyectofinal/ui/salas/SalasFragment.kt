package com.vivac.proyectofinal.ui.salas

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import com.vivac.proyectofinal.databinding.FragmentSalasBinding
import com.vivac.proyectofinal.ui.salas.SalasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class SalasFragment : Fragment() {

    private var _binding: FragmentSalasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SalaAdapter
    private lateinit var viewModel: SalasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSalasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        adapter = SalaAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        val btnPDF: Button = root.findViewById(com.vivac.proyectofinal.R.id.buttonPDF)

        btnPDF.setOnClickListener {
            Toast.makeText(requireContext(), "Creando PDF", Toast.LENGTH_SHORT).show()
            val position = 0
            recyclerView.smoothScrollToPosition(position)
            Toast.makeText(requireContext(), "¡PDF creado!", Toast.LENGTH_SHORT).show()

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // La vista del RecyclerView ha finalizado el desplazamiento
                        recyclerView.measure(
                            View.MeasureSpec.makeMeasureSpec(recyclerView.width, View.MeasureSpec.EXACTLY),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                        )

                        crearPDF(recyclerView)

                    }
                }
            })

        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SalasViewModel::class.java)

        viewModel.getSalas().observe(viewLifecycleOwner) { salas ->
            adapter.setSalas(salas)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun crearPDF(recyclerView: RecyclerView) {
        val carpeta = "/archivospdf"
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + carpeta

        val dir = File(path)
        if (!dir.exists()) dir.mkdirs()

        val archivo = File(dir, "salas.pdf")
        val f = FileOutputStream(archivo)

        val documento = Document()

        // Obtener las dimensiones del RecyclerView
        val viewWidth = recyclerView.measuredWidth
        val viewHeight = recyclerView.measuredHeight

        // Ajustar el tamaño del documento según las dimensiones del RecyclerView
        val rectangle = com.itextpdf.text.Rectangle(viewWidth.toFloat() + 60, viewHeight.toFloat())
        documento.setPageSize(rectangle)

        PdfWriter.getInstance(documento, f)

        documento.open()

        // Dibujar el contenido en el PDF
        val bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        recyclerView.draw(canvas)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        val imagen = Image.getInstance(byteArray)
        documento.add(imagen)

        documento.close()


    }

}
