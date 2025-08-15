/*
* Angel Gabriel Chavez Otzoy
* 24248
* Laboratorio 5
* 
* */


package com.example.laboratorio5

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio5.ui.theme.Laboratorio5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Frontend(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Frontend(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // actualización
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "actualizacion",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Text(
                "Actualización disponible",
                modifier = Modifier.weight(1f)
            )
            Text(
                "Descargar",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                    }
                    context.startActivity(intent)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // fecha
        Text(
            "Lunes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "18 de agosto",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // terminar jornada
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Jornada terminada",
                    Toast.LENGTH_SHORT
                ).show()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(
                "Terminar jornada",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // restaurante
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "La Estancia",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Direcciones",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("geo:0,0?q=14.601667,-90.517389")
                            }
                            context.startActivity(intent)
                        }
                    )
                }
                Text("12 Calle, Cdad. de Guatemala", fontSize = 14.sp, color = Color.Gray)
                Text("6:00AM 10:00PM", fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Angel Gabriel Chavez Otzoy",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE57373)
                        )
                    ) {
                        Text(
                            "Iniciar"
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Detalles",
                        color = Color(0xFFE57373),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                Toast.makeText(
                                    context,
                                    "Steak House\nQQ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio5Theme {
        Frontend()
    }
}