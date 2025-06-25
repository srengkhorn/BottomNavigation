package com.example.bottombars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bottombars.screens.AppNavHost
import com.example.bottombars.screens.Destination
import com.example.bottombars.screens.NavigationSample

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollBehavior= BottomAppBarDefaults.exitAlwaysScrollBehavior()
            val navController= rememberNavController()
            Scaffold(
                bottomBar = {
                    NavigationSample(navController=navController)
                }
            ) { innerPadding->
                AppNavHost(navController, Destination.Chats, modifier = Modifier.padding(innerPadding))
//                LazyColumn (modifier = Modifier.padding(innerPadding).nestedScroll(scrollBehavior.nestedScrollConnection)){
//                    items(50) {
//                        Text(
//                            text = "item no $it",
//                            modifier = Modifier.fillMaxWidth().padding(16.dp),
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                }
            }
        }
    }
}

