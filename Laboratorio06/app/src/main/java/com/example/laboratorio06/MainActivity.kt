/*
* Angel Gabriel Chavez Otzoy
* 24248
* Laboratorio 06
* 22/08/2025
* */


package com.example.laboratorio06

import android.os.Bundle
import android.widget.GridLayout
import android.widget.GridView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio06.ui.theme.Laboratorio06Theme
import kotlin.collections.arrayListOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio06Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Laboratorio06(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Laboratorio06(modifier: Modifier = Modifier) {
    var count = rememberSaveable { mutableStateOf(0) }
    var incrementos = rememberSaveable { mutableStateOf(0) }
    var decrementos = rememberSaveable { mutableStateOf(0) }
    var maximo = rememberSaveable { mutableStateOf(0) }
    var minimo = rememberSaveable { mutableStateOf(0) }
    var cambios = rememberSaveable { mutableStateOf(0) }
    var historial = rememberSaveable { mutableStateOf(listOf<Int>()) }

    Column (
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 30.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Angel Gabriel Chavez Otzoy",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            lineHeight = 40.sp,
            fontSize = 30.sp
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick = { down(
                    count = count,
                    decrementos = decrementos,
                    cambios = cambios,
                    historial = historial,
                    maximo = maximo,
                    minimo = minimo
                ) },
                modifier = Modifier.size(60.dp),
                shape = CircleShape
            ) {
                Text(
                    "-",
                    fontSize = 40.sp
                )
            }

            Text(
                text = count.value.toString(),
                fontSize = 80.sp,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = { up(
                    count = count,
                    incrementos = incrementos,
                    cambios = cambios,
                    historial = historial,
                    maximo = maximo,
                    minimo = minimo
                ) },
                modifier = Modifier.size(60.dp),
                shape = CircleShape
            ) {
                Text(
                    "+",
                    fontSize = 30.sp
                )
            }
        }

        // linea horizontal gris
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        // Incremento
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Total incrementos:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = incrementos.value.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        // decremento
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Total decrementos:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = decrementos.value.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        // valor maximo
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Valor maximo:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = maximo.value.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        // valor minimo
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Valor minimo:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = minimo.value.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        // total de cambios
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Total de cambios:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = cambios.value.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
        if (historial.value.isNotEmpty()) {
            Text(
                text = "Historial:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )

            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Fixed(5)
            ) {
                items(historial.value.size) { item ->
                    // verificar incremento o decremento
                    val colorFondo = if (item > 0) {
                        val actual = historial.value[item]
                        val anterior = historial.value[item - 1]

                        if (actual < anterior) Color.Red
                        else Color.Green
                    } else {
                        Color.Green
                    }

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .background(colorFondo, RoundedCornerShape(4.dp))
                            .height(50.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = historial.value[item].toString(),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                count.value = 0
                incrementos.value = 0
                decrementos.value = 0
                maximo.value = 0
                minimo.value = 0
                cambios.value = 0
                historial.value = listOf()
            }
        ) {
            Text(
                text = "Reiniciar",
                fontSize = 20.sp,
            )
        }
    }
}

fun up (
    count: MutableState<Int>,
    incrementos: MutableState<Int>,
    cambios: MutableState<Int>,
    historial: MutableState<List<Int>>,
    maximo: MutableState<Int>,
    minimo: MutableState<Int>
) {
    count.value++
    incrementos.value++
    cambios.value++
    historial.value = historial.value + listOf(count.value)

    if (count.value > maximo.value) {
        maximo.value = count.value
    }
    if (count.value < minimo.value || minimo.value == 0) {
        minimo.value = count.value
    }
}

fun down (
    count: MutableState<Int>,
    decrementos: MutableState<Int>,
    cambios: MutableState<Int>,
    historial: MutableState<List<Int>>,
    maximo: MutableState<Int>,
    minimo: MutableState<Int>
) {
    count.value--
    decrementos.value++
    cambios.value++
    historial.value = historial.value + listOf(count.value)

    if (count.value > maximo.value) {
        maximo.value = count.value
    }
    if (count.value < minimo.value || minimo.value == 0) {
        minimo.value = count.value
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio06Theme {
        Laboratorio06()
    }
}