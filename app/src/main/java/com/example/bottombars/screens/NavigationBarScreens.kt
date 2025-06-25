package com.example.bottombars.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ChatsScreen(modifier: Modifier= Modifier){
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Chats Screen"
        )
    }
}

@Composable
fun UpdatesScreen(modifier: Modifier= Modifier){
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Updates Screen"
        )
    }
}

@Composable
fun CommunitiesScreen(modifier: Modifier= Modifier){
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Communities Screen"
        )
    }
}

@Composable
fun CallsScreen(modifier: Modifier= Modifier){
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Calls Screen"
        )
    }
}