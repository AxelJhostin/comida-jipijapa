package com.negocio.comidajipijapa.Componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    text: String,
    color: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = textColor
        )
    ) {
        Text(text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda(
    valorActual: TextFieldValue, // 1. Parámetro para el valor actual
    alCambiarValor: (TextFieldValue) -> Unit, // 2. Lambda para notificar cambios
    modifier: Modifier = Modifier // 3. Permitir pasar un modifier externo
) {
    OutlinedTextField(
        value = valorActual, // Usar el valor directamente
        onValueChange = alCambiarValor, // Llamar a la lambda cuando cambie
        modifier = modifier // Aplicar el modifier externo primero
            .fillMaxWidth()
            .padding(16.dp), // Tu padding original
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
        },
        placeholder = { Text("Buscar restaurante...") },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(178, 235, 242), // #B2EBF2
            focusedBorderColor = Color(0, 188, 212),     // #00BCD4
            cursorColor = Color(0, 188, 212),            // #00BCD4
            focusedLabelColor = Color(0, 121, 107)       // #00796B (no tiene efecto sin un 'label')
        ),
        shape = MaterialTheme.shapes.large // Tu forma original
    )
}