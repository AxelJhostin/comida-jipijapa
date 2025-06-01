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
import androidx.compose.material.icons.outlined.*
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
import com.negocio.comidajipijapa.Componentes.DetailItemRow
import com.negocio.comidajipijapa.Data.FavoritosPrefs
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Restaurante(navController: NavController, local: Local) {
    val context = LocalContext.current
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }

    val favoritosPrefs = remember { FavoritosPrefs(context) }
    var isFavorita by remember { mutableStateOf(favoritosPrefs.esFavorito(local.id)) }

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
                    IconButton(onClick = {
                        isFavorita = !isFavorita
                        if (isFavorita) {
                            favoritosPrefs.guardarFavorito(local.id)
                        } else {
                            favoritosPrefs.eliminarFavorito(local.id)
                        }
                    }) {
                        Icon(
                            imageVector = if (isFavorita) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = "Favorito",
                            tint = if (isFavorita) Color(255, 215, 0) else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(33, 215, 109)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(33, 215, 109),
                contentColor = Color.White,
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomIconText("Menú", Icons.Default.Menu) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(local.menuUrl)))
                    }
                    BottomIconText("Llamar", Icons.Default.Call) {
                        context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${local.telefono}")))
                    }
                    BottomIconText("WhatsApp", Icons.Filled.AccountBox) {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://wa.me/${local.telefono}?text=¡Hola, venimos de la app! Quiero más información sobre tu menú.")
                        )
                        context.startActivity(intent)
                    }
                    BottomIconText("Ubicación", Icons.Default.LocationOn) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(local.ubicacion)))
                    }
                    BottomIconText("Instagram", Icons.Default.Share) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(local.instagram)))
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
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
                    .height(220.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp))
                    .clickable { selectedImageUrl = local.imagenUrl }
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = local.nombre,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally)
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
                        label = "Atención:",
                        value = local.diasAtencion.joinToString(", "),
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
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Galería de Imágenes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    local.imagenesExtra.forEach { imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = imageUrl,
                                placeholder = painterResource(R.drawable.placeholder),
                                error = painterResource(R.drawable.error)
                            ),
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

@Composable
fun BottomIconText(label: String, icon: ImageVector, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(24.dp))
        }
        Text(label, fontSize = 13.sp, color = Color.White)
    }
}
