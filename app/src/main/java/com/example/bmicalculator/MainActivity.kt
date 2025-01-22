package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Bmi(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Bmi(modifier: Modifier = Modifier) {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }

    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val formatter = DecimalFormat("0.00")
    val bmi = if (weight > 0 && height > 0) formatter.format(weight / (height * height)) else "0.0"

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.bmi_calculator),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(',', '.') },
            label = { Text(stringResource(id = R.string.height_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(',', '.') },
            label = { Text(stringResource(id = R.string.weight_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.bmi_result, bmi),
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BmiPreview() {
    MaterialTheme {
        Bmi()
    }
}
