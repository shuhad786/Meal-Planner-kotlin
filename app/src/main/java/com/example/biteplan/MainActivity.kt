package com.example.biteplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.biteplan.ui.theme.BitePlanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BitePlanTheme {
                MealPlannerScreen()
            }
        }
    }
}

@Composable
fun MealPlannerScreen() {
    var userInput by remember { mutableStateOf(TextFieldValue("")) }
    var outputText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Meal Planner", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = { Text("Enter your meal") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(onClick = {
                        outputText = when (userInput.text.lowercase()) {
                            "breakfast" -> "You planned: Pancakes"
                            "brunch" -> "You planned: Avocado Toast"
                            "lunch" -> "You planned: Caesar Salad"
                            "afternoon" -> "You planned: Smoothie"
                            "dinner" -> "You planned: Grilled Salmon"
                            "nightcap" -> "You planned: Whiskey Sour"
                            else -> "Please enter a valid meal type."
                        }
                    }) {
                        Text("Submit")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = {
                        // Reset both user input and output text
                        userInput = TextFieldValue("")
                        outputText = ""
                    }) {
                        Text("Reset")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = outputText, style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MealPlannerPreview() {
    BitePlanTheme {
        MealPlannerScreen()
    }
}