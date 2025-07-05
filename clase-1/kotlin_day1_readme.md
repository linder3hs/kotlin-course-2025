# Día 1: Introducción a Kotlin y Configuración del Entorno
## Código Práctico de la Clase

### 1. Primeros Pasos - Variables y Tipos

```kotlin
fun main() {
    // Variables mutables vs inmutables
    var nombre = "Juan"  // Mutable
    val edad = 25        // Inmutable
    
    // Tipos explícitos
    val precio: Double = 19.99
    val activo: Boolean = true
    
    // Inferencia de tipos
    val ciudad = "México"  // String inferido
    val contador = 0       // Int inferido
    
    println("Hola $nombre, tienes $edad años")
    println("Precio: $precio, Activo: $activo")
}
```

### 2. Funciones Básicas

```kotlin
// Función simple
fun saludar(nombre: String): String {
    return "Hola $nombre"
}

// Función con valor por defecto
fun calcularDescuento(precio: Double, descuento: Double = 0.1): Double {
    return precio * (1 - descuento)
}

// Función de expresión
fun sumar(a: Int, b: Int) = a + b

// Función que no retorna nada
fun mostrarInfo(producto: String, precio: Double) {
    println("Producto: $producto - Precio: $precio")
}

fun main() {
    println(saludar("Ana"))
    println("Precio con descuento: ${calcularDescuento(100.0)}")
    println("Suma: ${sumar(5, 3)}")
    mostrarInfo("Laptop", 15000.0)
}
```

### 3. Control de Flujo - if/else

```kotlin
fun evaluarNota(calificacion: Int): String {
    return if (calificacion >= 90) {
        "Excelente"
    } else if (calificacion >= 70) {
        "Bueno"
    } else if (calificacion >= 60) {
        "Suficiente"
    } else {
        "Insuficiente"
    }
}

fun calcularPrecioConDescuento(precio: Double, esEstudiante: Boolean): Double {
    val descuento = if (esEstudiante) 0.2 else 0.0
    return precio * (1 - descuento)
}

fun main() {
    println(evaluarNota(85))
    println("Precio estudiante: ${calcularPrecioConDescuento(100.0, true)}")
    println("Precio regular: ${calcularPrecioConDescuento(100.0, false)}")
}
```

### 4. When Expression (Switch Mejorado)

```kotlin
fun obtenerDiaSemana(dia: Int): String {
    return when (dia) {
        1 -> "Lunes"
        2 -> "Martes"
        3 -> "Miércoles"
        4 -> "Jueves"
        5 -> "Viernes"
        6, 7 -> "Fin de semana"
        else -> "Día inválido"
    }
}

fun categorizarEdad(edad: Int): String {
    return when {
        edad < 13 -> "Niño"
        edad < 18 -> "Adolescente"
        edad < 65 -> "Adulto"
        else -> "Adulto mayor"
    }
}

fun procesarCalificacion(letra: Char): String {
    return when (letra) {
        'A' -> "Excelente (90-100)"
        'B' -> "Bueno (80-89)"
        'C' -> "Regular (70-79)"
        'D' -> "Suficiente (60-69)"
        'F' -> "Reprobado (0-59)"
        else -> "Calificación inválida"
    }
}

fun main() {
    println(obtenerDiaSemana(3))
    println(categorizarEdad(25))
    println(procesarCalificacion('B'))
}
```

### 5. Loops y Rangos

```kotlin
fun ejemplosLoops() {
    // For con rango
    println("Números del 1 al 5:")
    for (i in 1..5) {
        println(i)
    }
    
    // For con until
    println("\nNúmeros del 0 al 4:")
    for (i in 0 until 5) {
        println(i)
    }
    
    // For con step
    println("\nNúmeros pares del 0 al 10:")
    for (i in 0..10 step 2) {
        println(i)
    }
    
    // While
    println("\nContador while:")
    var contador = 0
    while (contador < 3) {
        println("Contador: $contador")
        contador++
    }
    
    // Do-while
    println("\nDo-while:")
    var numero = 0
    do {
        println("Número: $numero")
        numero++
    } while (numero < 3)
}

fun main() {
    ejemplosLoops()
}
```

### 6. Null Safety - Concepto Clave

```kotlin
fun ejemplosNullSafety() {
    // Variable que puede ser null
    var nombre: String? = "Pedro"
    var apellido: String? = null
    
    // Safe call operator (?.)
    println("Longitud del nombre: ${nombre?.length}")
    println("Longitud del apellido: ${apellido?.length}")
    
    // Elvis operator (?:)
    val nombreCompleto = nombre ?: "Sin nombre"
    println("Nombre: $nombreCompleto")
    
    // Let para ejecutar código solo si no es null
    nombre?.let {
        println("El nombre '$it' tiene ${it.length} caracteres")
    }
    
    // Verificación de null
    if (apellido != null) {
        println("Apellido: $apellido")
    } else {
        println("No hay apellido")
    }
}

fun obtenerLongitudSegura(texto: String?): Int {
    return texto?.length ?: 0
}

fun main() {
    ejemplosNullSafety()
    println("Longitud: ${obtenerLongitudSegura("Hola")}")
    println("Longitud: ${obtenerLongitudSegura(null)}")
}
```

### 7. Calculadora Simple

```kotlin
class CalculadoraSimple {
    
    fun sumar(a: Double, b: Double): Double = a + b
    fun restar(a: Double, b: Double): Double = a - b
    fun multiplicar(a: Double, b: Double): Double = a * b
    
    fun dividir(a: Double, b: Double): Double? {
        return if (b != 0.0) {
            a / b
        } else {
            null
        }
    }
    
    fun calcular(operacion: String, a: Double, b: Double): String {
        val resultado = when (operacion.lowercase()) {
            "suma", "+" -> sumar(a, b)
            "resta", "-" -> restar(a, b)
            "multiplicacion", "*" -> multiplicar(a, b)
            "division", "/" -> dividir(a, b)
            else -> null
        }
        
        return if (resultado != null) {
            "Resultado: $resultado"
        } else {
            "Operación inválida o división por cero"
        }
    }
}

fun main() {
    val calc = CalculadoraSimple()
    
    println(calc.calcular("+", 10.0, 5.0))
    println(calc.calcular("-", 10.0, 5.0))
    println(calc.calcular("*", 10.0, 5.0))
    println(calc.calcular("/", 10.0, 5.0))
    println(calc.calcular("/", 10.0, 0.0))  // Error controlado
}
```

### 9. Arreglos y Listas

```kotlin
fun ejemplosArreglos() {
    // Array (tamaño fijo)
    val numeros = arrayOf(1, 2, 3, 4, 5)
    val nombres = arrayOf("Ana", "Carlos", "Luis")
    
    // Acceder a elementos
    println("Primer número: ${numeros[0]}")
    println("Último nombre: ${nombres[nombres.size - 1]}")
    
    // Recorrer array
    println("Todos los números:")
    for (numero in numeros) {
        println(numero)
    }
    
    // Con índices
    println("Números con índices:")
    for (i in numeros.indices) {
        println("Posición $i: ${numeros[i]}")
    }
}

fun ejemplosListas() {
    // Lista inmutable
    val frutas = listOf("Manzana", "Banana", "Naranja")
    val calificaciones = listOf(85, 92, 78, 95)
    
    // Lista mutable
    val tareas = mutableListOf("Estudiar", "Programar")
    tareas.add("Ejercitarse")
    tareas.add("Leer")
    
    println("Frutas disponibles:")
    frutas.forEach { fruta ->
        println("- $fruta")
    }
    
    println("Mis tareas:")
    tareas.forEachIndexed { index, tarea ->
        println("${index + 1}. $tarea")
    }
    
    // Operaciones con listas
    val calificacionAlta = calificaciones.filter { it > 90 }
    val promedio = calificaciones.average()
    
    println("Calificaciones altas: $calificacionAlta")
    println("Promedio: $promedio")
}

fun gestionarInventario() {
    // Ejemplo práctico: Sistema de inventario
    val productos = mutableListOf<String>()
    val precios = mutableListOf<Double>()
    val stock = mutableListOf<Int>()
    
    // Agregar productos
    fun agregarProducto(nombre: String, precio: Double, cantidad: Int) {
        productos.add(nombre)
        precios.add(precio)
        stock.add(cantidad)
        println("Producto '$nombre' agregado al inventario")
    }
    
    // Mostrar inventario
    fun mostrarInventario() {
        println("\n=== INVENTARIO ===")
        for (i in productos.indices) {
            println("${i + 1}. ${productos[i]} - ${precios[i]} - Stock: ${stock[i]}")
        }
    }
    
    // Buscar producto
    fun buscarProducto(nombre: String): Int? {
        return productos.indexOfFirst { it.equals(nombre, ignoreCase = true) }
            .takeIf { it != -1 }
    }
    
    // Usar las funciones
    agregarProducto("Laptop", 15000.0, 5)
    agregarProducto("Mouse", 250.0, 20)
    agregarProducto("Teclado", 800.0, 15)
    
    mostrarInventario()
    
    val indice = buscarProducto("laptop")
    if (indice != null) {
        println("Producto encontrado en posición: ${indice + 1}")
    }
}

fun main() {
    ejemplosArreglos()
    println("\n" + "=".repeat(30) + "\n")
    ejemplosListas()
    println("\n" + "=".repeat(30) + "\n")
    gestionarInventario()
}
```
        versionCode 1
        versionName "1.0"
    }
    
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation 'androidx.core:core-ktx:1.10.1'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
}
```

### 9. Arreglos y Listas

```kotlin
fun ejemplosArreglos() {
    // Array (tamaño fijo)
    val numeros = arrayOf(1, 2, 3, 4, 5)
    val nombres = arrayOf("Ana", "Carlos", "Luis")
    
    // Acceder a elementos
    println("Primer número: ${numeros[0]}")
    println("Último nombre: ${nombres[nombres.size - 1]}")
    
    // Recorrer array
    println("Todos los números:")
    for (numero in numeros) {
        println(numero)
    }
    
    // Con índices
    println("Números con índices:")
    for (i in numeros.indices) {
        println("Posición $i: ${numeros[i]}")
    }
}

fun ejemplosListas() {
    // Lista inmutable
    val frutas = listOf("Manzana", "Banana", "Naranja")
    val calificaciones = listOf(85, 92, 78, 95)
    
    // Lista mutable
    val tareas = mutableListOf("Estudiar", "Programar")
    tareas.add("Ejercitarse")
    tareas.add("Leer")
    
    println("Frutas disponibles:")
    frutas.forEach { fruta ->
        println("- $fruta")
    }
    
    println("Mis tareas:")
    tareas.forEachIndexed { index, tarea ->
        println("${index + 1}. $tarea")
    }
    
    // Operaciones con listas
    val calificacionAlta = calificaciones.filter { it > 90 }
    val promedio = calificaciones.average()
    
    println("Calificaciones altas: $calificacionAlta")
    println("Promedio: $promedio")
}

fun gestionarInventario() {
    // Ejemplo práctico: Sistema de inventario
    val productos = mutableListOf<String>()
    val precios = mutableListOf<Double>()
    val stock = mutableListOf<Int>()
    
    // Agregar productos
    fun agregarProducto(nombre: String, precio: Double, cantidad: Int) {
        productos.add(nombre)
        precios.add(precio)
        stock.add(cantidad)
        println("Producto '$nombre' agregado al inventario")
    }
    
    // Mostrar inventario
    fun mostrarInventario() {
        println("\n=== INVENTARIO ===")
        for (i in productos.indices) {
            println("${i + 1}. ${productos[i]} - ${precios[i]} - Stock: ${stock[i]}")
        }
    }
    
    // Buscar producto
    fun buscarProducto(nombre: String): Int? {
        return productos.indexOfFirst { it.equals(nombre, ignoreCase = true) }
            .takeIf { it != -1 }
    }
    
    // Usar las funciones
    agregarProducto("Laptop", 15000.0, 5)
    agregarProducto("Mouse", 250.0, 20)
    agregarProducto("Teclado", 800.0, 15)
    
    mostrarInventario()
    
    val indice = buscarProducto("laptop")
    if (indice != null) {
        println("Producto encontrado en posición: ${indice + 1}")
    }
}

fun main() {
    ejemplosArreglos()
    println("\n" + "=".repeat(30) + "\n")
    ejemplosListas()
    println("\n" + "=".repeat(30) + "\n")
    gestionarInventario()
}
```

## Ejercicios Para Practicar

### Ejercicio 1: Variables y Funciones
```kotlin
// TODO: Crear una función que calcule el IMC
fun calcularIMC(peso: Double, altura: Double): Double {
    // Tu código aquí
}

// TODO: Crear una función que categorice el IMC
fun categorizarIMC(imc: Double): String {
    // Tu código aquí
}
```

### Ejercicio 2: Control de Flujo
```kotlin
// TODO: Crear un sistema de calificaciones
fun sistemaCalificaciones(puntos: Int): String {
    // Usar when para categorizar puntos en letras A, B, C, D, F
}
```

### Ejercicio 3: Null Safety
```kotlin
// TODO: Crear una función que maneje nombres completos
fun crearNombreCompleto(nombre: String?, apellido: String?): String {
    // Usar operadores de null safety
}
```

## Comandos Útiles de Android Studio

```bash
# Crear nuevo proyecto
File > New > New Project > Empty Activity

# Configurar Kotlin
Tools > Kotlin > Configure Kotlin in Project

# Ejecutar aplicación
Run > Run 'app' (Shift + F10)

# Limpiar proyecto
Build > Clean Project
```

## Próxima Clase
En el Día 2 veremos:
- Layouts en Android
- Views y ViewGroups
- Manejo de eventos
- Navegación entre Activities