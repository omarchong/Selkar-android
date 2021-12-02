package com.example.examen_omar_chong

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.LiveData
import kotlinx.android.synthetic.main.activity_inicio_sistema.*

class InicioSistema : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var alumno: Alumno
    private lateinit var alumnoLiveData: LiveData<Alumno>

    //menu
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sistema)

        //menu
        toggle = ActionBarDrawerToggle(this, drawLayout, R.string.open, R.string.close)
        drawLayout.addDrawerListener(toggle)
        toggle.syncState()

        //menu
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Item1 -> startActivity(Intent(this, ListaAlumnos::class.java))
                R.id.Item2 -> startActivity(Intent(this, ClienteActivity::class.java))
                R.id.Item3 -> startActivity(Intent(this, Api_google::class.java))
                R.id.Item4 -> startActivity(Intent(this, Api_google::class.java))
                R.id.Item5 -> startActivity(Intent(this, Api_google::class.java))
                //R.id.Item3 -> startActivity(Intent(this, ClienteActivity2::class.java))



            }
            true
        }

        //login
        var ir_lista=findViewById<Button>(R.id.lista2)
        ir_lista.setOnClickListener {
            val intent = Intent(this, ListaAlumnos::class.java)
            startActivity(intent)
        }


        database = AppDatabase.getDatabase(this )
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        if(email == ""){txt_email.setText("Error ingresa el email")}
        else{
            if(password == "") {txt_email.setText("Error ingresa tu contraseña")}
            else{
                alumnoLiveData = database.alumnos().get(email.toString(), password.toString())
                alumnoLiveData.observe(this, Observer {
                    if(it != null){
                        alumno = it
                        txt_email.setText("Bienvenido al sistema\n" + alumno.nombre)
                        ir_lista.setVisibility(View.VISIBLE)
                    }
                    else{txt_email.setText("No existe el usuario\n o datos incorrectos (Email o password)")}
                })
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}



