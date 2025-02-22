package com.example.financysystem.presentation.screens.components

import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green


@Composable
fun BubbleAnimation(
    bubbleColor1: Color,
    bubbleColor2: Color,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val radiusBigCircle = 100.dp
    val radiusSmallCircle = 75.dp
    val infiniteTransition = rememberInfiniteTransition()

    Box(
        modifier = modifier
    ) {
        val xValue = infiniteTransition.animateFloat(
            initialValue = radiusBigCircle.value,
            targetValue = screenWidthPx - radiusBigCircle.value,
            animationSpec = infiniteRepeatable(
                animation = tween(9000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value

        val yValue = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 600f,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value

        val xValue2 = infiniteTransition.animateFloat(
            initialValue = screenWidthPx - radiusBigCircle.value,
            targetValue = radiusBigCircle.value,
            animationSpec = infiniteRepeatable(
                animation = tween(9500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value

        val yValue2 = infiniteTransition.animateFloat(
            initialValue = 600f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(8500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value

        val xValue3 = infiniteTransition.animateFloat(
            initialValue = screenWidthPx - radiusSmallCircle.value,
            targetValue = radiusSmallCircle.value,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value

        val yValue3 = infiniteTransition.animateFloat(
            initialValue = 150f,
            targetValue = 600f,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bubbleColor1, bubbleColor2),
                    start = Offset(xValue - 90, yValue),
                    end = Offset(xValue + 90, yValue)
                ),
                radius = radiusBigCircle.value,
                center = Offset(xValue, yValue)
            )
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bubbleColor1, bubbleColor2),
                    start = Offset(xValue2 - 90, yValue2),
                    end = Offset(xValue2 + 90, yValue2)
                ),
                radius = radiusBigCircle.value,
                center = Offset(xValue2, yValue2)
            )
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bubbleColor1, bubbleColor2),
                    start = Offset(xValue3 - 90, yValue3),
                    end = Offset(xValue3 + 90, yValue3)
                ),
                radius = radiusSmallCircle.value,
                center = Offset(xValue3, yValue3)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BubbleAnimationPreview() {
    BubbleAnimation(
        bubbleColor1 = green,
        bubbleColor2 = gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}