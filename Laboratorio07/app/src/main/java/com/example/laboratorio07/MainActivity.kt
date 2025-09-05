/*
* Angel Gabriel Chavez Otzoy
* 24248
* Laboratorio 07
* Lazy Layouts
* 04 / 09 / 2025
* */

package com.example.laboratorio07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio07.ui.theme.Laboratorio07Theme
// funcion para obtener las notificaciones fake
import generateFakeNotifications

// tipos de notificaciones
import NotificationType
import Notification
import androidx.compose.foundation.border

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio07Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotificationsView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationsView(modifier: Modifier = Modifier) {
    val notifications = generateFakeNotifications()
    // estado del filtro
    var selectedFilter by remember { mutableStateOf<NotificationType?>(null) }

    val filteredNotifications = if (selectedFilter != null) {
        // filtrado de notificaciones por tipo NotificationType
        notifications.filter { it.type == selectedFilter }
    } else {
        notifications
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Notificaciones",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // titulo
            Text(
                text = "Tipos de notificaciones",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            // filtros
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterButton(
                    text = "Informativas",
                    isSelected = selectedFilter == NotificationType.GENERAL,
                    onClick = {
                        selectedFilter = if (selectedFilter == NotificationType.GENERAL) null
                        else NotificationType.GENERAL
                    }
                )
                FilterButton(
                    text = "Capacitaciones",
                    isSelected = selectedFilter == NotificationType.NEW_MEETING,
                    onClick = {
                        selectedFilter = if (selectedFilter == NotificationType.NEW_MEETING) null
                        else NotificationType.NEW_MEETING
                    }
                )
            }

            // notificaciones
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                // gap
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredNotifications) { notification ->
                    NotificationCard(notification = notification)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(notification: Notification) {
    // colores y iconos de las notificaciones
    val (backgroundColor, iconColor, icon) = when (notification.type) {
        NotificationType.GENERAL -> Triple(
            MaterialTheme.colorScheme.onSecondary,
            MaterialTheme.colorScheme.surfaceTint,
            Icons.Filled.Info
        )
        NotificationType.NEW_MEETING -> Triple(
            MaterialTheme.colorScheme.onSecondary,
            MaterialTheme.colorScheme.inverseSurface,
            Icons.Filled.Home
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // icono
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = iconColor.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            // data
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = notification.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = notification.sendAt,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

// boton de filtro, header
@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                shape = MaterialTheme.shapes.extraSmall
            )
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else Color.Transparent,
                shape = MaterialTheme.shapes.extraSmall
            )
    ) {
        Text(
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
        )
    }
}

// vista clara
@Preview(showBackground = true)
@Composable
fun NotificationsPreview() {
    Laboratorio07Theme {
        NotificationsView()
    }
}

// tema oscuro
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotificationsPreviewDark() {
    Laboratorio07Theme {
        NotificationsView()
    }
}