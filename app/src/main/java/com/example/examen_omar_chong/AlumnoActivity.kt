package com.example.examen_omar_chong

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_alumno.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoActivity : AppCompatActivity() {

    private lateinit var  database: AppDatabase
    private  lateinit var alumno: Alumno
    private lateinit var alumnoLiveData: LiveData<Alumno>
    private val EDIT_ACTIVITY = 49

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno)

        database = AppDatabase.getDatabase(this)


        //val alumno = intent.getSerializableExtra("alumno") as Alumno
        val idAlumno = intent.getIntExtra("id", 0)
        //----------------------------------------------------
        val imageUri = ImageController.getImageUri(this, idAlumno.toLong())
        image.setImageURI(imageUri)
        //-----------------------------------------------------
        alumnoLiveData = database.alumnos().get(idAlumno)

        alumnoLiveData.observe(this, Observer {

        alumno = it


        nombre_alumno.text = alumno.nombre
        matricula_alumno.text = alumno.matricula
        email_alumno.text = alumno.email
        password_alumno.text = alumno.password
        //image.setImageResource(alumno.imagen)//

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.alumno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.edit_item -> {
                val intent = Intent(this, NuevoAlumnoActivity::class.java)
                intent.putExtra("alumno", alumno)
                startActivityForResult(intent, EDIT_ACTIVITY)
            }
            R.id.delete_item -> {
                alumnoLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    database.alumnos().delete(alumno)
                    ImageController.deleteImage(this@AlumnoActivity, alumno.idAlumno.toLong())
                    this@AlumnoActivity.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            resultCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                image.setImageURI(data!!.data)
            }
        }
    }
}