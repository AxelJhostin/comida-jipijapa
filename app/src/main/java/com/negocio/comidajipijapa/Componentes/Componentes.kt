package com.negocio.comidajipijapa.Componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun DetailItemRow(
    icon: ImageVector,
    label: String,
    value: String,
    iconColor: Color // Has estado pasando este color, así que lo mantengo como requerido
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp), // Espacio vertical para cada fila
        verticalAlignment = Alignment.Top // Alinea el ícono con la primera línea del texto
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier
                .size(28.dp) // <--- CAMBIO: Ícono más grande (antes 20.dp)
                .padding(end = 10.dp) // Espacio entre el ícono y el texto (puedes ajustarlo si es necesario)
        )
        Column {
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge, // Manteniendo el estilo que tenías
                color = MaterialTheme.colorScheme.onSurfaceVariant // Color para la etiqueta
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge, // Manteniendo el estilo que tenías
                color = MaterialTheme.colorScheme.onSurface // Color para el valor
            )
        }
    }
}