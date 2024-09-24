package com.example.travelplannerapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelplannerapp.models.Trip

/**
 * TripDao es la interfaz que define las operaciones CRUD sobre la tabla de viajes (trips).
 * Utiliza las anotaciones de Room para generar el código SQL necesario.
 */
@Dao
interface TripDao {

    /**
     * Consulta todos los registros de viajes en la base de datos.
     *
     * @return Una lista de objetos Trip.
     */
    @Query("SELECT * FROM trips")
    suspend fun getAllTrips(): List<Trip>

    /**
     * Consulta un viaje específico por su ID.
     *
     * id El ID del viaje.
     * @return El objeto Trip correspondiente o null si no existe.
     */
    @Query("SELECT * FROM trips WHERE id = :id")
    suspend fun getTripById(id: Int): Trip?

    /**
     * Inserta un nuevo viaje en la base de datos.
     * Si el viaje ya existe (mismo ID), se reemplaza.
     *
     * trip El objeto Trip a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: Trip)

    /**
     * Elimina un viaje de la base de datos.
     *
     * trip El objeto Trip a eliminar.
     */
    @Delete
    suspend fun deleteTrip(trip: Trip)
}


