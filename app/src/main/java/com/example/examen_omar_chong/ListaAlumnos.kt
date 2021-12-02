package com.example.examen_omar_chong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_lista_alumnos.*


class ListaAlumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alumnos)

        //---------------------------------------------
        var listaAlumno = emptyList<Alumno>()
        var database = AppDatabase.getDatabase(this)
        database.alumnos().getAll().observe(this, Observer {
            listaAlumno = it
            val adapter = AlumnosAdapter(this, listaAlumno)

            lista.adapter = adapter
        })


        lista.setOnItemClickListener{ parent, view, position, id ->
            val intent  = Intent(this, AlumnoActivity::class.java)
            intent.putExtra("id", listaAlumno[position].idAlumno)
            startActivity(intent)

        }

        floatingActionButton2.setOnClickListener{
            val intent = Intent(this, NuevoAlumnoActivity::class.java)
            startActivity(intent)
        }
    }
}