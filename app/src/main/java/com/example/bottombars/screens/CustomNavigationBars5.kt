package com.example.bottombars.screens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottombars.R

@Composable
fun BottomNavigationBarSample4(){
    val filledIcons=listOf(Icons.Default.Home, Icons.AutoMirrored.Default.Message, Icons.Default.Favorite,
        Icons.Default.Person)
    val outlinedIcons=listOf(Icons.Outlined.Home, Icons.AutoMirrored.Outlined.Message, Icons.Outlined.FavoriteBorder,
        Icons.Outlined.Person)
    var selectedIndex by remember { mutableIntStateOf(0) }
    NavigationBar {
        filledIcons.forEachIndexed { index, vector ->
            val isSelected=selectedIndex==index
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectedIndex=index
                },
                icon = {
                    IconButton(
                        onClick = {
                            selectedIndex=index
                        },
                        shape = RoundedCornerShape(2.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = if(isSelected) colorResource(R.color.meron_color) else Color.Transparent
                        )
                    ) {
                         Icon(
                             imageVector = if(isSelected) vector else outlinedIcons[index],
                             contentDescription = null,
                             tint = if(isSelected) Color.White else colorResource(R.color.icon_color)
                         )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )

            )

        }
    }
}

@Preview
@Composable
fun PreviewSample1(){
    BottomNavigationBarSample4()
}