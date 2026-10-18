package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AboutActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("About") },
                        navigationIcon = {
                            TextButton(onClick = { finish() }) {
                                Text("Back")
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                    Text("Name: Yiğit Alkoç", style = MaterialTheme.typography.headlineMedium)
                    Text("Student ID: 101558073", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}