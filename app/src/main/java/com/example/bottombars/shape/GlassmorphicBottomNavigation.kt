/**
 * Copyright © [Chip Mong Commercial Bank Plc.]
 *
 * All rights reserved.
 *
 * These files are licensed under the "Chip Mong Commercial Bank Plc license."
 * Permission is granted to use, copy, modify, and distribute these files for internal purposes within "Chip Mong Commercial Bank Plc only".
 * Any other use requires the express written permission of the copyright holder.
 *
 * Designed and developed by Chip Mong Commercial Bank Plc.
 */
package com.example.bottombars.shape

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bottombars.R
import com.example.bottombars.ui.theme.FontSize
import com.example.bottombars.ui.theme.Purple40
import com.example.bottombars.ui.theme.spacing
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

data class GlassBottomNavItem(
    val title: String? = null,
    val outlinedIcon: ImageVector,
    val filledIcon: ImageVector,
)

val DefaultGlassBottomNavItems = listOf(
    GlassBottomNavItem("Home", Icons.Outlined.Home, Icons.Default.Home),
    GlassBottomNavItem("Calendar", Icons.Outlined.DateRange, Icons.Default.DateRange), // Renamed from DateRange
    GlassBottomNavItem("Favorite", Icons.Outlined.FavoriteBorder, Icons.Default.Favorite),
    GlassBottomNavItem("Email", Icons.Outlined.Email, Icons.Default.Email),
    GlassBottomNavItem("Profile", Icons.Outlined.AccountCircle, Icons.Default.AccountCircle), // Renamed from AccountCircle
)

const val TOTAL_TAB_HEIGHT = 100
const val QR_CODE_ICON = 60

class BottomNavCustomShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val width = size.width
        val padding = with(density) { 4.dp.toPx() }
        val radius = with(density) { QR_CODE_ICON.dp.toPx() / 2 } + padding // Adjust radius as needed
        return Outline.Generic(
            path = Path().apply {
                moveTo(0f, radius / 2)
                lineTo(
                    (size.width / 2) - (radius),
                    radius / 2,
                )

                // Draw the semicircle cut-out
                arcTo(
                    rect = Rect(
                        left = width / 2 - radius,
                        top = 0f,
                        right = width / 2 + radius,
                        bottom = radius,
                    ),
                    startAngleDegrees = -180f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )

                lineTo(size.width, radius / 2)
                lineTo(size.width, size.height)
                lineTo(0F, size.height)
            },
        )
    }
}

@Composable
fun GlassBottomNavigation(
    hazeState: HazeState,
    initialSelectedIndex: Int = 0,
    navigationItems: List<GlassBottomNavItem> = DefaultGlassBottomNavItems,
    onItemSelected: (index: Int, item: GlassBottomNavItem) -> Unit = { _, _ -> }
) {
    GlassNavigationBar(
        hazeState = hazeState
    ) {
        GlassBottomNavItem(
            initialSelectedIndex = initialSelectedIndex,
            navigationItems = navigationItems,
            onItemSelected = onItemSelected
        )
    }
}

@Composable
fun GlassNavigationBar(
    hazeState: HazeState,
    content: @Composable () -> Unit
) {
    val hazeStyle = HazeStyle(
        blurRadius = 10.dp,
        noiseFactor = 0.5f,
        tint = HazeTint(
            color = White.copy(alpha = .7f)
        ),
    )
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .background(Transparent)
            .height(TOTAL_TAB_HEIGHT.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .clip(BottomNavCustomShape())
                .background(White.copy(.1f), shape = BottomNavCustomShape())
                .hazeEffect(hazeState, hazeStyle)
        ) {
            content()
        }
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = MaterialTheme.spacing.tiny8)
                .size(QR_CODE_ICON.dp),
            painter = painterResource(R.drawable.ic_tab_scan),
            contentDescription = "scan",
        )
    }
}

@Composable
fun GlassBottomNavItem(
    accentColor: Color = Purple40,
    initialSelectedIndex: Int = 0,
    navigationItems: List<GlassBottomNavItem>,
    onItemSelected: (index: Int, item: GlassBottomNavItem) -> Unit = { _, _ -> }
) {
    require(navigationItems.isNotEmpty()) { "Items list cannot be empty" }
    require(initialSelectedIndex in navigationItems.indices) {
        "Initial selected index must be within items range"
    }

    var elementSelectedPos by remember { mutableIntStateOf(initialSelectedIndex) }

    Row(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.tiny8)
            .fillMaxSize(),
    ) {
        navigationItems.mapIndexed { index, item ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(MaterialTheme.spacing.large)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            elementSelectedPos = index
                            onItemSelected(index, item)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                if(index == elementSelectedPos) {
                    Icon(
                        imageVector = item.outlinedIcon,
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        tint = accentColor,
                    )
                } else {
                    Icon(
                        imageVector = item.filledIcon,
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        tint = accentColor,
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))
                if (item.filledIcon != Icons.Default.Favorite) {
                    Text(
                        text = "${item.title}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = FontSize.small
                        ),
                    )
                }
            }
        }
    }
}