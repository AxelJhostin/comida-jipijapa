package com.negocio.comidajipijapa.Vistas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.negocio.comidajipijapa.Modelo.Local

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Restaurante(navController: NavController, local: Local) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Restaurante", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = local.imagenUrl),
                contentDescription = "Imagen del local",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))
            Divider()
            Spacer(modifier = Modifier.padding(8.dp))

            Text("Nombre: ${local.nombre}", style = MaterialTheme.typography.titleLarge)
            Text("Horario: ${local.horario}", style = MaterialTheme.typography.bodyLarge)
            Text("Teléfono: ${local.telefono}", style = MaterialTheme.typography.bodyLarge)
            Text(
                "Días de atención: ${local.diasAtencion.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.weight(1f)) // Empuja los botones al fondo

            // Botones de acción
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.menuUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Ver Menú")
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${local.telefono}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Llamar al restaurante")
                }

                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://wa.me/${local.telefono}?text=¡Hola! Quiero más información sobre tu menú.")
                        )
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Escribir por WhatsApp")
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.ubicacion))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Ver Ubicación")
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RestaurantePreview() {
    val navController = rememberNavController()
    val localDemo = Local(
        id = "1",
        nombre = "Cevichería El Buen Sabor",
        horario = "10:00 AM - 9:00 PM",
        telefono = "0999999999",
        menuUrl = "https://example.com/menu.pdf",
        ubicacion = "https://maps.google.com/?q=-1.33,-80.01",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1tYqQAsLGK2Szj583iLmeQGhWm8ZRV6O_",
        diasAtencion = listOf("Lunes", "Martes", "Miércoles", "Viernes", "Sábado")
    )
    Restaurante(navController = navController, local = localDemo)
}
