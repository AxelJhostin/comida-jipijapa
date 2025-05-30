package com.negocio.comidajipijapa.Vistas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.* // Para días de atención
import androidx.compose.material.icons.outlined.Info // Un ícono genérico si es necesario
import androidx.compose.material.icons.outlined.LocationOn // Para dirección
import androidx.compose.material.icons.outlined.Phone // Para teléfono
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
                                imageVector = Icons.Filled.Star, // Podrías usar Icons.Outlined.Star para no favorito
                                contentDescription = "No favorito",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(33,215,109)
                )
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = Color(33,215,109),
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
                            Icon(Icons.Default.Menu, contentDescription = "Ver Menú") // Ícono más específico
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
                            // Puedes usar un ícono específico de WhatsApp si lo tienes en tus resources
                            // o uno genérico como MailOutline o Chat
                            Icon(painterResource(id = R.drawable.instagram), contentDescription = "WhatsApp", modifier = Modifier.size(24.dp)) // Asumiendo que tienes ic_whatsapp
                            // Icon(Icons.Default.Chat, contentDescription = "WhatsApp") // Alternativa
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

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.instagram))
                            context.startActivity(intent)
                        }) {
                            Icon(painterResource(id = R.drawable.instagram), contentDescription = "Instagram", modifier = Modifier.size(24.dp)) // Asumiendo que tienes ic_instagram
                            // Icon(Icons.Default.Share, contentDescription = "Red Social") // Alternativa
                        }
                        Text("Instagram", fontSize = 13.sp, color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(250, 250, 250))
                .verticalScroll(rememberScrollState()) // Habilita scroll vertical
                .padding(horizontal = 16.dp, vertical = 8.dp) // Ajusta padding general si es necesario
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = local.imagenUrl,
                    placeholder = painterResource(R.drawable.placeholder), // Asegúrate que estos drawables existen
                    error = painterResource(R.drawable.error)
                ),
                contentDescription = "Imagen del local",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp) // Un poco más de altura
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp)) // Placeholder background
                    .clickable { selectedImageUrl = local.imagenUrl } // Si quieres que la imagen principal también sea clickeable
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp), // Esquinas un poco más redondeadas
                colors = CardDefaults.cardColors(
                    containerColor = Color.White // Un fondo blanco limpio, o el que tenías si te gustaba
                    // containerColor = Color(0xFFE0F7FA) // Tu color original
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Sombra sutil
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 16.dp) // Más padding interno
                        .fillMaxWidth()
                ) {
                    Text(
                        text = local.nombre,
                        fontSize = 26.sp, // Un poco más grande
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B), // Tu color original (Teal oscuro)
                        modifier = Modifier
                            .padding(bottom = 16.dp) // Más espacio después del nombre
                            .align(Alignment.CenterHorizontally) // Centrar el nombre
                    )

                    DetailItemRow(
                        icon = Icons.Outlined.Info,
                        label = "Horario:",
                        value = local.horario,
                        iconColor = Color(0xFF00796B)
                    )
                    DetailItemRow(
                        icon = Icons.Outlined.Phone,
                        label = "Teléfono:",
                        value = local.telefono,
                        iconColor = Color(0xFF00796B)
                    )
                    DetailItemRow(
                        icon = Icons.Outlined.DateRange,
                        label = "Atención:", // Más corto
                        value = local.diasAtencion.joinToString(", "), // Mejor formato para la lista
                        iconColor = Color(0xFF00796B)
                    )
                    DetailItemRow(
                        icon = Icons.Outlined.LocationOn,
                        label = "Dirección:",
                        value = local.direccionFisica,
                        iconColor = Color(0xFF00796B)
                    )
                }
            }

            if (local.imagenesExtra.isNotEmpty()) {
                Spacer(modifier = Modifier.height(20.dp)) // Más espacio
                Text(
                    text = "Galería de Imágenes", // Título más descriptivo
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp, start = 4.dp) // Pequeño padding al inicio
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) { // Un poco más de espacio entre imágenes
                    local.imagenesExtra.forEach { imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = imageUrl,
                                placeholder = painterResource(R.drawable.placeholder), // Placeholder diferente si quieres
                                error = painterResource(R.drawable.error)
                            ),
                            contentDescription = "Imagen extra de la galería",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp) // Un poco más altas
                                .background(Color.LightGray, RoundedCornerShape(10.dp))
                                .clickable { selectedImageUrl = imageUrl }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espacio al final del scroll
        }
    }

    // Modal para imagen seleccionada (opcional, pero buena idea si las imágenes son clickeables)
    if (selectedImageUrl != null) {
        AlertDialog(
            onDismissRequest = { selectedImageUrl = null },
            text = {
                Image(
                    painter = rememberAsyncImagePainter(model = selectedImageUrl),
                    contentDescription = "Imagen ampliada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp) // Limitar altura máxima
                )
            },
            confirmButton = {
                Button(onClick = { selectedImageUrl = null }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun DetailItemRow(
    icon: ImageVector,
    label: String,
    value: String,
    iconColor: Color = MaterialTheme.colorScheme.primary // Color por defecto para el ícono
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp), // Espacio vertical entre cada item
        verticalAlignment = Alignment.Top // Alinea el ícono con la primera línea del texto si el valor es largo
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier
                .size(20.dp) // Tamaño del ícono
                .padding(end = 10.dp) // Espacio entre ícono y texto
        )
        Column { // Usamos Column para que el label y el value puedan estar uno encima de otro si es necesario o para más control.
            // Pero aquí los pondremos en línea con Text Spans si fuera necesario o simplemente concatenados.
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold, // Label un poco más destacado
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Un gris oscuro, no tan fuerte como el negro puro
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface // Color de texto principal
            )
        }
    }
}