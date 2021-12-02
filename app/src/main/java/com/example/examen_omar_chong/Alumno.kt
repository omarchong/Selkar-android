package com.example.examen_omar_chong

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "alumnos")
class Alumno (
        val nombre:String,
        val matricula:String,
        val email:String,
        val password:String,
        val imagen:Int,
        @PrimaryKey(autoGenerate = true)
        var idAlumno: Int = 0
    ): Serializable