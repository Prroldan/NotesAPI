package com.e.notesapp.modelos

data class DetalleNotaResponse(
    val autor: Autor,
    val contenido: String,
    val fechaCreacion: String,
    val fechaUltEdicion: String,
    val id: String,
    val titulo: String
)