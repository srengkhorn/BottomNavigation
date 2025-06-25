package com.example.bottombars.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavHost(navController: NavHostController,startDestination: Destination,modifier: Modifier = Modifier){
    NavHost(
        navController=navController,
        startDestination=startDestination.route
    ){
        Destination.entries.forEach { destination ->
            composable (destination.route){
                when(destination){
                    Destination.Chats -> ChatsScreen()
                    Destination.Updates -> UpdatesScreen()
                    Destination.Communities -> CommunitiesScreen()
                    Destination.Calls -> CallsScreen()
            }
          }
       }
    }
}