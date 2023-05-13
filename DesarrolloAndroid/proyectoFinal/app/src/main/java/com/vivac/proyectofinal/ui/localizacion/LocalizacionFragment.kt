package com.vivac.proyectofinal.ui.localizacion

import android.content.res.Configuration
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
import com.google.android.gms.maps.model.MapStyleOptions
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

        val isNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES


        val casa = LatLng(36.537312,-6.201562)
        googleMap.addMarker(
            MarkerOptions()
                .position(casa)
                .title("VIVAC - Sede Oficial")
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(casa, 17.0f))
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.style_json));

        if (isNightMode) {
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.stylenoche_json))
        } else {
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.style_json))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
