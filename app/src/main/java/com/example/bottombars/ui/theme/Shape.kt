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
package com.example.bottombars.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

@Suppress("UnusedReceiverParameter")
val Shapes.normal: CornerBasedShape
    get() = RoundedCornerShape(12.dp)

val Shapes.medium: CornerBasedShape
    get() = RoundedCornerShape(16.dp)

val Shapes.bottomSheet: CornerBasedShape
    get() = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

object Spacing {
    val zero = 0.dp
    val one = 1.dp
    val tiny2 = 2.dp
    val tiny = 4.dp
    val tiny6 = 6.dp
    val tiny8 = 8.dp
    val small10 = 10.dp
    val small = 12.dp
    val normal14 = 14.dp
    val normal = 16.dp
    val normal18 = 18.dp
    val medium = 20.dp
    val large = 24.dp
    val large26 = 26.dp
    val xLarge = 30.dp
    val xxLarge = 36.dp
    val xxxLarge = 56.dp
}

object Border {
    val zero = 0.dp
    val one = 1.dp
    val tiny2 = 2.dp
    val tiny = 4.dp
    val tiny6 = 6.dp
    val tiny8 = 8.dp
    val small = 12.dp
    val normal = 16.dp
    val medium = 20.dp
    val large = 24.dp
    val xLarge = 30.dp
    val xxLarge = 36.dp
}

val LocalSpacing = compositionLocalOf { Spacing }

val LocalBorder = compositionLocalOf { Border }

@Suppress("UnusedReceiverParameter")
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

@Suppress("UnusedReceiverParameter")
val MaterialTheme.border: Border
    @Composable
    @ReadOnlyComposable
    get() = LocalBorder.current

object FontSize {
    val small = 12.sp
    val normal = 16.sp
    val medium = 18.sp
    val large = 22.sp
    val xLarge = 28.sp
    val xLarge30 = 30.sp
    val xxLarge = 36.sp
}

val LocalFontSize = compositionLocalOf { FontSize }

@Suppress("UnusedReceiverParameter")
val MaterialTheme.fontSize: FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current

val TextUnit.nonScaledSp
    @Composable
    get() = (this.value / LocalDensity.current.fontScale).sp
