package com.example.bottombars.screens


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarDefaults
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
import androidx.compose.ui.text.font.FontWeight
import com.example.bottombars.R


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NavigationBarSample(){
    var isItemSelected by remember { mutableIntStateOf(0) }
    val listOfLabels=listOf("Chats","Updates","Communities","Calls")
    val listOfSelectedIcons=listOf(Icons.Default.Chat, Icons.Default.Update, Icons.Default.Groups,
        Icons.Default.Call)
    val listOfUnselectedIcons=listOf(Icons.Outlined.Chat, Icons.Outlined.Update, Icons.Outlined.Groups,
        Icons.Outlined.Call)
    ShortNavigationBar(
        modifier = Modifier,
        containerColor = Color.White,
        arrangement = ShortNavigationBarDefaults.arrangement,
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        listOfLabels.forEachIndexed {index,item->
            ShortNavigationBarItem(
                selected = isItemSelected==index,
                onClick = {
                    isItemSelected=index
                },
                icon = {
                    Icon(
                        imageVector = if(isItemSelected==index) listOfSelectedIcons[index] else listOfUnselectedIcons[index],
                        contentDescription = null
                    )
                },
                modifier = Modifier,
                enabled = true,
                label = {
                    Text(
                        text = item,
                        color = Color.Black,
                        fontWeight =  if(isItemSelected==index) FontWeight.Bold else FontWeight.Medium
                    )
                },

                colors = ShortNavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(R.color.dark_green),
                    selectedTextColor = Color.Black,
                    selectedIndicatorColor = colorResource(R.color.trans_green1),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Gray,
                    disabledIconColor = Color.Blue,
                    disabledTextColor = Color.Cyan
                ),
                iconPosition = NavigationItemIconPosition.Top
            )
        }
    }
}
