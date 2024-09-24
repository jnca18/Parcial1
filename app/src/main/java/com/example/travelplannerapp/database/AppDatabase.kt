package com.example.travelplannerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelplannerapp.models.Trip

/**
 * AppDatabase es la clase que define la base de datos de la aplicación.
 * Utiliza Room como ORM (Object-Relational Mapping) para manejar los datos.
 * Incluye la entidad Trip y su correspondiente DAO.
 */
@Database(entities = [Trip::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Definición del DAO que gestiona las operaciones CRUD sobre la tabla de viajes.
    abstract fun tripDao(): TripDao

    companion object {
        // Instancia Singleton de la base de datos.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Obtiene la instancia de la base de datos. Si no existe, crea una nueva.
         * Se asegura que sólo haya una instancia de la base de datos en la aplicación.
         *
         * @param context El contexto de la aplicación.
         * @return La instancia de AppDatabase.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trip_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

