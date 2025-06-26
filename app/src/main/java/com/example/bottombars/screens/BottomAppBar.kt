package com.example.bottombars.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomAppBarComposable1(scrollBehavior: BottomAppBarScrollBehavior){
    BottomAppBar(actions = {
        IconButton(
            onClick = {}
        ) {
            Icon(Icons.Default.Home, contentDescription = null)
        }

        IconButton(
            onClick = {}
        ) {
            Icon(Icons.Default.Settings, contentDescription = null)
        }
    
        IconButton(
            onClick = {}
        ) {
            Icon(Icons.Default.AddLocation, contentDescription = null)
        }
},
    floatingActionButton = {
        FloatingActionButton(
            onClick = {}
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    },
        scrollBehavior = scrollBehavior,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        tonalElevation = 16.dp,
        contentPadding = BottomAppBarDefaults.FlexibleContentPadding,
        windowInsets = BottomAppBarDefaults.windowInsets
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBarComposable2(scrollBehavior: BottomAppBarScrollBehavior){
    BottomAppBar (
        modifier = Modifier,
        containerColor = Color.Yellow,
        contentColor = Color.Blue,
        tonalElevation = 16.dp,
        contentPadding = BottomAppBarDefaults.FlexibleContentPadding,
        windowInsets = BottomAppBarDefaults.windowInsets,
        scrollBehavior = scrollBehavior
    ){
        FilledIconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null
            )
        }
        FilledIconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null
            )
        }
        FilledIconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null
            )
        }
        Spacer(Modifier.padding(start = 120.dp))
        var isChecked by remember { mutableStateOf(false) }
        ToggleFloatingActionButton(
            checked = isChecked,
            onCheckedChange = {isChecked=it},
            containerColor = ToggleFloatingActionButtonDefaults.containerColor(Color.Red,Color.Magenta),
            containerCornerRadius = ToggleFloatingActionButtonDefaults.containerCornerRadius(12.dp,22.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewSample2(){
    val scrollBehavior= BottomAppBarDefaults.exitAlwaysScrollBehavior()
    val navController= rememberNavController()
    AKBottomAppBar(navController)
}