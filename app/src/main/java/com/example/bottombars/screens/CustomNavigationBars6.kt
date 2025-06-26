package com.example.bottombars.screens

import androidx.compose.foundation.checkScrollableContainerConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomNavigationBarSample5(){
    val painters=listOf(
        painterResource(R.drawable.home1),
        painterResource(R.drawable.order),
        painterResource(R.drawable.location),
        painterResource(R.drawable.notification),
        painterResource(R.drawable.profile)
    )
    var selectedIndex by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.padding(bottom = 20.dp, top = 20.dp).fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        color = Color.Transparent,
        tonalElevation = 16.dp,
        shadowElevation = 16.dp
    ) {
        BottomAppBar(
            containerColor = colorResource(R.color.back_color),
            contentPadding = BottomAppBarDefaults.ContentPadding
        ) {
            painters.forEachIndexed { index, painter ->
                val isSelected=selectedIndex==index
                IconButton(
                    modifier = Modifier.padding(start = 14.dp).size(60.dp),
                    onClick = {selectedIndex=index},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if(index==2) colorResource(R.color.circle_color) else Color.Transparent
                    )
                ) {
                    Column {
                        Icon(
                            painter=painter,
                            contentDescription = null,
                            tint = if(isSelected||index==2)  Color.White else colorResource(R.color.icon_color_1),
                            modifier = Modifier.size(24.dp)
                        )
                        if(isSelected&&index!=2){
                        Icon(
                            painter=painterResource(R.drawable.dot),
                            tint = Color.White,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 4.dp).size(16.dp)
                        )
                        }
                    }
                }


            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CustomPreview(){
    BottomNavigationBarSample5()
}