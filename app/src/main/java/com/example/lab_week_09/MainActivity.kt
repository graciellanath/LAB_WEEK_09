package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                // Gunakan Scaffold agar tidak nempel dengan status bar
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val list = remember { mutableStateListOf("Tanu", "Tina", "Tono") }
                        Home(list)
                    }
                }
            }
        }
    }
}

@Composable
fun Home(items: MutableList<String>) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp), // jarak dari status bar
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Enter a name",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val input = textState.text.trim()
                    if (input.isNotEmpty()) {
                        items.add(input)
                        textState = TextFieldValue("") // reset field
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A1B9A) // Ungu tua
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Submit", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(items) { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    LAB_WEEK_09Theme {
        val sampleList = remember { mutableStateListOf("Tanu", "Tina", "Tono") }
        Home(sampleList)
    }
}