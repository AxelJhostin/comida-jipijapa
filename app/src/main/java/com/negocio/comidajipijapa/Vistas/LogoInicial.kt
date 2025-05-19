package com.negocio.comidajipijapa.Vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.negocio.comidajipijapa.R
import kotlinx.coroutines.delay

@Composable
fun LogoInicial(navController: NavController){
//Efecto para pasar de pantalla a otra pasando 3 segundos
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("Lugares")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(R.drawable.fondoplaya), // Usa aquí tu imagen de fondo
            contentDescription = "Fondo de pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido centrado sobre la imagen
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logocomida), // Tu logo redondo
                contentDescription = "Logo",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(300.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text("¿Dónde comer?", fontSize = 25.sp, color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
            Text("Jipijapa", fontSize = 50.sp, color = Color(0xFF21D76D)) // Verde personalizado
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaLogoInicial() {
    val navController = rememberNavController()
    LogoInicial(navController)
}