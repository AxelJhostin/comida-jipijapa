package com.negocio.comidajipijapa.Vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.negocio.comidajipijapa.Componentes.BarraBusqueda
import com.negocio.comidajipijapa.Componentes.LocalOpcion
import com.negocio.comidajipijapa.Modelo.locales

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lugares(navController: NavController) {
    var searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Locales jipijapa", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = Color(0, 187, 212)
                    containerColor = Color(255,87,34) // sabores tradicionales
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(252,228,236))
        ) {
            BarraBusqueda(searchQuery)

            val filteredLocales = locales.filter { local ->
                local.nombre.contains(searchQuery.value.text, ignoreCase = true)
            }

            LazyColumn {
                items(filteredLocales) { local ->
                    LocalOpcion(local = local) {
                        navController.navigate("Restaurante/${local.id}")
                    }
                }
            }
        }
    }
}
