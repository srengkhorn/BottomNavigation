package com.example.bottombars.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bottombars.R

@Composable
fun NavigationSample(modifier: Modifier= Modifier,navController: NavController){
    var isSelected by remember { mutableStateOf(Destination.Chats.ordinal) }
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.White,
        tonalElevation = 16.dp,
    ) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = isSelected==index,
                onClick = {
                    navController.navigate(destination.route)
                    isSelected=index
                },
                icon = {
                    Icon(
                        imageVector = if(isSelected==index) destination.selectedImageVector else destination.unselectedImageVector,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = destination.label,
                        fontWeight = if(isSelected==index) FontWeight.Bold else FontWeight.Medium,
                        color = Color.Black
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(R.color.dark_green),
                    selectedTextColor = Color.Black,
                    indicatorColor = colorResource(R.color.trans_green1),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black
                ),
                alwaysShowLabel = true
            )
        }
    }
}