package com.example.examen_omar_chong

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "clientes")

class Cliente (
    val nombrecliente:String,
    val nombrecomercial:String,
    val giro:String,
    val telefono:String,
    val direccion:String,
    val tipocliente:String,
    val estatus:String,
    val imagen:Int,
@PrimaryKey(autoGenerate = true)
var idCliente: Int = 0
): Serializable
