package com.negocio.comidajipijapa.Navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.Modelo.locales
import com.negocio.comidajipijapa.Vistas.LogoInicial
import com.negocio.comidajipijapa.Vistas.Lugares
import com.negocio.comidajipijapa.Vistas.Restaurante

@Composable
fun Navegacion(){
    //funciones para hacer el navController
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LogoInicial"
    ) {
        //aqui lleno las distintas vistas
        composable("LogoInicial") {
            LogoInicial(navController)
        }
        composable("Lugares") {
            Lugares(navController)
        }
        composable("Restaurante/{localId}") { backStackEntry ->
            val localId = backStackEntry.arguments?.getString("localId")
            val local = locales.find { it.id == localId }
            if (local != null) {
                Restaurante(navController, local)
            } else {
                // Puedes mostrar un mensaje de error o pantalla vacía
                Text("Local no encontrado")
            }
        }

    }
}