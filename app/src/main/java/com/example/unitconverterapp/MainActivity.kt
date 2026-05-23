package com.example.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                UnitConverterScreen()
            }
        }
    }
}

@Composable
fun UnitConverterScreen() {

    var inputValue by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf("")
    }

    var selectedConversion by remember {
        mutableStateOf("Meters to Kilometers")
    }

    val conversionOptions = listOf(
        "Meters to Kilometers",
        "Kilograms to Pounds",
        "Celsius to Fahrenheit"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Unit Converter App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
            },
            label = {
                Text("Enter Value")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box {

            Button(
                onClick = {
                    expanded = true
                }
            ) {
                Text(selectedConversion)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                conversionOptions.forEach { option ->

                    DropdownMenuItem(
                        text = {
                            Text(option)
                        },
                        onClick = {
                            selectedConversion = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val input = inputValue.toDoubleOrNull()

                if (input != null) {

                    result = when (selectedConversion) {

                        "Meters to Kilometers" ->
                            "${input / 1000} KM"

                        "Kilograms to Pounds" ->
                            "${input * 2.20462} Pounds"

                        else ->
                            "${(input * 9 / 5) + 32} °F"
                    }

                } else {

                    result = "Please enter valid number"
                }
            }
        ) {

            Text("Convert")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}