package com.negocio.comidajipijapa.Componentes

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.R
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun LocalOpcion(local: Local, onClick: () -> Unit = {}) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            //containerColor = Color(224, 247, 250) // principal base
            //containerColor = Color(156,39,176) // sabores tradicionales
            containerColor = Color(205, 248, 184, 255)//verde azulado oscuro
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Modificación para redimensionar la imagen
            val painter = rememberImagePainter(
                data = local.imagenUrl,
                builder = {
                    //el crossfade hace algo raro, que la imagen la hace pequeña hasta que mueva
                    //la esa para abajo o para arriba, las razones no las sé
                    //por eso la deje en false
                    crossfade(false) // Deslizar para transición suave
                    placeholder(R.drawable.placeholder) // Placeholder
                    error(R.drawable.error) // Imagen de error
                    size(1200, 1200) // Redimensionar la imagen
                }
            )
            Image(
                painter = painter,
                contentDescription = "Imagen del local",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)  // Ajusta la altura según lo necesites
            )
            EspacioV(8)
            Text(
                text = local.nombre,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Horario: ${local.horario}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Dirección: ${local.direccionFisica}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Días: ${local.diasAtencion.joinToString()}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(116, 165, 123, 255),
                        contentColor = Color(12, 13, 13)
                    ),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(local.menuUrl))
                        context.startActivity(intent)
                    }
                ) {
                    Text("Menú")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(116, 165, 123, 255),
                        contentColor = Color(12, 13, 13)
                    ),
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${local.telefono}"))
                        context.startActivity(intent)
                    }
                ) {
                    Text("Llamar")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(116, 165, 123, 255),
                        contentColor = Color(12, 13, 13)
                    ),
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://wa.me/${local.telefono}?text=¡Hola! Quiero más información sobre tu menú.")
                        )
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("WhatsApp")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LocalOpcionPreview() {
    val localDemo = Local(
        id = "1",
        nombre = "Pizzería Don Mario",
        horario = "12:00 PM - 10:00 PM",
        categoria = "Pizzería",
        telefono = "0987654321",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.34,-80.02",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
        direccionFisica = "Av. Principal y calle 10, Jipijapa", // 👈 Añadido aquí
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE"
        ),
        diasAtencion = listOf("Lunes", "Martes", "Miércoles", "Viernes", "Sábado")
    )
    LocalOpcion(local = localDemo)
}


@Composable
fun EspacioV(i: Int) {
    Spacer(modifier = Modifier.height(i.dp))
}

@Composable
fun EspacioH(i: Int) {
    Spacer(modifier = Modifier.width(i.dp))
}