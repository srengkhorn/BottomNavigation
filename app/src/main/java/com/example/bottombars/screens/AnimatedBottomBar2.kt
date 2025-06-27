package com.example.bottombars.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bottombars.R
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.ShapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable

@Composable
fun AnimatedBar1(){
    val vectorsIds=listOf(R.drawable.home,R.drawable.location,R.drawable.exchange,
        R.drawable.briefcase)
    var selectedIndex by remember { mutableIntStateOf(0) }
    AnimatedNavigationBar(
        modifier = Modifier,
        selectedIndex = selectedIndex,
        barColor = Color.Cyan,
        ballColor = Color.Magenta,
        cornerRadius = ShapeCornerRadius(12f,12f,0f,0f),
        ballAnimation = Straight(tween(300)),
        indentAnimation = Height(tween(300))
    ) {
         vectorsIds.forEachIndexed { index, vectorId ->
             Box(
                 modifier = Modifier
                     .height(50.dp)
                     .noRippleClickable({selectedIndex=index}),
                 contentAlignment = Alignment.Center
             ) {
                 Icon(
                     painter = painterResource(vectorId),
                     contentDescription = null,
                     modifier = Modifier.size(24.dp),
                     tint = if(selectedIndex==index) Color.White else Color.LightGray
                     )
             }
         }
    }
}
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnimatedBar2() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val vectors = listOf(
        Icons.Outlined.Home, Icons.AutoMirrored.Outlined.Message, Icons.Outlined.FavoriteBorder,
        Icons.Outlined.PersonOutline
    )
    AnimatedNavigationBar(
            modifier = Modifier.padding(bottom = 120.dp).background(color = Color.White, shape = RoundedCornerShape(24.dp)),
            selectedIndex = selectedIndex,
            barColor = colorResource(R.color.meron_background_color),
            ballColor = colorResource(R.color.meron_color),
            cornerRadius = ShapeCornerRadius(92f, 92f, 12f, 12f),
            ballAnimation = Parabolic(tween()),
            indentAnimation = Height(tween())
        ) {
            vectors.forEachIndexed { index, vector ->
                val isSelected = selectedIndex == index
                ShortNavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        selectedIndex = index
                    },
                    icon = {
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .noRippleClickable({ selectedIndex = index }),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = vector,
                                contentDescription = null,
                                tint = if (isSelected) colorResource(R.color.meron_color) else colorResource(
                                    R.color.icon_color)
                            )
                        }
                    },
                    label = {},
                    colors = ShortNavigationBarItemDefaults.colors(
                        selectedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    }


