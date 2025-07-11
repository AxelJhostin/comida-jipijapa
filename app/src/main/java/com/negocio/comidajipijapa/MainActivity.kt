package com.negocio.comidajipijapa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.negocio.comidajipijapa.Modelo.Local
import com.negocio.comidajipijapa.Navegacion.Navegacion
import com.negocio.comidajipijapa.ui.theme.ComidajipijapaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Envolvemos la navegación con nuestro tema personalizado
            ComidajipijapaTheme {
                Navegacion()
            }
        }
    }
}
