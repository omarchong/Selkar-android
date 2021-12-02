package com.example.examen_omar_chong

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
 interface AlumnosDao {
    @Query("SELECT * FROm alumnos")
    fun getAll(): LiveData<List<Alumno>>

    @Query("SELECT * FROM  alumnos WHERE idAlumno = :id")
    fun get(id: Int): LiveData<Alumno>

    @Insert
    fun insertAll(vararg alumno: Alumno): List<Long>

    @Update
    fun update(alumno: Alumno)

    @Query("SELECT * FROM alumnos WHERE email = :email AND  password = :password ")
    fun get(email:String, password:String): LiveData<Alumno>

   @Delete
   fun delete(alumno: Alumno)

}