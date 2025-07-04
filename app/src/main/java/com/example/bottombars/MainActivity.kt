package com.example.bottombars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bottombars.shape.CircleBottomNavigation
import com.example.bottombars.animation.DefaultWaterDropBottomNavItems
import com.example.bottombars.shape.GlassBottomNavigation
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box {
                Scaffold(
                    bottomBar = {
//                        AnimatedBottomNavigation1()
//                        AnimatedBottomNavigation2()
//                        AnimatedBottomNavigation3()

                        CircleBottomNavigation()
                    },
                ) { innerPadding ->
//                    SampleAppContentWaterDropBottomNav(innerPadding)
                    Surface(
                        modifier = Modifier
                            .padding(paddingValues = innerPadding)
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        color = Color.White
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun SampleAppContentWaterDropBottomNav(innerPadding: PaddingValues) {
    val hazeState = remember { HazeState() }
    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
            .hazeSource(hazeState)
    ) {
        Text(
            text = "Content for ${DefaultWaterDropBottomNavItems[selectedIndex].title}",
            modifier = Modifier.align(Alignment.Center)
        )

        Box(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
//            WaterDropBottomNavigation(
//                items = DefaultWaterDropBottomNavItems,
//                initialSelectedIndex = selectedIndex,
//                accentColor = Color(0xFF6200EE),
//                onItemSelected = { index, item ->
//                    selectedIndex = index
//                    android.util.Log.d("FluidNavSample", "Selected: ${item.title} at index $index")
//                }
//            )

            GlassBottomNavigation(
                hazeState = hazeState,
                initialSelectedIndex = selectedIndex,
                onItemSelected = { index, item ->
                    selectedIndex = index
                }
            )
        }
    }
}
