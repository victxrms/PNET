package com.vivac.proyectofinal.ui.localizacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vivac.proyectofinal.R
import com.vivac.proyectofinal.databinding.FragmentLocalizacionBinding

class LocalizacionFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentLocalizacionBinding? = null
    private var googleMap: GoogleMap? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val localizacionViewModel =
            ViewModelProvider(this).get(LocalizacionViewModel::class.java)

        _binding = FragmentLocalizacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLocalizacion
        localizacionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Obtenemos una referencia al SupportMapFragment y llamamos al método getMapAsync
        // para registrar un OnMapReadyCallback.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Asignamos el valor de GoogleMap a nuestra referencia.
        this.googleMap = googleMap

        val casa = LatLng(36.537312,-6.201562)
        googleMap.addMarker(
            MarkerOptions()
                .position(casa)
                .title("VIVAC - Sede Oficial")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(casa))
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    location.getLatitude(),
                    location.getLongitude()
                ), 12.0f
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
