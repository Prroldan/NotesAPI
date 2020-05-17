package com.salesianostriana.notesapi

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*

data class NotaDto(
        val id: UUID?,
        val titulo: String,
        val contenido:String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fechaCreacion:  LocalDate = LocalDate.now(),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fechaUltEdicion : LocalDate = LocalDate.now(),
        val autor: User?
)

fun Nota.toNotaDto() = NotaDto(id,titulo, contenido, fechaCreacion, fechaUltEdicion, autor)
fun NotaDto.toNota() = Nota(titulo, contenido, fechaCreacion, fechaUltEdicion, autor, id)

data class NewNotaDto(
        val titulo: String,
        val contenido:String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fechaCreacion: LocalDate = LocalDate.now(),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fechaUltEdicion: LocalDate = LocalDate.now(),
        val autor : User?
)

fun NewNotaDto.toNota() = Nota(titulo,contenido,fechaCreacion,fechaUltEdicion,autor)