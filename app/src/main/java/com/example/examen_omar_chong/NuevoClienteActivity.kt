package com.example.examen_omar_chong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_cliente.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_cliente)

        var idCliente: Int? = null

        if(intent.hasExtra("cliente")) {
            val cliente = intent.extras?.getSerializable("cliente") as Cliente
            nombrecliente_et.setText(cliente.nombrecliente)
            nombrecomercial_et.setText(cliente.nombrecomercial)
            giro_et.setText(cliente.giro)
            telefono_et.setText(cliente.telefono)
            direccion_et.setText(cliente.direccion)
            tipocliente_et.setText(cliente.tipocliente)
            estauscliente_et.setText(cliente.estatus)
            idCliente = cliente.idCliente
        }

        val database = AppDatabase2.getDatabase(this)

        save_btn2.setOnClickListener{
            val nombrecliente = nombrecliente_et.text.toString()
            val nombrecomercial = nombrecomercial_et.text.toString()
            val giro = giro_et.text.toString()
            val telefono = telefono_et.text.toString()
            val direccion = direccion_et.text.toString()
            val tipocliente = tipocliente_et.text.toString()
            val estatus = estauscliente_et.text.toString()

            val cliente = Cliente(nombrecliente, nombrecomercial, giro, telefono, direccion, tipocliente, estatus, R.drawable.ic_launcher_background)

            if(idCliente != null){
                CoroutineScope(Dispatchers.IO).launch {
                    cliente.idCliente = idCliente
                    database.clientes().update(cliente)
                    this@NuevoClienteActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.clientes().insertAll(cliente)
                    this@NuevoClienteActivity.finish()
                }
            }
        }

    }
}