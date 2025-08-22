package com.example.ejercicio21082025

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio21082025.ui.theme.EJERCICIO21082025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EJERCICIO21082025Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Sumas(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sumas(modifier: Modifier = Modifier) {
    var num1 = rememberSaveable { mutableStateOf("") }
    var num2 = rememberSaveable { mutableStateOf("") }
    var suma = rememberSaveable { mutableStateOf("") }
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            Icons.Default.Delete,
            modifier = Modifier,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.primary,
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    suma.value = ""
                           },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                "Sumas"
            )

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                SimpleTextField(num1)
                SimpleTextField(num2)
                // boton de sumar ambos numeros
                Button(
                    onClick = { suma.value = (num1.value.toInt() + num2.value.toInt()).toString() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Sumar")
                }
                // mostrar el resultado de la suma
                if (suma.value.isNotEmpty()) {
                    Text(
                        "Resultado: ${suma.value}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleTextField(num: MutableState<String>) {
    TextField(
        value = num.value,
        onValueChange = { num.value = it },
        label = { Text("Ingresar un numero") },
        placeholder = { Text("Ingresar un numero") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EJERCICIO21082025Theme {
        Sumas()
    }
}