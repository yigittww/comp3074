package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var hours by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }
    var taxRate by remember { mutableStateOf("") }

    var pay by remember { mutableStateOf(0.0) }
    var overtimePay by remember { mutableStateOf(0.0) }
    var totalPay by remember { mutableStateOf(0.0) }
    var tax by remember { mutableStateOf(0.0) }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        OutlinedTextField(value = hours, onValueChange = { hours = it }, label = { Text("Hours Worked") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = rate, onValueChange = { rate = it }, label = { Text("Hourly Rate") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = taxRate, onValueChange = { taxRate = it }, label = { Text("Tax Rate (e.g. 0.13)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val h = hours.toDoubleOrNull() ?: 0.0
            val r = rate.toDoubleOrNull() ?: 0.0
            val t = taxRate.toDoubleOrNull() ?: 0.0

            if (h <= 40) {
                pay = h * r
                overtimePay = 0.0
            } else {
                pay = 40 * r
                overtimePay = (h - 40) * r * 1.5
            }
            totalPay = pay + overtimePay
            tax = pay * t // Formül ödevde tam olarak böyle verilmiş: tax = pay * tax_rate
        }) { Text("Calculate") }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Pay: $pay")
        Text("Overtime Pay: $overtimePay")
        Text("Total Pay: $totalPay")
        Text("Tax: $tax")

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }) { Text("About") }
    }
}