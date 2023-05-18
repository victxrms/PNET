package com.vivac.proyectofinal.ui.salas

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vivac.proyectofinal.databinding.FragmentSalasBinding
import io.ktor.utils.io.errors.IOException
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

    val pageHeight = 1120
    val pagewidth = 792

    //var bmp: Bitmap? = null
    //var scaledbmp:android.graphics.Bitmap? = null



    // constant code for runtime permissions

    // constant code for runtime permissions
    val PERMISSION_REQUEST_CODE = 200


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

        // initializing our variables.
        // initializing our variables.

        val view: View = inflater.inflate(com.vivac.proyectofinal.R.layout.fragment_salas, container, false)
        val generatePDFbtn = view.findViewById<MaterialButton>(com.vivac.proyectofinal.R.id.buttonReservar)

        //bmp = BitmapFactory.decodeResource(resources, com.vivac.proyectofinal.R.drawable.sala1)
        //scaledbmp = Bitmap.createBitmap(bmp)

        // below code is used for
        // checking our permissions.

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }

        generatePDFbtn.setOnClickListener(View.OnClickListener { // calling method to
            // generate our PDF file.
            generatePDF()
        })

        return root
    }

    private fun generatePDF() {
        // creating an object variable
        // for our PDF document.
        val pdfDocument = PdfDocument()

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        val paint = Paint()
        val title = Paint()

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        val mypageInfo = PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create()

        // below line is used for setting
        // start page for our PDF file.
        val myPage = pdfDocument.startPage(mypageInfo)

        // creating a variable for canvas
        // from our page of PDF.
        val canvas = myPage.canvas

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        //canvas.drawBitmap(scaledbmp!!, 56f, 40f, paint)

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.textSize = 15f

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.color = ContextCompat.getColor(requireActivity(), com.vivac.proyectofinal.R.color.purple_200)

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("A portal for IT professionals.", 209f, 100f, title)
        canvas.drawText("Geeks for Geeks", 209f, 80f, title)

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
        title.color = ContextCompat.getColor(requireActivity(), com.vivac.proyectofinal.R.color.purple_200)
        title.textSize = 15f

        // below line is used for setting
        // our text to center of PDF.
        title.textAlign = Paint.Align.CENTER
        canvas.drawText("This is sample document which we have created.", 396f, 560f, title)

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage)

        // below line is used to set the name of
        // our PDF file and its path.
        val file = File(Environment.getExternalStorageDirectory(), "GFG.pdf")
        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(FileOutputStream(file))

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(
                requireActivity(),
                "PDF file generated successfully.",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: IOException) {
            // below line is used
            // to handle error
            e.printStackTrace()
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close()
    }

    private fun checkPermission(): Boolean {
        // checking of permissions.
        val permission1 =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permission2 =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        // Solicitar permisos si no se han proporcionado.
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (writeStorage && readStorage) {
                    Toast.makeText(requireActivity(), "Permission Granted..", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Permission Denied.", Toast.LENGTH_SHORT).show()
                    //finish()
                }
            }
        }
    }

    companion object {
        // constant code for runtime permissions
        private const val PERMISSION_REQUEST_CODE = 200
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
}
