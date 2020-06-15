package com.e.notesapp.dto

import com.e.notesapp.modelos.Autor

data class NotaDto (
    val id: String,
    val titulo: String,
    val contenido:String,
    val fechaCreacion: String,
    val fechaUltEdicion : String,
    val autor: Autor?
)
