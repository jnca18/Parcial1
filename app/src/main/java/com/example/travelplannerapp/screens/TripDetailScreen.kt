package com.example.travelplannerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelplannerapp.viewmodel.TripViewModel

/**
 * Pantalla que muestra los detalles de un viaje específico.
 *
 * Esta función permite a los usuarios ver información sobre un viaje, incluyendo su destino,
 * descripción, y fechas. También proporciona una opción para editar el viaje.
 *
 * @param tripId ID del viaje cuyos detalles se mostrarán.
 * @param viewModel Instancia del [TripViewModel] para acceder a los datos del viaje.
 * @param navController Controlador de navegación que permite gestionar la navegación entre pantallas.
 */
@Composable
fun TripDetailScreen(tripId: Int, viewModel: TripViewModel, navController: NavController) {
    // Obtener los detalles del viaje usando el ID proporcionado
    val trip = viewModel.getTripById(tripId).collectAsState(initial = null)

    // Comprobamos si tenemos datos del viaje
    trip.value?.let { tripData ->
        // Scaffold para el diseño de la pantalla
        Scaffold(
            floatingActionButton = {
                // Botón para editar el viaje
                FloatingActionButton(onClick = {
                    navController.navigate("editTrip/${tripData.id}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar Viaje")
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
                // Muestra los detalles del viaje
                Text(text = "Destino: ${tripData.destination}", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Descripción: ${tripData.description}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Fecha de inicio: ${tripData.startDate}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Fecha de fin: ${tripData.endDate}")
            }
        }
    }
}

