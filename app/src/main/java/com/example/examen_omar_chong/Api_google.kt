package com.example.examen_omar_chong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Api_google : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_google)
        createFragment()
    }
    private fun createFragment(){
        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap){
        map = googleMap
        createMarker()
    }
    private fun createMarker(){
        val coordenada = LatLng(19.33585580243287, -99.5390256167001)
        val marcador = MarkerOptions().position(coordenada).title("Grupo selkar")
        map.addMarker(marcador)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordenada, 18f),
            4000,
            null
        )
    }
}