package com.salesianostriana.notesapi

import com.fasterxml.jackson.annotation.JsonBackReference
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
        var fechaCreacion: LocalDate,
        var fechaUltEdicion: LocalDate,
        @JsonBackReference @ManyToOne
        var autor: User? = null,

        @Id
    @GeneratedValue
    val id: UUID? = null

)