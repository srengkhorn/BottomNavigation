package com.example.bottombars.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombars.R
import com.exyte.animatednavbar.utils.noRippleClickable

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnimatedBar3(){
    val vectors = listOf(
        Icons.Outlined.Home, Icons.AutoMirrored.Outlined.Message, Icons.Outlined.FavoriteBorder,
        Icons.Outlined.PersonOutline

    )
    var selectedItem by remember { mutableIntStateOf(0) }
    AnimatedBottomBar (
        modifier = Modifier.padding(bottom = 150.dp, start = 20.dp, end = 20.dp).shadow(elevation = 16.dp),
        bottomBarHeight =64.dp,
        containerColor = Color.Cyan,
        contentColor = Color.Magenta,
        containerShape = RoundedCornerShape(16.dp),
        selectedItem=selectedItem,
        itemSize = 4,
        indicatorStyle = IndicatorStyle.FILLED,
        indicatorColor = Color.Magenta,
        indicatorHeight = 4.dp,
        animationSpec = spring(Spring.DampingRatioMediumBouncy),
        indicatorDirection = IndicatorDirection.TOP,
        indicatorShape = RoundedCornerShape(24.dp),
    ){
        vectors.forEachIndexed { index, vector ->

            val isSelected = selectedItem == index

            Box(
                modifier = Modifier
                    .height(60.dp).width(80.dp)
                    .noRippleClickable({selectedItem=index}),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = vector,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if(isSelected) Color.White else Color.LightGray
                )
            }
        }
    }

}
@Preview(showBackground = false)
@Composable
fun PreviewAnimation(){
    AnimatedBar3()
}