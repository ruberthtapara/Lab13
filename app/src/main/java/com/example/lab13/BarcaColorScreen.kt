package com.example.lab13

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BarcaColorScreen(modifier: Modifier = Modifier) {
    var isVipMode by rememberSaveable { mutableStateOf(false) }
    val azulBarca = Color(0xFF004D98)
    val doradoBarca = Color(0xFFEDBB00)
    val backgroundColorAnimated by animateColorAsState(
        targetValue = if (isVipMode) doradoBarca else azulBarca,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "ColorCardAnimation"
    )
    val textColor = if (isVipMode) Color(0xFF004D98) else Color.White

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MEMBRESÍA CULÉ",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF004D98),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(200.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColorAnimated // <--- AQUÍ: Pasamos el color animado
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isVipMode) "Socio VIP" else "Socio Estándar",
                    color = textColor,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isVipMode) "Acceso total a Palcos\nAsientos Preferenciales" else "Acceso a Tribuna General\nDescuentos en Tienda",
                    color = textColor.copy(alpha = 0.8f),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { isVipMode = !isVipMode },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFA50044), // Grana
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = if (isVipMode) "Cambiar a Estándar" else "Mejorar a VIP",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}