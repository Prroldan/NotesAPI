package com.e.notesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.notesapp.modelos.DetalleNotaResponse
import com.e.notesapp.retrofit.NotesApiRepository

class DetalleNoteViewModel: ViewModel() {
    lateinit var nota: MutableLiveData<DetalleNotaResponse>
    var notesApiRepository: NotesApiRepository = NotesApiRepository()

    fun getNote(idEvent: String): MutableLiveData<DetalleNotaResponse> {
        nota = notesApiRepository.getOneNote(idEvent)
        return nota

    }
}