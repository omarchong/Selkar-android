package com.example.examen_omar_chong

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cliente::class], version = 2, exportSchema = false)
abstract class AppDatabase2: RoomDatabase()  {
    abstract  fun clientes(): ClientesDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase2? = null
        fun getDatabase(context: Context): AppDatabase2{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase2::class.java,
                    "app_database2"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}