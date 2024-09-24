package com.example.travelplannerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import com.example.travelplannerapp.database.AppDatabase
import com.example.travelplannerapp.models.Trip

/**
 * ViewModel para manejar la lógica de negocio relacionada con los viajes en la aplicación de planificación de viajes.
 *
 * Esta clase se encarga de interactuar con la base de datos a través del DAO de Trip, proporcionando
 * métodos para obtener, guardar y eliminar viajes. Extiende AndroidViewModel para acceder al contexto de la aplicación
 * y utilizar el alcance de ViewModel en las corutinas.
 *
 * @param application Instancia de la aplicación para acceder a recursos y la base de datos.
 */
class TripViewModel(application: Application) : AndroidViewModel(application) {

    // Acceso al DAO de Trip desde la base de datos de la aplicación
    private val tripDao = AppDatabase.getDatabase(application).tripDao()

    /**
     * Obtiene todos los viajes de la base de datos como un flujo.
     *
     * @return Un flujo que emite una lista de objetos Trip.
     */
    fun getAllTrips() = flow { emit(tripDao.getAllTrips()) }

    /**
     * Obtiene un viaje específico de la base de datos por su ID.
     *
     * @param id El ID del viaje que se desea obtener.
     * @return Un flujo que emite el objeto Trip correspondiente al ID proporcionado.
     */
    fun getTripById(id: Int) = flow { emit(tripDao.getTripById(id)) }

    /**
     * Guarda un nuevo viaje en la base de datos.
     *
     * Este método se ejecuta en un alcance de corutina y utiliza el DAO para insertar el viaje.
     *
     * @param trip El objeto Trip que se desea guardar.
     */
    fun saveTrip(trip: Trip) = viewModelScope.launch {
        tripDao.insertTrip(trip)
    }

    /**
     * Elimina un viaje de la base de datos.
     *
     * Este método se ejecuta en un alcance de corutina y utiliza el DAO para eliminar el viaje.
     *
     * @param trip El objeto Trip que se desea eliminar.
     */
    fun deleteTrip(trip: Trip) = viewModelScope.launch {
        tripDao.deleteTrip(trip)
    }
}
