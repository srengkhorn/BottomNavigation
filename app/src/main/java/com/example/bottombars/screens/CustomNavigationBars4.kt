package com.example.bottombars.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottombars.R

@Composable
fun BottomNavigationBarSample3() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val labels = listOf("Home", "Portfolio", "Watchlist", "Markets")
    val painters = listOf(
        painterResource(R.drawable.home),
        painterResource(R.drawable.briefcase),
        painterResource(R.drawable.exchange),
        painterResource(R.drawable.globe)
    )
    Surface(
        modifier = Modifier
            .padding(bottom = 20.dp)
        .fillMaxWidth(),
        color = Color.Transparent,
        shape = RoundedCornerShape(50.dp),
        tonalElevation = 16.dp,
        shadowElevation = 16.dp
    ) {
        BottomAppBar(
            modifier = Modifier
                .background(
                    shape = CircleShape,
                    color = Color.Cyan
                ),
            tonalElevation = 16.dp,
            containerColor = colorResource(R.color.bar_background),
        ) {
            labels.forEachIndexed { index, string ->
                val selected = selectedIndex == index
                Card(
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp)
                        .background(
                            color = if (selected) colorResource(R.color.icon_background) else colorResource(
                                R.color.icon_background1
                            ),
                            shape = if (selected) RoundedCornerShape(36.dp) else CircleShape
                        )
                        .padding(6.dp)
                        .clickable(onClick = { selectedIndex = index }),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected) colorResource(R.color.icon_background) else colorResource(
                            R.color.icon_background1
                        )
                    ),
                ) {
                    Row {
                        IconButton(
                            onClick = {
                                selectedIndex = index
                            },
                            modifier = Modifier.background(
                                color = if (selected) colorResource(R.color.light_green)
                                else Color.Transparent,
                                shape = CircleShape
                            )
                        ) {
                            Icon(
                                painter = painters[index],
                                contentDescription = null,
                                tint = if (selected) Color.Black else colorResource(R.color.icon_tint),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        AnimatedVisibility(
                            visible = selected,
                            enter = expandHorizontally() + fadeIn(),
                            exit = shrinkHorizontally() + fadeOut()
                        ) {
                            Text(
                                text = string,
                                color = Color.White,
                                modifier = Modifier.padding(
                                    14.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun PreviewSample(){
    BottomNavigationBarSample1()
}