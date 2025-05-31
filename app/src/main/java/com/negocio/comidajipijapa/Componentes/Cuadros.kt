package com.negocio.comidajipijapa.Componentes // Asegúrate que este sea tu paquete

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call // Para botón Llamar
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn // Para InfoRow (Dirección)
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter // Usamos rememberAsyncImagePainter
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.R

@Composable
fun LocalOpcion(local: Local, onClick: () -> Unit = {}) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            // No necesitas .padding(8.dp) aquí si tu LazyColumn ya tiene Arrangement.spacedBy()
            // o contentPadding. Si no, puedes añadirlo: .padding(vertical = 4.dp /*o lo que necesites*/)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp), // Esquinas más redondeadas
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(205, 248, 184, 255) // Tu color verde pálido original
        )
    ) {
        Column {
            // Manejo de Imagen Mejorado
            val imagePainter = rememberAsyncImagePainter(
                model = local.imagenUrl,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.error),
                // Si el problema del crossfade persiste con AsyncImagePainter, considera crossfade(false)
                // en un ImageRequest.Builder(LocalContext.current).data(local.imagenUrl).crossfade(false).build()
            )
            Image(
                painter = imagePainter,
                contentDescription = "Imagen del local: ${local.nombre}",
                contentScale = ContentScale.Crop, // Importante para llenar el espacio sin distorsión
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp) // Altura de la imagen
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Redondear solo esquinas superiores
                    .background(Color.LightGray) // Fondo mientras carga la imagen
            )

            // Contenido Textual y Botones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp) // Padding interno
            ) {
                Text(
                    text = local.nombre,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), // Un poco más grande
                    color = Color(0xFF004D40) // Un verde oscuro para buen contraste
                )

                Spacer(modifier = Modifier.height(10.dp)) // Reemplaza EspacioV(8)

                // Información del local con íconos
                InfoRowWithIcon(
                    icon = Icons.Outlined.Star,
                    text = "Horario: ${local.horario}"
                )
                InfoRowWithIcon(
                    icon = Icons.Outlined.LocationOn,
                    text = "Dirección: ${local.direccionFisica}"
                )
                InfoRowWithIcon(
                    icon = Icons.Outlined.DateRange,
                    text = "Días: ${local.diasAtencion.joinToString(", ")}" // Formato con coma y espacio
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espacio antes de los botones

                // Fila de Botones de Acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Espacio entre botones
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ActionButton(
                        text = "Menú",
                        icon = Icons.Filled.Menu, // Ícono específico
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.menuUrl))
                                context.startActivity(intent)
                            } catch (e: Exception) { /* Manejar URL inválida o error */ }
                        },
                        modifier = Modifier.weight(1f), // Para distribuir el ancho equitativamente
                        buttonColors = ButtonDefaults.buttonColors( // Tus colores de botón
                            containerColor = Color(116, 165, 123, 255),
                            contentColor = Color(12, 13, 13)
                        )
                    )
                    ActionButton(
                        text = "Llamar",
                        icon = Icons.Filled.Call,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${local.telefono}"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.weight(1f),
                        buttonColors = ButtonDefaults.buttonColors(
                            containerColor = Color(116, 165, 123, 255),
                            contentColor = Color(12, 13, 13)
                        )
                    )
                    ActionButton(
                        text = "WhatsApp",
                        icon = Icons.Filled.Email, // Ícono de Chat, más apropiado que Email
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://wa.me/${local.telefono}?text=¡Hola! Vengo de la app y quiero más información sobre ${local.nombre}.")
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier.weight(1f),
                        buttonColors = ButtonDefaults.buttonColors(
                            containerColor = Color(116, 165, 123, 255),
                            contentColor = Color(12, 13, 13)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRowWithIcon(
    icon: ImageVector,
    text: String,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant // Color sutil para el ícono
) {
    Row(
        verticalAlignment = Alignment.Top, // Mejor si el texto ocupa varias líneas
        modifier = Modifier.padding(vertical = 3.dp) // Espacio vertical para cada fila de info
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null, // El texto ya es descriptivo
            tint = iconTint,
            modifier = Modifier.size(18.dp).padding(end = 8.dp) // Espacio entre ícono y texto
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface // Color de texto principal
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector?, // El ícono es opcional
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    // Padding interno del botón, ajustado para que quepa el texto e ícono
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp), // Botones con esquinas redondeadas
        colors = buttonColors,
        contentPadding = contentPadding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Centra el contenido si hay espacio extra
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null, // El texto del botón sirve de descripción
                    modifier = Modifier.size(18.dp) // Tamaño del ícono
                )
                Spacer(modifier = Modifier.width(4.dp)) // Espacio reducido entre ícono y texto
            }
            Text(
                text = text,
                fontSize = 12.sp, // Tamaño de fuente reducido para que no se corte
                maxLines = 1,     // Asegurar una sola línea
                overflow = TextOverflow.Ellipsis // Truncar con "..." si aún así no cabe
            )
        }
    }
}

// Puedes mantener tus EspacioV y EspacioH si los usas en otros lugares,
// o reemplazarlos por Spacer(modifier = Modifier.height(X.dp)) directamente.
@Composable
fun EspacioV(i: Int) {
    Spacer(modifier = Modifier.height(i.dp))
}

@Composable
fun EspacioH(i: Int) {
    Spacer(modifier = Modifier.width(i.dp))
}

// El Preview sigue siendo útil para ver tus cambios en LocalOpcion
@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
fun LocalOpcionPreview() {
    val localDemo = Local(
        id = "1",
        nombre = "Pizzería Don Genaro Auténtico Sabor", // Nombre más largo
        horario = "11:00 AM - 10:30 PM sin interrupción", // Horario más largo
        categoria = "Pizzería",
        telefono = "0987654321",
        menuUrl = "http://example.com/menu",
        ubicacion = "http://maps.google.com/lugar",
        // Usa una URL de imagen pública para que el preview funcione mejor
        imagenUrl = "https://www.publicdomainpictures.net/pictures/320000/nahled/pizza-slice.jpg",
        direccionFisica = "Avenida de las Palmeras 456, Esquina Sol Brillante, Jipijapa", // Dirección más larga
        imagenesExtra = listOf(),
        diasAtencion = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"),
        instagram = "http://instagram.com/pizzeriadongenaro"
    )
    Column(Modifier.padding(16.dp)) { // Padding alrededor para ver bien la Card en el Preview
        LocalOpcion(local = localDemo) {}
    }
}