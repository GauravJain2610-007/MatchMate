package com.example.matchmate.presentation.screens.dashboard.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIconButton(
    painter: Painter,
    contentDescription: String = "",
    onClick: () -> Unit= {},
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    iconTint: Color = Color.White,
    size: Dp = 48.dp
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(size)
            .background(color = backgroundColor, shape = CircleShape)
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }
}
