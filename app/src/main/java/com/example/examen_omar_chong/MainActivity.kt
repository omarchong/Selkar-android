package com.example.examen_omar_chong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_alumno.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            val intent = Intent(this, InicioSistema::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            startActivity(intent)
        }




        }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.usuario_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.add_user->{
                    val intent= Intent(this,NuevoAlumnoActivity::class.java)
                    startActivity(intent)
                }
            }
            return super.onOptionsItemSelected(item)
        }

    }



