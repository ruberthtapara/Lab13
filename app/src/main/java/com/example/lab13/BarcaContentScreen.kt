package com.example.lab13

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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

enum class ScreenState {
    CARGANDO, CONTENIDO, ERROR
}

@Composable
fun BarcaContentScreen(modifier: Modifier = Modifier) {
    var currentState by rememberSaveable { mutableStateOf(ScreenState.CARGANDO) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "CENTRAL DE PARTIDOS",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF004D98),
            modifier = Modifier.padding(top = 24.dp)
        )
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                (fadeIn(animationSpec = tween(durationMillis = 500)) + scaleIn(initialScale = 0.92f, animationSpec = tween(500))) togetherWith
                        (fadeOut(animationSpec = tween(durationMillis = 400)) + scaleOut(targetScale = 0.92f, animationSpec = tween(400)))
            },
            label = "StateContentAnimation",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 32.dp)
        ) { targetState ->
            when (targetState) {
                ScreenState.CARGANDO -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color(0xFFA50044),
                            strokeWidth = 4.dp,
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Actualizando marcador en vivo...",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                ScreenState.CONTENIDO -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF004D98)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("LALIGA EA SPORTS", color = Color(0xFFEDBB00), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("FCB\n(3)", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black, textAlign = TextAlign.Center)
                                Text("VS", color = Color(0xFFEDBB00), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text("RMA\n(1)", color = Color.White.copy(alpha = 0.7f), fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFA50044), RoundedCornerShape(50.dp))
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                            ) {
                                Text("¡Finalizado!", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                ScreenState.ERROR -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color(0xFFFEE2E2), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("⚠️", fontSize = 28.sp)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Error de conexión",
                            color = Color(0xFFDC2626),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No se pudieron sincronizar los servidores de Camp Nou. Inténtalo de nuevo.",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { currentState = ScreenState.CARGANDO },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B7280))
            ) {
                Text("Cargar")
            }
            Button(
                onClick = { currentState = ScreenState.CONTENIDO },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D98))
            ) {
                Text("Éxito")
            }
            Button(
                onClick = { currentState = ScreenState.ERROR },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626))
            ) {
                Text("Error")
            }
        }
    }
}