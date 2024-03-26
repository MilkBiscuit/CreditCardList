package com.cheng.hellodemo.ui.common.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheng.hellodemo.ui.theme.HelloDemoTheme


private const val ROTATION_DURATION = 400
private const val STROKE_RATIO: Float = 1f / 15f
private const val INNER_RADIUS_RATIO: Float = 5f / 12f

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(ROTATION_DURATION, easing = LinearEasing)
        ), label = "angle"
    )
    GradientRing(
        modifier = modifier.graphicsLayer { rotationZ = angle }
    )
}

@Composable
private fun GradientRing(
    modifier: Modifier = Modifier,
) {
    val brush = sweepGradientBrush()
    Canvas(
        modifier = modifier
    ) {
        val sideWithInPx = minOf(this.size.width, this.size.height)
        val strokeWidthInPx = sideWithInPx * STROKE_RATIO
        val innerRadiusInPx = sideWithInPx * INNER_RADIUS_RATIO

        drawCircle(
            brush = brush,
            radius = innerRadiusInPx,
            style = Stroke(strokeWidthInPx)
        )
    }
}

@Composable
private fun sweepGradientBrush() = Brush.sweepGradient(
    listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.primary,
    )
)


////////////////////////////////////// Preview //////////////////////////////////////
@Preview(showBackground = true)
@Composable
private fun PreviewCircularProgressIndicator() {
    HelloDemoTheme {
        CircularProgressIndicator(modifier = Modifier.size(300.dp))
    }
}
