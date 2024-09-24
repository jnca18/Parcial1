package com.example.travelplannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelplannerapp.ui.theme.TravelPlannerAppTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.travelplannerapp.viewmodel.TripViewModel
import com.example.travelplannerapp.screens.TripListScreen
import com.example.travelplannerapp.screens.TripDetailScreen
import com.example.travelplannerapp.screens.AddEditTripScreen

/**
 * Clase principal de la aplicación que representa la actividad principal.
 *
 * Esta clase extiende [ComponentActivity] y establece el contenido de la actividad usando Jetpack Compose.
 * También configura la navegación entre las diferentes pantallas de la aplicación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilitamos el modo Edge-to-Edge para aprovechar todo el espacio de la pantalla
        enableEdgeToEdge()

        // Definimos el contenido de la actividad usando Jetpack Compose
        setContent {
            // Aplicamos el tema de la app
            TravelPlannerAppTheme {
                // Usamos Scaffold para manejar el diseño de la pantalla, permitiendo espacio para elementos flotantes
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Placeholder para probar que la UI se muestra correctamente
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                // Recordamos el controlador de navegación
                val navController = rememberNavController()

                // Obtenemos una instancia del ViewModel TripViewModel
                val tripViewModel: TripViewModel = viewModel<TripViewModel>()

                // Llamamos a la función que configura el sistema de navegación de la app
                TravelPlannerApp(navController, tripViewModel)
            }
        }
    }
}

/**
 * Composable que muestra un saludo.
 *
 * Este es un ejemplo de UI que se puede personalizar.
 *
 * @param name El nombre que se mostrará en el saludo.
 * @param modifier Modificador para aplicar estilos al Composable.
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Muestra un saludo
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/**
 * Vista previa de la composable Greeting en Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Preview de la UI en el editor de Android Studio
    TravelPlannerAppTheme {
        Greeting("Android")
    }
}

/**
 * Configura la navegación de la aplicación y define las pantallas disponibles.
 *
 * @param navController Controlador de navegación que gestiona la navegación entre pantallas.
 * @param viewModel Instancia del ViewModel que gestiona los datos de los viajes.
 */
@Composable
fun TravelPlannerApp(navController: NavHostController, viewModel: TripViewModel) {
    // Definimos el NavHost, que contiene todas las pantallas de la aplicación
    NavHost(navController = navController, startDestination = "tripList") {

        // Ruta que lleva a la pantalla de la lista de viajes
        composable("tripList") {
            TripListScreen(navController, viewModel)
        }

        // Ruta que lleva a la pantalla de detalles de un viaje, pasando el tripId como argumento
        composable("tripDetail/{tripId}") { backStackEntry ->
            // Obtenemos el ID del viaje desde los argumentos de la navegación
            val tripId = backStackEntry.arguments?.getString("tripId")?.toInt()
            tripId?.let {
                TripDetailScreen(it, viewModel, navController)
            }
        }

        // Ruta que lleva a la pantalla de agregar un nuevo viaje
        composable("addTrip") {
            AddEditTripScreen(navController, viewModel, null)
        }

        // Ruta que lleva a la pantalla de editar un viaje existente
        composable("editTrip/{tripId}") { backStackEntry ->
            // Obtenemos el ID del viaje a editar desde los argumentos de la navegación
            val tripId = backStackEntry.arguments?.getString("tripId")?.toInt()
            tripId?.let {
                AddEditTripScreen(navController, viewModel, it)
            }
        }
    }
}
