package com.negocio.comidajipijapa.Vistas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Restaurante(navController: NavController, local: Local) {
    val context = LocalContext.current
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }
    var isFavorita by remember { mutableStateOf(false) } // icono de estrella - estado

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Restaurante", color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {isFavorita = !isFavorita}) {
                        if (isFavorita){
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Favorito",
                                tint = Color(255,215,0) // Amarillo dorado
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "no favorito",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0, 187, 212)
                )
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF00BCD4),
                contentColor = Color.White,
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.menuUrl))
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Ver Menú")
                        }
                        Text("Menú", fontSize = 13.sp, color = Color.White)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${local.telefono}"))
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.Call, contentDescription = "Llamar")
                        }
                        Text("Llamar", fontSize = 13.sp, color = Color.White)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://wa.me/${local.telefono}?text=¡Hola, venimos de la app! Quiero más información sobre tu menú.")
                            )
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.MailOutline, contentDescription = "WhatsApp")
                        }
                        Text("WhatsApp", fontSize = 13.sp, color = Color.White)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.ubicacion))
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.LocationOn, contentDescription = "Ubicación")
                        }
                        Text("Ubicación", fontSize = 13.sp, color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Habilita scroll vertical
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = local.imagenUrl,
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.error)
                ),
                contentDescription = "Imagen del local",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = local.nombre,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Horario: ${local.horario}", style = MaterialTheme.typography.bodyLarge)
                    Text("Teléfono: ${local.telefono}", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        "Días de atención: ${local.diasAtencion.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (local.imagenesExtra.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Galería",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    local.imagenesExtra.forEach { imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUrl),
                            contentDescription = "Imagen extra",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clickable { selectedImageUrl = imageUrl }
                        )
                    }
                }
            }
        }
    }
}
