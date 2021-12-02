package com.example.examen_omar_chong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_cliente.*

class ClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        var listaClientes = emptyList<Cliente>()

        val database = AppDatabase2.getDatabase(this)
        database.clientes().getAll().observe(this, Observer{
            listaClientes = it
            val adapter = ClientesAdapter(this, listaClientes)


        listaclientes.adapter = adapter

        })



       listaclientes.setOnItemClickListener { parent, view, position, id ->
           val intent = Intent(this, ClienteActivity2::class.java )
           intent.putExtra("id", listaClientes[position].idCliente)
           startActivity(intent)

       }
        floatingActionButton2.setOnClickListener{
            val intent = Intent(this, NuevoClienteActivity::class.java)
            startActivity(intent)
        }
    }
}