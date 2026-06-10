package com.example.lab13

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BarcaSizeAndPositionScreen(modifier: Modifier = Modifier) {
    var isAttacking by rememberSaveable { mutableStateOf(false) }
    val boxSizeAnimated by animateDpAsState(
        targetValue = if (isAttacking) 160.dp else 90.dp, // Crece al atacar
        animationSpec = tween(durationMillis = 600),
        label = "SizeAnimation"
    )

    val offsetXAnimated by animateDpAsState(
        targetValue = if (isAttacking) 80.dp else 0.dp,
        animationSpec = tween(durationMillis = 600),
        label = "PositionXAnimation"
    )

    val offsetYAnimated by animateDpAsState(
        targetValue = if (isAttacking) (-100).dp else 0.dp,
        animationSpec = tween(durationMillis = 600),
        label = "PositionYAnimation"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ESTRATEGIA TÁCTICA",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF004D98),
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart // Inicia abajo a la izquierda
        ) {

            Box(
                modifier = Modifier
                    .offset(x = offsetXAnimated, y = offsetYAnimated) // Modifica la posición
                    .size(boxSizeAnimated) // Modifica el tamaño
                    .background(Color(0xFFEDBB00), RoundedCornerShape(24.dp)), // Dorado Barça
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isAttacking) "¡GOL!" else "Balón",
                    color = Color(0xFF004D98),
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isAttacking) 22.sp else 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { isAttacking = !isAttacking },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFA50044),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = if (isAttacking) "Recomponer Defensa" else "Iniciar Ataque",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}