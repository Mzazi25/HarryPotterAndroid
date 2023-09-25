package com.mzazi.harrypotterandroid.designsystem.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun StatusBar(
    text:Boolean,
    modifier:Modifier = Modifier
) {
    Row(
        modifier = modifier
    ){
        Canvas(modifier = Modifier
            .height(16.dp)
            .width(16.dp)){
            drawCircle(
                when (text) {
                    true ->{
                        Color.Green
                    }
                    else ->{
                        Color.Gray
                    }

                }
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text =
            when (text){
                true ->{
                    "Alive"
                }
                else ->{
                    "Dead/Unknown"
                }
            },
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
}