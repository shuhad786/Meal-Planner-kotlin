package com.example.biteplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
    var errorMessage by remember { mutableStateOf("") }
    val isFocused by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Welcome to Bite Plan!",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = userInput,
                    onValueChange = {
                        userInput = it
                        errorMessage = "" // Clear error message on input change
                    },
                    label = {
                        Text(
                            "Enter the Time of day (breakfast, brunch, lunch, afternoon, dinner, nightcap)",
                            color = if (isFocused) Color.White else Color.Gray // Change colour of the label to be a light grey
                        ) },
                    isError = errorMessage.isNotEmpty(),
                    colors = TextFieldDefaults.colors( // edit the colours of the textbox
                        focusedContainerColor = Color(0xffbfe8ee), // change colour to black when hovering or clicked
                        unfocusedContainerColor = Color.White, // changed to white when it is not clicked or hovered over
                        focusedIndicatorColor = Color.Blue, // changes to be when container is Clicked
                        unfocusedIndicatorColor = Color.Black // stays black when text box is inactive
                    ),
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp) // made the text box with rounded edges when it is active
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .width(280.dp)
                ) {
                    Button(
                        onClick = {
                            outputText = when (userInput.text.trim().lowercase()) {
                                "breakfast" -> "Suggested: Pancakes"
                                "brunch" -> "Suggested: Avocado Toast"
                                "lunch" -> "Suggested: Caesar Salad"
                                "afternoon" -> "Suggested: Smoothie"
                                "dinner" -> "Suggested: Grilled Salmon"
                                "nightcap" -> "Suggested: Whiskey Sour"
                                else -> {
                                    errorMessage = "Invalid Time of day, Please enter a valid Time of day."
                                    ""
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Set the background color to black
                            contentColor = Color.White // Set the text color to white
                        )
                    ) {
                        Text("Show Me My Meal!") // Button text
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = {
                        // Reset both user input and output text
                        userInput = TextFieldValue("")
                        outputText = ""
                        errorMessage = "" // Clear error message on reset
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black // Set the background color to black
                        )
                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_restart), // Removed text and changed it to a vector icon in drawable
                            contentDescription = "Reset",
                            modifier = Modifier
                                .size(24.dp) // The size of the vector image
                                .wrapContentWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(text = outputText,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold, // made the output text bigger
                        fontSize = 30.sp // changed the font size to be larger
                    )
                )
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