package com.example.bottombars.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarArrangement
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bottombars.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomNavigationBarSample2(){
    var selectedIndex by remember { mutableIntStateOf(0) }
    val labels=listOf("Home","Portfolio","Watchlist","Markets")
    val painters=listOf(
        painterResource(R.drawable.home),
        painterResource(R.drawable.briefcase),
        painterResource(R.drawable.exchange),
        painterResource(R.drawable.globe)
    )
    ShortNavigationBar(
        modifier = Modifier
            .background(
                color = colorResource(R.color.bar_background),
                shape = RoundedCornerShape(24.dp)
            )
            ,
        arrangement = ShortNavigationBarArrangement.Centered
    ){
       labels.forEachIndexed { index, string ->
               ShortNavigationBarItem(
                   selected = selectedIndex == index,
                   onClick = {
                       selectedIndex = index
                   },
                   modifier = Modifier
                       .padding(start = 5.dp)
                       .width(if (selectedIndex == index) 120.dp else 70.dp)
                       .background(
                           color = colorResource(R.color.icon_background),
                           shape = if (selectedIndex == index) RoundedCornerShape(24.dp) else CircleShape
                       ),
                   icon = {
                       FilledIconButton(
                           onClick = { selectedIndex = index },
                           shape = CircleShape,
                           colors = IconButtonDefaults.iconButtonColors(
                               containerColor = if (selectedIndex == index) colorResource(R.color.light_green)
                               else colorResource(R.color.icon_background)
                           )
                       ) {
                           Icon(
                               painter = painters[index],
                               contentDescription = null,
                               modifier = Modifier.size(24.dp)
                           )
                       }
                   },
                   label = {
                       if (selectedIndex == index) {
                           Text(
                               text = string,
                               color = Color.White
                           )
                       }
                   },
                   colors = ShortNavigationBarItemDefaults.colors(
                       selectedIconColor = colorResource(R.color.black),
                       selectedIndicatorColor = Color.Transparent,
                       unselectedIconColor = colorResource(R.color.icon_tint)
                   ),
                   iconPosition = NavigationItemIconPosition.Start,
               )
           }
    }
}