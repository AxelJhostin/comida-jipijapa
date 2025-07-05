package com.negocio.comidajipijapa.Vistas

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.negocio.comidajipijapa.Componentes.BarraBusqueda
import com.negocio.comidajipijapa.Componentes.LocalOpcion
import com.negocio.comidajipijapa.Modelo.locales

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lugares(navController: NavController) {
    var estadoConsulta by remember { mutableStateOf(TextFieldValue("")) }
    var mostrarDialogoSalida by remember { mutableStateOf(false) }
    val actividad = LocalActivity.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Jipi-Jama",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif
                    )
                },
                actions = {
                    // --- NUEVO BOTÓN AÑADIDO ---
                    IconButton(onClick = { navController.navigate("Favoritos") }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Ver Favoritos"
                        )
                    }
                    IconButton(onClick = { mostrarDialogoSalida = true }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Salir de la aplicación"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { valoresPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresPadding)
                .background(MaterialTheme.colorScheme.background)
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
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                OutlinedButton(onClick = { mostrarDialogoSalida = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
