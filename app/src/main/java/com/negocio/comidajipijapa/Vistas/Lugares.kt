package com.negocio.comidajipijapa.Vistas

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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.negocio.comidajipijapa.Componentes.BarraBusqueda
import com.negocio.comidajipijapa.Componentes.LocalOpcion
import com.negocio.comidajipijapa.Modelo.locales
import androidx.activity.compose.LocalActivity

// Asegúrate de tener estas importaciones para las mejoras:
// import androidx.compose.ui.input.nestedscroll.nestedScroll
// import androidx.compose.ui.text.font.FontFamily
// import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lugares(navController: NavController) {
    var estadoConsulta by remember { mutableStateOf(TextFieldValue("")) }
    var mostrarDialogoSalida by remember { mutableStateOf(false) }
    val actividad = LocalActivity.current

    // --- MEJORA 1: Scroll Behavior ---
    // Esto permite que la TopAppBar reaccione al hacer scroll (ej. cambiando de color o elevación).
    // Le da un toque muy profesional a la UI.
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    // --- MEJORA 2: Conectar el Scroll Behavior con el Scaffold ---
    // Usamos Modifier.nestedScroll para que el Scaffold sepa cómo manejar el scroll de la lista
    // y aplicarlo a la TopAppBar.
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // --- MEJORA 3: Usar CenterAlignedTopAppBar ---
            // Este componente está específicamente diseñado para centrar el título de forma correcta,
            // considerando el espacio de los íconos de acción.
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        // Sugerencia: Usar el nombre completo puede verse más profesional.
                        text = "Jipi-Jama",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis, // Evita que el texto se desborde si es muy largo
                        fontWeight = FontWeight.Normal,   // Un peso Normal o Medium puede ser más elegante
                        fontFamily = FontFamily.Serif      // Una fuente con serifa añade un toque clásico/elegante
                    )
                },
                actions = {
                    IconButton(onClick = { mostrarDialogoSalida = true }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Salir de la aplicación" // Siempre es bueno añadir descripción
                        )
                    }
                },
                // --- MEJORA 4: Paleta de colores refinada y dinámica ---
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    // Color principal de la barra
                    containerColor = Color(0xFFF57F17), // Un tono naranja/mostaza elegante (Tono 700 de Material Yellow)

                    // Color del título y los íconos
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,

                    // Color que toma la barra cuando se hace scroll hacia abajo.
                    // Puede ser el mismo o uno ligeramente más oscuro para dar profundidad.
                    scrolledContainerColor = Color(0xE4F57F17) // Un tono más oscuro del naranja (Tono 900)
                ),
                // --- MEJORA 5: Aplicar el comportamiento de scroll ---
                scrollBehavior = scrollBehavior
            )
        }
    ) { valoresPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresPadding)
                .background(Color(0xFFFFFDF9)) // Un fondo "off-white" muy sutil
        ) {
            BarraBusqueda(
                valorActual = estadoConsulta,
                alCambiarValor = { nuevoValor -> estadoConsulta = nuevoValor },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            val localesFiltrados = locales.filter { local ->
                estadoConsulta.text.isBlank() ||
                        local.nombre.contains(estadoConsulta.text, ignoreCase = true) ||
                        local.categoria.contains(estadoConsulta.text, ignoreCase = true)
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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
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

    if (mostrarDialogoSalida) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoSalida = false },
            title = { Text("Confirmar Salida") },
            text = { Text("¿Estás seguro de que deseas salir de la aplicación?") },
            confirmButton = {
                Button(
                    onClick = {
                        actividad?.finishAffinity()
                        mostrarDialogoSalida = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Salir", color = MaterialTheme.colorScheme.onError)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { mostrarDialogoSalida = false }) { // Un botón delineado para la acción secundaria se ve bien
                    Text("Cancelar")
                }
            }
        )
    }
}