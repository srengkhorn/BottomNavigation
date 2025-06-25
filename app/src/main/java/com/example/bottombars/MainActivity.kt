package com.example.bottombars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bottombars.screens.BottomAppBarComposable1
import com.example.bottombars.screens.BottomAppBarComposable2
import com.example.bottombars.screens.NavigationBarSample

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollBehavior= BottomAppBarDefaults.exitAlwaysScrollBehavior()
            Scaffold(
                bottomBar = {
                    NavigationBarSample()
                }
            ) { innerPadding->
                LazyColumn (modifier = Modifier.padding(innerPadding).nestedScroll(scrollBehavior.nestedScrollConnection)){
                    items(50) {
                        Text(
                            text = "item no $it",
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

