package com.example.examen_omar_chong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_cliente2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClienteActivity2 : AppCompatActivity() {

    private lateinit var  database: AppDatabase2
    private lateinit var  cliente: Cliente
    private lateinit var clienteLiveData: LiveData<Cliente>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente2)


        database = AppDatabase2.getDatabase(this)

       val idCliente = intent.getIntExtra("id", 0)

        clienteLiveData = database.clientes().get(idCliente)
        clienteLiveData.observe(this, Observer {
            cliente = it
            nombrecliented.text = cliente.nombrecliente
            nombrecomerciald.text = cliente.nombrecomercial
            girod.text = cliente.giro
            telefonod.text = cliente.telefono
            direcciond.text = cliente.direccion
            tipocliented.text = cliente.tipocliente
            estatuscliented.text = cliente.estatus
            imageView6.setImageResource(cliente.imagen)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cliente_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_item -> {
                val intent = Intent(this, NuevoClienteActivity::class.java)
                intent.putExtra("cliente", cliente)
                startActivity(intent)
            }
            R.id.delete_item -> {
                clienteLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    database.clientes().delete(cliente)
                    this@ClienteActivity2.finish()

                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

}