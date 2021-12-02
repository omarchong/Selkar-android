package com.example.examen_omar_chong

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoAlumnoActivity : AppCompatActivity() {

    private  val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        var idAlumno: Int? = null

        if(intent.hasExtra("alumno")){
            val alumno = intent.extras?.getSerializable("alumno") as Alumno
            nombre_et.setText(alumno.nombre)
            matricula_et.setText(alumno.matricula)
            email_et.setText(alumno.email)
            password_et.setText(alumno.password)
            idAlumno = alumno.idAlumno
            //------------------------------------------
            val imageUri = ImageController.getImageUri(this,idAlumno.toLong())
            imageSelect_iv.setImageURI(imageUri)
            //------------------------------------------
        }


        val database = AppDatabase.getDatabase(this)

        save_btn.setOnClickListener {
            val nombre = nombre_et.text.toString()
            val matricula = matricula_et.text.toString()
            val email = email_et.text.toString()
            val password = password_et.text.toString()

            val alumno = Alumno(nombre, matricula, email, password, R.drawable.ic_launcher_foreground)
            //----------------------------------------------------------------------------------------
            if(idAlumno != null){
                CoroutineScope(Dispatchers.IO).launch {
                    alumno.idAlumno = idAlumno
                    database.alumnos().update(alumno)
                    imageUri?.let {
                        val intent = Intent()
                        intent.data = it
                        setResult(Activity.RESULT_OK, intent)
                        ImageController.saveImage(this@NuevoAlumnoActivity, idAlumno.toLong(), it)
                    }
                    this@NuevoAlumnoActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val id = database.alumnos().insertAll(alumno)[0]
                    imageUri?.let{
                        ImageController.saveImage(this@NuevoAlumnoActivity, id, it)
                    }

                    this@NuevoAlumnoActivity.finish()
                }
            }
        }
        imageSelect_iv.setOnClickListener {
            ImageController.selectPhotoFromGallery(this, SELECT_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                imageSelect_iv.setImageURI(imageUri)
            }
        }
    }
}