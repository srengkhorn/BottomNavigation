package com.example.bottombars.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottombars.R

// I am going to create a bottom navigation bar for navigation

// The four screens to show
@Composable
fun HomeScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = "Home",
            fontSize = 32.sp,
            color = Color.Cyan
        )
    }
}
@Composable
fun SearchScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = "Search",
            fontSize = 32.sp,
            color = Color.Cyan
        )
    }
}
@Composable
fun NotificationScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = "Notification",
            fontSize = 32.sp,
            color = Color.Cyan
        )
    }
}
@Composable
fun ProfileScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = "Profile",
            fontSize = 32.sp,
            color = Color.Cyan
        )
    }
}
// The destinations
enum class Destination1(val route:String,val imageVector: ImageVector){
    // i named Destination1 because Destination class is already found
    HOME("home_route", Icons.Default.Home),
    SEARCH("search_route", Icons.Default.Search),
    NOTIFICATION("notification_route", Icons.Default.Notifications),
    PROFILE("profile_route", Icons.Default.Person)
}

// The Nav Graph
@Composable
fun AppNavGraph(navController: NavHostController,startDestination: Destination1,modifier: Modifier= Modifier){
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ){
        Destination1.entries.forEach { destination1 ->
            composable(destination1.route) {
                when(destination1.ordinal){
                    0-> HomeScreen()
                    1-> SearchScreen()
                    2-> NotificationScreen()
                    3-> ProfileScreen()
                }
            }
        }
    }
}
// the bottom navigation bar
@Composable
fun AKBottomNavigationBar(navController: NavController){
    var selectedIndex by remember { mutableIntStateOf(Destination1.HOME.ordinal) }
    NavigationBar(
        modifier = Modifier,
        containerColor = colorResource(R.color.ak_green),
    ) {
        NavigationBarItem(
            selected = selectedIndex== Destination1.HOME.ordinal,
            onClick = {
                navController.navigate(Destination1.HOME.route)
                selectedIndex= Destination1.HOME.ordinal
            },
            icon = {
                    Icon(
                        imageVector = Destination1.HOME.imageVector,
                        contentDescription = null,
                        tint = if(selectedIndex== Destination1.HOME.ordinal) Color.White else Color.Black
                    )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selectedIndex== Destination1.SEARCH.ordinal,
            onClick = {
                navController.navigate(Destination1.SEARCH.route)
                selectedIndex= Destination1.SEARCH.ordinal
            },
            icon = {
                    Icon(
                        imageVector = Destination1.SEARCH.imageVector,
                        contentDescription = null,
                        tint = if(selectedIndex== Destination1.SEARCH.ordinal) Color.White else Color.Black
                    )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
        val context= LocalContext.current
        FloatingActionButton(
            onClick = {
                Toast.makeText(context,"FAB clicked!", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
        NavigationBarItem(
            selected = selectedIndex== Destination1.NOTIFICATION.ordinal,
            onClick = {
                navController.navigate(Destination1.NOTIFICATION.route)
                selectedIndex= Destination1.NOTIFICATION.ordinal
            },
            icon = {
                    Icon(
                        imageVector = Destination1.NOTIFICATION.imageVector,
                        contentDescription = null,
                        tint = if(selectedIndex== Destination1.NOTIFICATION.ordinal) Color.White else Color.Black
                    )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selectedIndex== Destination1.PROFILE.ordinal,
            onClick = {
                navController.navigate(Destination1.PROFILE.route)
                selectedIndex= Destination1.PROFILE.ordinal
            },
            icon = {
                    Icon(
                        imageVector = Destination1.PROFILE.imageVector,
                        contentDescription = null,
                        tint = if(selectedIndex== Destination1.PROFILE.ordinal) Color.White else Color.Black
                    )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
    }
}
@Composable
fun AKBottomAppBar(navController: NavController){
    var selectedIndex by remember { mutableIntStateOf(Destination1.HOME.ordinal) }
    BottomAppBar(
        containerColor = colorResource(R.color.ak_green),
    ) {
        IconButton(
            onClick = {
                navController.navigate(Destination1.HOME.route)
                selectedIndex= Destination1.HOME.ordinal
            }
        ) {
            Icon(
                imageVector = Destination1.HOME.imageVector,
                contentDescription = null,
                tint = if(selectedIndex== Destination1.HOME.ordinal) Color.White else Color.Black
            )
        }
        Spacer(Modifier.width(25.dp))
        IconButton(
            onClick = {
                navController.navigate(Destination1.SEARCH.route)
                selectedIndex= Destination1.SEARCH.ordinal
            }
        ) {
            Icon(
                imageVector = Destination1.SEARCH.imageVector,
                contentDescription = null,
                tint = if(selectedIndex== Destination1.SEARCH.ordinal) Color.White else Color.Black
            )
        }
        Spacer(Modifier.width(25.dp))

        val context=LocalContext.current
        FloatingActionButton(
            onClick = {
                Toast.makeText(context,"FAB clicked!", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
        Spacer(Modifier.width(25.dp))

        IconButton(
            onClick = {
                navController.navigate(Destination1.NOTIFICATION.route)
                selectedIndex= Destination1.NOTIFICATION.ordinal
            }
        ) {
            Icon(
                imageVector = Destination1.NOTIFICATION.imageVector,
                contentDescription = null,
                tint = if(selectedIndex== Destination1.NOTIFICATION.ordinal) Color.White else Color.Black
            )
        }
        Spacer(Modifier.width(25.dp))

        IconButton(
            onClick = {
                navController.navigate(Destination1.PROFILE.route)
                selectedIndex= Destination1.PROFILE.ordinal
            }
        ) {
            Icon(
                imageVector = Destination1.PROFILE.imageVector,
                contentDescription = null,
                tint = if(selectedIndex== Destination1.PROFILE.ordinal) Color.White else Color.Black
            )
        }
    }
}


