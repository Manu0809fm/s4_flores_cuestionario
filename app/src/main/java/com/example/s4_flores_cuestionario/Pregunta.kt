package com.example.s4_flores_cuestionario

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val respuestaCorrecta: String
)