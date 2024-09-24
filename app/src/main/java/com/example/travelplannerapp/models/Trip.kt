package com.example.travelplannerapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Trip es la entidad que representa un viaje en la base de datos.
 * Cada objeto Trip se convierte en un registro de la tabla "trips".
 *
 * @param id El identificador único de cada viaje.
 * @param destination El destino del viaje.
 * @param startDate La fecha de inicio del viaje.
 * @param endDate La fecha de fin del viaje.
 * @param description Una descripción del viaje.
 */
@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val destination: String,
    val startDate: String,
    val endDate: String,
    val description: String
)


