package com.example.bottombars.shape

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil3.compose.AsyncImage
import com.example.bottombars.liquidglass.LiquidGlassStyle
import com.example.bottombars.liquidglass.LocalLiquidGlassProviderState
import com.example.bottombars.liquidglass.liquidGlass
import com.example.bottombars.liquidglass.rememberLiquidGlassProviderState
import com.kyant.expressa.m3.LocalColorScheme
import com.kyant.expressa.m3.color.ColorScheme
import com.kyant.expressa.overscroll.rememberOffsetOverscrollFactory
import com.kyant.expressa.prelude.onSurface
import com.kyant.expressa.prelude.surfaceContainer


@Composable
fun GlassiOSDemo() {
    val rippleConfiguration = remember {
        RippleConfiguration(
            rippleAlpha = RippleAlpha(
                hoveredAlpha = 2f * 0.08f,
                focusedAlpha = 2f * 0.10f,
                pressedAlpha = 2f * 0.10f,
                draggedAlpha = 2f * 0.16f
            )
        )
    }
    val overscrollFactory = rememberOffsetOverscrollFactory()

    CompositionLocalProvider(
        LocalColorScheme provides ColorScheme.systemDynamic()
    ) {
        CompositionLocalProvider(
            LocalContentColor provides onSurface,
            LocalRippleConfiguration provides rippleConfiguration,
            LocalIndication provides ripple(),
            LocalOverscrollFactory provides overscrollFactory,
        ) {
            val view = LocalView.current
            val background = surfaceContainer
            LaunchedEffect(view, background) {
                view.rootView.background = background.toArgb().toDrawable()
            }
            Scaffold(
                bottomBar = {
                    GlassiOSBottomNavigation()
                }
            ) { padding ->
                LazyColumn(
                    Modifier.fillMaxSize(),
                    contentPadding = padding
                ) {
                    items(50) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color.DarkGray, RoundedCornerShape(12.dp))
                                .border(
                                    width = Dp.Hairline,
                                    color = Color.White.copy(alpha = .5f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            AsyncImage(
                                model = "https://source.unsplash.com/random?neon,$it",
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GlassiOSBottomNavigation() {
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    val providerState = rememberLiquidGlassProviderState()
    CompositionLocalProvider(
        LocalLiquidGlassProviderState provides providerState
    ) {
        Box(
            modifier = Modifier
                .safeContentPadding()
                .padding(vertical = 24.dp, horizontal = 64.dp)
                .fillMaxWidth()
                .height(64.dp)
                .liquidGlass(
                    remember {
                        LiquidGlassStyle(
                            shape = CircleShape,
                            chromaMultiplier = 1.5f
                        )
                    }
                )
                .clip(CircleShape)
                .border(
                    width = Dp.Hairline,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = .8f),
                            Color.White.copy(alpha = .2f),
                        ),
                    ),
                    shape = CircleShape
                )
        ) {
            BottomBarTabs(
                tabs,
                selectedTab = selectedTabIndex,
                onTabSelected = {
                    selectedTabIndex = tabs.indexOf(it)
                }
            )

            val animatedSelectedTabIndex by animateFloatAsState(
                targetValue = selectedTabIndex.toFloat(), label = "animatedSelectedTabIndex",
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioLowBouncy,
                )
            )

            val animatedColor by animateColorAsState(
                targetValue = tabs[selectedTabIndex].color,
                label = "animatedColor",
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                )
            )

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            ) {
                val tabWidth = size.width / tabs.size
                drawCircle(
                    color = animatedColor.copy(alpha = .6f),
                    radius = size.height / 2,
                    center = Offset(
                        (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                        size.height / 2
                    )
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            ) {
                val path = Path().apply {
                    addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
                }
                val length = PathMeasure().apply { setPath(path, false) }.length

                val tabWidth = size.width / tabs.size
                drawPath(
                    path,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            animatedColor.copy(alpha = 0f),
                            animatedColor.copy(alpha = 1f),
                            animatedColor.copy(alpha = 1f),
                            animatedColor.copy(alpha = 0f),
                        ),
                        startX = tabWidth * animatedSelectedTabIndex,
                        endX = tabWidth * (animatedSelectedTabIndex + 1),
                    ),
                    style = Stroke(
                        width = 6f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(length / 2, length)
                        )
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGlassiOS() {
    GlassiOSDemo()
}