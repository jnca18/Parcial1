package com.example.travelplannerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelplannerapp.models.Trip
import com.example.travelplannerapp.viewmodel.TripViewModel

/**
 * Pantalla para agregar o editar un viaje en la aplicación.
 *
 * Esta función permite a los usuarios introducir o modificar los detalles de un viaje, incluyendo
 * el destino, las fechas de inicio y fin, y una descripción. Al guardar, se actualizan los datos
 * en el ViewModel y se regresa a la pantalla anterior.
 *
 * @param navController Controlador de navegación que permite gestionar la navegación entre pantallas.
 * @param viewModel Instancia del [TripViewModel] para acceder y manipular los datos del viaje.
 * @param tripId ID del viaje a editar. Si es null, se considera que se está agregando un nuevo viaje.
 */
@Composable
fun AddEditTripScreen(navController: NavController, viewModel: TripViewModel, tripId: Int?) {
    // Variables para almacenar los datos del viaje
    var destination by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Si se está editando un viaje, obtenemos sus datos
    if (tripId != null) {
        val trip = viewModel.getTripById(tripId).collectAsState(initial = null)
        trip.value?.let {
            destination = it.destination
            startDate = it.startDate
            endDate = it.endDate
            description = it.description
        }
    }

    // Scaffold para el diseño de la pantalla
    Scaffold(
        floatingActionButton = {
            // Botón para guardar el viaje
            FloatingActionButton(onClick = {
                viewModel.saveTrip(
                    Trip(
                        id = tripId ?: 0,
                        destination = destination,
                        startDate = startDate,
                        endDate = endDate,
                        description = description
                    )
                )
                navController.popBackStack() // Regresar a la pantalla anterior
            }) {
                Icon(Icons.Default.Check, contentDescription = "Guardar Viaje")
            }
        }
    ) { paddingValues ->
        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Campos de texto para la entrada de datos del viaje
            TextField(
                value = destination,
                onValueChange = { destination = it },
                label = { Text("Destino") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = startDate,
                onValueChange = { startDate = it },
                label = { Text("Fecha de inicio") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = { Text("Fecha de fin") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
