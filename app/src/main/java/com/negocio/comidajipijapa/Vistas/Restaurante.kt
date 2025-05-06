package com.negocio.comidajipijapa.Vistas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.negocio.comidajipijapa.Componentes.ActionButton
import com.negocio.comidajipijapa.Componentes.EspacioV
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Restaurante(navController: NavController, local: Local) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurante", color = Color.White) },
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
                    containerColor = Color(0, 187, 212) // Color principal
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // Imagen del restaurante - principal
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
            //Galeria de imagenes extras
            if (local.imagenesExtra.isNotEmpty()){
                Text(
                    text = "Galería",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.
                    fillMaxWidth().
                    height(120.dp)
                ) {
                    items(local.imagenesExtra){ imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUrl),
                            contentDescription = "Imagenes extrasss",
                            modifier = Modifier
                                .width(160.dp)
                                .fillMaxHeight()
                        )

                    }
                }
                EspacioV(16)

            }

            // Tarjeta con detalles del restaurante
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)) // Fondo suave celeste
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = local.nombre,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B) // Verde oscuro
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

            Spacer(modifier = Modifier.height(24.dp))

            // Botones de acción
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val buttonColor = Color(77, 208, 225)
                val textColor = Color(12, 13, 13)

                ActionButton(
                    text = "Ver Menú",
                    color = buttonColor,
                    textColor = textColor
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.menuUrl))
                    context.startActivity(intent)
                }

                ActionButton(
                    text = "Llamar al restaurante",
                    color = buttonColor,
                    textColor = textColor
                ) {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${local.telefono}"))
                    context.startActivity(intent)
                }

                ActionButton(
                    text = "Escribir por WhatsApp",
                    color = buttonColor,
                    textColor = textColor
                ) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://wa.me/${local.telefono}?text=¡Hola! Quiero más información sobre tu menú.")
                    )
                    context.startActivity(intent)
                }

                ActionButton(
                    text = "Ver Ubicación",
                    color = buttonColor,
                    textColor = textColor
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.ubicacion))
                    context.startActivity(intent)
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
        nombre = "Pizzería Don Mario",
        horario = "12:00 PM - 10:00 PM",
        categoria = "Pizzería",
        telefono = "0987654321",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.34,-80.02",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE"
        ),
        diasAtencion = listOf("Lunes", "Martes", "Miércoles", "Viernes", "Sábado")
    )
    Restaurante(navController = navController, local = localDemo)
}
