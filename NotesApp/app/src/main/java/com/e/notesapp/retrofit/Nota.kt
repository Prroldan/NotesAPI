package com.e.notesapp.retrofit

data class Nota(
    val contenido: String,
    val fechaCreacion: String,
    val fechaUltEdicion: String,
    val id: String,
    val titulo: String
)