package com.usil.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.usil.mytestapp.ui.theme.MyTestAppTheme

data class TodoItem(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)

fun getSampleTodos(): List<TodoItem> {
    return listOf(
        TodoItem(1, "Aprender Jetpack Compose", false),
        TodoItem(2, "Hacer ejercicio", true),
        TodoItem(3, "Comprar comida", false),
        TodoItem(4, "Leer un libro", true),
        TodoItem(5, "Llamar a mamá", false),
        TodoItem(6, "Estudiar Kotlin", false)
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestAppTheme {
                TodoListScreen()
            }
        }
    }
}


@Composable
fun TodoListScreen() {
    val todos = getSampleTodos()
    val completedCount = todos.count { it.isCompleted }
    val totalCount = todos.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBB3939))
    ) {
        // Header con información
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF6200EE),
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Mis Tareas",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$completedCount de $totalCount completadas",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // Lista de tareas con scroll
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todos) { todo ->
                TodoItemCard(todo = todo)
            }
        }
    }
}


@Composable
fun TodoItemCard(todo: TodoItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (todo.isCompleted)
                Color(0xFFF0F8F0) else Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox moderno
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (todo.isCompleted)
                            Color(0xFF4CAF50) else Color(0xFFE0E0E0),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (todo.isCompleted) {
                    Text(
                        text = "✓",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Contenido de la tarea
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todo.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = if (todo.isCompleted)
                        TextDecoration.LineThrough else null,
                    color = if (todo.isCompleted)
                        Color(0xFF757575) else Color(0xFF212121)
                )
            }

            // Ícono de estado
            Text(
                text = if (todo.isCompleted) "🎉" else "⏰",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    MyTestAppTheme {
        TodoListScreen()
    }
}