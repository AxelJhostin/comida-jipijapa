package com.negocio.comidajipijapa.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 1. Esquema de colores para el Modo Oscuro usando la paleta oficial
private val DarkColorScheme = darkColorScheme(
    primary = NaranjaPrincipal,
    onPrimary = BlancoPuro,
    secondary = NaranjaOscuro,
    onSecondary = BlancoPuro,
    background = FondoOscuro,
    onBackground = TextoPrincipalOscuro,
    surface = FondoOscuro,
    onSurface = TextoPrincipalOscuro,
    onSurfaceVariant = TextoSecundarioOscuro,
    error = Color(0xFFCF6679),
    onError = Color.Black
)

// 2. Esquema de colores para el Modo Claro usando la paleta oficial
private val LightColorScheme = lightColorScheme(
    primary = NaranjaPrincipal,
    onPrimary = BlancoPuro,
    secondary = NaranjaOscuro,
    onSecondary = BlancoPuro,
    background = CremaFondo,
    onBackground = TextoPrincipal,
    surface = CremaFondo,
    onSurface = TextoPrincipal,
    onSurfaceVariant = TextoSecundario,
    error = Color(0xFFB00020),
    onError = Color.White
)

@Composable
fun ComidajipijapaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // 3. Color dinámico DESACTIVADO por defecto para mantener tu marca.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Usamos el color primario para la barra de estado.
            window.statusBarColor = colorScheme.primary.toArgb()
            // Ajusta el color de los iconos de la barra de estado (reloj, batería)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asume que tienes Typography.kt
        content = content
    )
}
