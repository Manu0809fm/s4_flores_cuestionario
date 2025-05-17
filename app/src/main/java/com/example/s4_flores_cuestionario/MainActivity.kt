package com.example.s4_flores_cuestionario
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog



class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private var puntaje = 0
    private val puntosCorrecta = 1
    private val puntosIncorrecta = 1

    private val preguntas = listOf(
        Pregunta(
            texto = "¿Qué componente se utiliza para mostrar una lista desplazable de elementos?",
            opciones = listOf("ScrollView", "RecyclerView", "LinearLayout"),
            respuestaCorrecta = "RecyclerView"
        ),
        Pregunta(
            texto = "¿Cuál es el archivo donde se definen los permisos que necesita una app Android?",
            opciones = listOf("AndroidManifest.xml", "build.gradle", "MainActivity.kt"),
            respuestaCorrecta = "AndroidManifest.xml"
        ),
        Pregunta(
            texto = "¿Cuál es el ciclo de vida correcto de una Activity?",
            opciones = listOf("onCreate → onStart → onResume", "onStart → onCreate → onResume", "onResume → onCreate → onStart"),
            respuestaCorrecta = "onCreate → onStart → onResume"
        ),
        Pregunta(
            texto = "¿Qué función se usa para cambiar de una Activity a otra?",
            opciones = listOf("startService()", "startActivity()", "startContentView()"),
            respuestaCorrecta = "startActivity()"
        ),
        Pregunta(
            texto = "¿Cuál es el lenguaje oficial para desarrollar en Android actualmente?",
            opciones = listOf("Kotlin", "JavaScript", "Python"),
            respuestaCorrecta = "Kotlin"
        ),
        Pregunta(
            texto = "¿Qué archivo se usa para definir el diseño de la interfaz de usuario?",
            opciones = listOf("MainActivity.kt", "styles.xml", "activity_main.xml"),
            respuestaCorrecta = "activity_main.xml"
        ),
        Pregunta(
            texto = "¿Qué clase se utiliza para crear una ventana emergente de diálogo?",
            opciones = listOf("DialogBuilder", "AlertDialog.Builder", "ToastBuilder"),
            respuestaCorrecta = "AlertDialog.Builder"
        ),
        Pregunta(
            texto = "¿Cuál es la función del archivo build.gradle?",
            opciones = listOf("Definir UI", "Gestionar dependencias", "Crear layouts"),
            respuestaCorrecta = "Gestionar dependencias"
        ),
        Pregunta(
            texto = "¿Qué método se usa para inflar un layout en un fragmento?",
            opciones = listOf("setContentView()", "inflateLayout()", "onCreateView()"),
            respuestaCorrecta = "onCreateView()"
        ),
        Pregunta(
            texto = "¿Qué elemento XML define un botón?",
            opciones = listOf("<TextView>", "<Button>", "<ImageButtonView>"),
            respuestaCorrecta = "<Button>"
        ),
        Pregunta(
            texto = "¿Qué es ViewModel en Android?",
            opciones = listOf("Una vista personalizada", "Una clase que almacena y gestiona datos UI", "Una base de datos local"),
            respuestaCorrecta = "Una clase que almacena y gestiona datos UI"
        ),
        Pregunta(
            texto = "¿Qué función tiene el archivo strings.xml?",
            opciones = listOf("Definir permisos", "Definir colores", "Definir textos reutilizables"),
            respuestaCorrecta = "Definir textos reutilizables"
        ),
        Pregunta(
            texto = "¿Qué Layout permite ubicar elementos de forma relativa entre sí?",
            opciones = listOf("LinearLayout", "RelativeLayout", "ConstraintLayout"),
            respuestaCorrecta = "RelativeLayout"
        ),
        Pregunta(
            texto = "¿Qué componente permite observar cambios en datos desde la UI?",
            opciones = listOf("LiveData", "ViewBinding", "RecyclerView"),
            respuestaCorrecta = "LiveData"
        ),
        Pregunta(
            texto = "¿Qué es una Intent en Android?",
            opciones = listOf("Una clase para manejar errores", "Una clase para navegar entre componentes", "Una base de datos local"),
            respuestaCorrecta = "Una clase para navegar entre componentes"
        ),
        Pregunta(
            texto = "¿Para qué se usa Room en Android?",
            opciones = listOf("Rediseñar layouts", "Conectar con bases de datos locales", "Obtener ubicación"),
            respuestaCorrecta = "Conectar con bases de datos locales"
        ),
        Pregunta(
            texto = "¿Qué biblioteca se recomienda para manejar imágenes en Android?",
            opciones = listOf("Glide", "Room", "Retrofit"),
            respuestaCorrecta = "Glide"
        ),
        Pregunta(
            texto = "¿Retrofit se usa principalmente para qué tarea?",
            opciones = listOf("Crear layouts", "Conectarse a APIs REST", "Almacenar archivos"),
            respuestaCorrecta = "Conectarse a APIs REST"
        ),
        Pregunta(
            texto = "¿Qué es un Fragment en Android?",
            opciones = listOf("Un tipo de base de datos", "Un archivo de recursos", "Una parte reutilizable de la interfaz"),
            respuestaCorrecta = "Una parte reutilizable de la interfaz"
        ),
        Pregunta(
            texto = "¿Cuál de estas es una arquitectura recomendada por Google?",
            opciones = listOf("MVC", "MVVM", "MVP"),
            respuestaCorrecta = "MVVM"
        )
    )

    private var preguntaActual = 0
    private val botones by lazy { listOf(option1, option2, option3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)

        mostrarPregunta()

        botones.forEach { boton ->
            boton.setOnClickListener {
                verificarRespuesta(boton.text.toString())
            }
        }
    }

    private fun mostrarPregunta() {
        if (preguntaActual >= preguntas.size) {
            mostrarFinal()
            return
        }

        val pregunta = preguntas[preguntaActual]
        questionTextView.text = pregunta.texto
        val opciones = pregunta.opciones.shuffled() // baraja las opciones

        botones.forEachIndexed { index, boton ->
            boton.text = opciones[index]
        }
    }

    private fun verificarRespuesta(respuestaSeleccionada: String) {
        val pregunta = preguntas[preguntaActual]
        val esCorrecta = respuestaSeleccionada == pregunta.respuestaCorrecta

        if (esCorrecta) {
            puntaje += puntosCorrecta
        } else {
            puntaje -= puntosIncorrecta
            if (puntaje < 0) puntaje = 0
        }

        val mensaje = if (esCorrecta) {
            "¡Correcto! +$puntosCorrecta puntos"
        } else {
            "Incorrecto. La respuesta correcta era: ${pregunta.respuestaCorrecta}\n-$puntosIncorrecta puntos"
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(mensaje + "\nPuntaje actual: $puntaje")
            .setCancelable(false)
            .setPositiveButton("Siguiente") { _, _ ->
                preguntaActual++
                mostrarPregunta()
            }
            .show()
    }


    private fun mostrarFinal() {
        AlertDialog.Builder(this)
            .setTitle("¡Fin del cuestionario!")
            .setMessage("Puntaje final: $puntaje")
            .setPositiveButton("Reiniciar") { _, _ ->
                preguntaActual = 0
                puntaje = 0
                mostrarPregunta()
            }
            .setNegativeButton("Salir", null)
            .setCancelable(false)
            .show()
    }


    private fun showAlert(titulo: String, mensaje: String, onClose: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Siguiente") { _, _ ->
                onClose()
            }
            .setCancelable(false)
            .show()
    }
}
