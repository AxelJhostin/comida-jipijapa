package com.negocio.comidajipijapa.Vistas

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight // Importante para el TopAppBar
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign // Importante para el TopAppBar
import androidx.compose.ui.unit.dp // Importante para PaddingValues y Arrangement.spacedBy
import androidx.compose.ui.unit.sp // Importante para el TopAppBar
import androidx.navigation.NavController
import com.negocio.comidajipijapa.Componentes.BarraBusqueda // Asumo que el parámetro de BarraBusqueda es 'estadoConsulta'
import com.negocio.comidajipijapa.Componentes.LocalOpcion
import com.negocio.comidajipijapa.Modelo.locales
import androidx.activity.compose.LocalActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lugares(navController: NavController) {
    // Cambié el nombre de la variable para consistencia con ejemplos anteriores
    var estadoConsulta by remember { mutableStateOf(TextFieldValue("")) }
    //Estado para controlar la visibilidad del dialogo de salida
    var mostrarDialogoSalida by remember { mutableStateOf(false) }
    //Obtener la actividad actual para poder cerrarla
    val actividad = LocalActivity.current


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text( // Título mejorado y centrado
                        text = "Locales en Jipijapa",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    IconButton(onClick = {mostrarDialogoSalida = true}) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(33, 215, 109) // verde lima
                )
            )
        }
    ) { valoresPadding -> // Cambiado a español para consistencia
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresPadding) // Padding del Scaffold
                .background(Color(248, 249, 250)) // Un fondo ligeramente diferente
        ) {
            // Asegúrate que tu componente BarraBusqueda acepte 'estadoConsulta'
            BarraBusqueda(
                valorActual = estadoConsulta,
                alCambiarValor = { nuevoValor -> estadoConsulta = nuevoValor }
                // Puedes añadir un Modifier aquí si es necesario, ej:
                // modifier = Modifier.padding(top = 8.dp)
            )

            val localesFiltrados = locales.filter { local -> // Cambiado a 'localesFiltrados'
                estadoConsulta.text.isBlank() || // Mostrar todos si la búsqueda está vacía
                        local.nombre.contains(estadoConsulta.text, ignoreCase = true) ||
                        local.categoria.contains(estadoConsulta.text, ignoreCase = true) // Búsqueda por categoría también
            }

            if (localesFiltrados.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron locales que coincidan con tu búsqueda.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    // Espacio VERTICAL ENTRE las tarjetas:
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    // Padding HORIZONTAL y VERTICAL para la lista completa:
                    contentPadding = PaddingValues(
                        start = 20.dp,   // Espacio a la izquierda de la lista
                        end = 20.dp,     // Espacio a la derecha de la lista
                        top = 16.dp,     // Espacio arriba de la primera tarjeta
                        bottom = 20.dp   // Espacio debajo de la última tarjeta
                    )
                ) {
                    items(localesFiltrados, key = { local -> local.id }) { local ->
                        LocalOpcion(local = local) {
                            navController.navigate("Restaurante/${local.id}")
                        }
                    }
                }
            }
        }
    }
    // Diálogo de confirmación para salir
    if (mostrarDialogoSalida) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoSalida = false }, // Cerrar diálogo si se toca fuera
            title = { Text("Confirmar Salida") },
            text = { Text("¿Estás seguro de que deseas salir de la aplicación?") },
            confirmButton = {
                Button(
                    onClick = {
                        actividad?.finishAffinity() // Cierra esta actividad y todas las de la app
                        mostrarDialogoSalida = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error) // Color rojo para acción destructiva
                ) {
                    Text("Salir", color = MaterialTheme.colorScheme.onError)
                }
            },
            dismissButton = {
                Button(onClick = { mostrarDialogoSalida = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}