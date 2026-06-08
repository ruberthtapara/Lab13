package com.example.lab13

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun BarcaScreen(modifier: Modifier = Modifier) {
    var isCardVisible by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FC BARCELONA",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF004D98),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        AnimatedVisibility(
            visible = isCardVisible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            ) + expandVertically(
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            ) + shrinkVertically(
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        ) {
            CampNouCard()
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { isCardVisible = !isCardVisible },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFA50044),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            )
        ) {
            Text(
                text = if (isCardVisible) "Ocultar Info" else "Mostrar Info Club",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}