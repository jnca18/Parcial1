package com.example.travelplannerapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelplannerapp.models.Trip
import com.example.travelplannerapp.viewmodel.TripViewModel

/**
 * Pantalla que muestra una lista de viajes.
 *
 * Esta función permite a los usuarios ver todos los viajes guardados, así como agregar un nuevo viaje
 * o eliminar uno existente. Cada viaje en la lista se puede seleccionar para ver sus detalles.
 *
 * @param navController Controlador de navegación que permite gestionar la navegación entre pantallas.
 * @param viewModel Instancia del [TripViewModel] para acceder a los datos de los viajes.
 */
@Composable
fun TripListScreen(navController: NavController, viewModel: TripViewModel) {
    // Obtener la lista de viajes del ViewModel
    val trips = viewModel.getAllTrips().collectAsState(initial = emptyList())

    // Scaffold para el diseño de la pantalla
    Scaffold(
        floatingActionButton = {
            // Botón para agregar un nuevo viaje
            FloatingActionButton(onClick = { navController.navigate("addTrip") }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Viaje")
            }
        }
    ) { paddingValues ->
        // Lista de viajes usando LazyColumn
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(trips.value) { trip ->
                // Componente para mostrar cada viaje
                TripItem(
                    trip = trip,
                    onClick = {
                        navController.navigate("tripDetail/${trip.id}")
                    },
                    onDeleteClick = {
                        viewModel.deleteTrip(trip)
                    }
                )
            }
        }
    }
}

/**
 * Componente que representa un ítem de viaje en la lista.
 *
 * Este componente muestra información sobre un viaje y proporciona opciones para seleccionarlo
 * y eliminarlo de la lista.
 *
 * @param trip El objeto [Trip] que contiene los detalles del viaje.
 * @param onClick Función que se ejecuta cuando se selecciona el ítem.
 * @param onDeleteClick Función que se ejecuta cuando se hace clic en el botón de eliminar.
 */
@Composable
fun TripItem(trip: Trip, onClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() } // Hacer clic para ver detalles
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = trip.destination, style = MaterialTheme.typography.h6)
            Text(text = trip.description)
            Button(onClick = { onDeleteClick() }) {
                Text("Eliminar")
            }
        }
    }
}





