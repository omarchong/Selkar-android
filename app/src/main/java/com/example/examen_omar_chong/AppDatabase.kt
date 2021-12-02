package com.example.examen_omar_chong
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Alumno::class], version = 1, exportSchema = false)



abstract class AppDatabase: RoomDatabase() {
    abstract  fun  alumnos(): AlumnosDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            var tempInstant = INSTANCE
            if (tempInstant != null){
                return tempInstant
            }
            synchronized(this) {
                var instant = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instant
                return instant
            }
        }
    }
}