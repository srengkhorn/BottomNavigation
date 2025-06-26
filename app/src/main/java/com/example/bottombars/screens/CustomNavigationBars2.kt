package com.example.bottombars.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarArrangement
import androidx.compose.material3.ShortNavigationBarDefaults
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottombars.R


@Composable
fun ExpandableCard(){
    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable{
                expanded=!expanded
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "click to ${if(expanded) "collapse" else "expanded"}"
            )
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically()+ fadeIn(),
                exit = shrinkVertically ()+ fadeOut()
            ) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "Expanded content line 1"
                    )
                    Text(
                        text = "Expanded content line 2"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomNavigationBarSample1(){
   val labels=listOf("Home","Search","Likes","Profile")
   val icons=listOf(Icons.Outlined.Home, Icons.Outlined.Search, Icons.Outlined.FavoriteBorder, Icons.Outlined.Person)
   var selectedIndex by remember { mutableIntStateOf(0) }
    ShortNavigationBar(
        modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(22.dp)),
        containerColor = Color.White,
        arrangement = ShortNavigationBarArrangement.EqualWeight
    ){
        labels.forEachIndexed { index, string ->
            ShortNavigationBarItem(
                selected = selectedIndex==index,
                onClick = {
                    selectedIndex=index
                },
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = null,
                    )
                },
                label = {
                         AnimatedVisibility(
                            visible = selectedIndex==index,
                            enter = expandHorizontally() + fadeIn(),
                            exit = shrinkHorizontally() + fadeOut()
                        ) {
                            Text(
                                text = string,
                                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                            )
                        }
                },
                modifier = Modifier,
                iconPosition = NavigationItemIconPosition.Start,
                colors = ShortNavigationBarItemDefaults.colors(
                    selectedIndicatorColor = colorResource(R.color.trans_blue2)
                )
            )
        }
    }
}

