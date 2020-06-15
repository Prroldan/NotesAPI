package com.e.notesapp.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.e.notesapp.modelos.Autor
import java.time.LocalDate

data class CreateNotaDto constructor(

    val titulo: String,
    val contenido:String,
    val fechaCreacion: String
)