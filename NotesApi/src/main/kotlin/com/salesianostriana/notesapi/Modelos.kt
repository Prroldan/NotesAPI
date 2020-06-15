package com.salesianostriana.notesapi

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Nota(

        var titulo: String,
        var contenido: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var fechaCreacion: LocalDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var fechaUltEdicion: LocalDate,
        @JsonBackReference @ManyToOne
        var autor: User? = null,

        @Id
    @GeneratedValue
    val id: UUID? = null

)