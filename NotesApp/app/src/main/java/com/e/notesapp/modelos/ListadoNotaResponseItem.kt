package com.e.notesapp.modelos

data class ListadoNotaResponseItem(
    val contenido: String,
    val fechaCreacion: String,
    val fechaUltEdicion: String,
    val id: String,
    val titulo: String
)