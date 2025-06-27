package com.example.bottombars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.bottombars.screens.AKBottomAppBar
import com.example.bottombars.screens.AnimatedBar1
import com.example.bottombars.screens.AnimatedBar2
import com.example.bottombars.screens.AnimatedBar3
import com.example.bottombars.screens.AppNavGraph
import com.example.bottombars.screens.BottomNavigationBarSample1
import com.example.bottombars.screens.BottomNavigationBarSample2
import com.example.bottombars.screens.BottomNavigationBarSample3
import com.example.bottombars.screens.BottomNavigationBarSample4
import com.example.bottombars.screens.BottomNavigationBarSample5
import com.example.bottombars.screens.Destination1


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box {
            Scaffold(
                bottomBar = {
                    AnimatedBar2()
                },


            ) { innerPadding->
                Surface (
                    modifier = Modifier
                        .padding(paddingValues = innerPadding)
                        .fillMaxSize(),
                    color = Color.White


                ){

                }
            }
        }
            }
    }
}

