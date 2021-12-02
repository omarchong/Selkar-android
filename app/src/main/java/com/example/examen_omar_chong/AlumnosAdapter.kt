package com.example.examen_omar_chong

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_alumno.view.*

class AlumnosAdapter(private val mContext: Context, private val listaAlumno: List<Alumno>):
    ArrayAdapter<Alumno>(mContext, 0, listaAlumno){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_alumno, parent, false)
        val alumno = listaAlumno[position]

        layout.nombre.text = alumno.nombre
        layout.email.text = alumno.email
        layout.password.text = alumno.password
        val imageUri = ImageController.getImageUri(mContext, alumno.idAlumno.toLong())
        layout.imageView2.setImageURI(imageUri)

        return layout

    }
}