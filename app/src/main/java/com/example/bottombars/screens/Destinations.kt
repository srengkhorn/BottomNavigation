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
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label:String,
    val selectedImageVector: ImageVector,
    val unselectedImageVector: ImageVector
){
    Chats(
        route = "chats_route",
        label = "Chats",
        selectedImageVector=Icons.Default.Chat,
        unselectedImageVector=Icons.Outlined.Chat
    ),
    Updates(
    route = "updates_route",
    label = "Updates",
    selectedImageVector=Icons.Default.Update,
    unselectedImageVector=Icons.Outlined.Update
    ),
    Communities(
    route = "communities_route",
    label = "Communities",
    selectedImageVector=Icons.Default.Groups,
    unselectedImageVector=Icons.Outlined.Groups
    ),
    Calls(
    route = "calls_route",
    label = "Calls",
    selectedImageVector=Icons.Default.Call,
    unselectedImageVector=Icons.Outlined.Call
    )

}