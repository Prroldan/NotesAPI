package com.e.notesapp.modelos

data class Nota(
    val contenido: String,
    val fechaCreacion: String,
    val fechaUltEdicion: String,
    val id: String,
    val titulo: String
)