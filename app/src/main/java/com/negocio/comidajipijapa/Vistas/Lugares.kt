package com.negocio.comidajipijapa.Vistas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.negocio.comidajipijapa.Componentes.LocalOpcion
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.Modelo.locales

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lugares(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Locales jipijapa", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0, 187, 212)
                )
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            LazyColumn {
                items(locales) { local ->
                    LocalOpcion(local = local){
                        navController.navigate("Restaurante/${local.id}")

                    }
                }
            }

        }
    }
}