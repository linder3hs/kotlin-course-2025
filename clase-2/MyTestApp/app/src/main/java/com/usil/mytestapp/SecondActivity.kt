package com.usil.mytestapp

//import androidx.compose.ui.graphics.Color
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.usil.mytestapp.ui.theme.MyTestAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestAppTheme {
                MainComponent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainComponent() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mis componentes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Click sobre esto", Toast.LENGTH_LONG).show()
                },
                ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { paddingValues ->
        ComponentsDemo(paddingValues)
    }
}


@Composable
fun ComponentsDemo(paddingValues: PaddingValues = PaddingValues(0.dp)) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_200))
            .padding(paddingValues)
            .padding(top = 20.dp)
    ) {
        item {
            Button(onClick = {
                val intent = Intent(context, NewActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "New Activity")
            }
            TextComponent()
            ButtonComponent()
            ImageComponent()
            RowComponent()
        }

    }

}

@Composable
fun TextComponent() {
    Text(
        text = "Mi primer componente",
        fontSize = 24.sp,
        color = colorResource(R.color.white),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ButtonComponent() {
    var clickCount by rememberSaveable { mutableStateOf(0) }
    var lastButtonPressed by remember { mutableStateOf("Ninguno") }

    Column {
        Button(
            onClick = {
                clickCount++
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Click en este boton")
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total de clicks: $lastButtonPressed",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Último botón presionado: $clickCount",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun ImageComponent() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",

            )
    }
}

@Composable
fun RowComponent() {
    var selectedDemo by remember { mutableStateOf("Row") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterChip(
            onClick = { selectedDemo = "Row" },
            label = { Text("Row") },
            selected = selectedDemo == "Row"
        )
        FilterChip(
            onClick = { selectedDemo = "Scaffold" },
            label = { Text("Scaffold") },
            selected = selectedDemo == "Scaffold"
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Demostración
    when (selectedDemo) {
        "Row" -> RowExamples()
        "Scaffold" -> ScaffoldExample()
    }
}

@Composable
fun RowExamples() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Ejemplos de Row",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Ejemplo 1: Row básico
        RowExample1()

        // Ejemplo 2: Row con diferentes arrangements
        RowExample2()

        // Ejemplo 3: Row con pesos
        RowExample3()

        // Ejemplo 4: Row con alineación vertical
        RowExample4()
    }
}

@Composable
fun RowExample1() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "1. Row Básico",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { }) { Text("Botón 1") }
                Button(onClick = { }) { Text("Botón 2") }
                Button(onClick = { }) { Text("Botón 3") }
            }

            Text(
                text = "Elementos uno al lado del otro sin espaciado",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun RowExample2() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "2. Row con Arrangement.SpaceBetween",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { }) { Text("Inicio") }
                Button(onClick = { }) { Text("Centro") }
                Button(onClick = { }) { Text("Final") }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "3. Row con Arrangement.SpaceEvenly",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("A", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("B", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("C", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "SpaceBetween: espacio entre elementos\nSpaceEvenly: espacio igual alrededor",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun RowExample3() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "3. Row con Pesos (weight)",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.3f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("1f", fontWeight = FontWeight.Bold)
                    }
                }

                Card(
                    modifier = Modifier.weight(2f),
                    colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = 0.3f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("2f", fontWeight = FontWeight.Bold)
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.3f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("1f", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Text(
                text = "weight() distribuye el espacio proporcionalmente",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun RowExample4() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "4. Row con Alineación Vertical",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "🔴",
                    fontSize = 24.sp
                )

                Column {
                    Text("Texto Principal", fontWeight = FontWeight.Bold)
                    Text("Subtítulo", fontSize = 12.sp, color = Color.Gray)
                }

                Button(onClick = { }) {
                    Text("Acción")
                }
            }

            Text(
                text = "verticalAlignment.CenterVertically centra verticalmente",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

// ===========================================
// SCAFFOLD EXAMPLE
// ===========================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        if (snackbarMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(snackbarMessage)
            snackbarMessage = ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mi Aplicación")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        snackbarMessage = "Menú presionado"
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        snackbarMessage = "Buscar presionado"
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = {
                        snackbarMessage = "Configuración presionada"
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    snackbarMessage = "FAB presionado"
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        ScaffoldContent(
            paddingValues = paddingValues,
            onButtonClick = { buttonName ->
                snackbarMessage = "$buttonName presionado"
            }
        )
    }
}

@Composable
fun ScaffoldContent(
    paddingValues: PaddingValues,
    onButtonClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Contenido del Scaffold",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Estructura del Scaffold:",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "• TopAppBar: Barra superior con título y acciones\n" +
                            "• Content: Área principal (este contenido)\n" +
                            "• FloatingActionButton: Botón flotante\n" +
                            "• SnackbarHost: Para mostrar mensajes\n" +
                            "• BottomBar: Barra inferior (opcional)",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Text(
            text = "Prueba los botones:",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        // Ejemplos de contenido usando Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onButtonClick("Botón 1") }) {
                Text("Botón 1")
            }
            Button(onClick = { onButtonClick("Botón 2") }) {
                Text("Botón 2")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = { onButtonClick("Cancelar") }) {
                Text("Cancelar")
            }
            Button(onClick = { onButtonClick("Confirmar") }) {
                Text("Confirmar")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "💡 Ventajas del Scaffold:",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• Maneja automáticamente el padding para evitar solapamientos\n" +
                            "• Proporciona una estructura consistente\n" +
                            "• Integra componentes Material Design\n" +
                            "• Maneja el SnackbarHost automáticamente",
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

