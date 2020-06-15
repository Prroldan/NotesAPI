package com.e.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.notesapp.dto.CreateNotaDto
import com.e.notesapp.dto.NotaDto
import com.e.notesapp.modelos.ListadoNotaResponse
import com.e.notesapp.modelos.ListadoNotaResponseItem
import com.e.notesapp.modelos.Nota
import com.e.notesapp.retrofit.NotesApiRepository


class NotesViewModel():ViewModel() {

    private val  notesApiRepository = NotesApiRepository()
    private var allNotes :LiveData<List<ListadoNotaResponseItem>> = notesApiRepository.getAllnotes()
    var idSeleccionado: MutableLiveData<String> = MutableLiveData()
    lateinit var notaDto: LiveData<NotaDto>
    //lateinit var payEventDto: LiveData<PayEventDto>
    //lateinit var payEditEventDto: LiveData<PayEventDto>

    fun getAllNotes():LiveData<List<ListadoNotaResponseItem>>{
        return allNotes
    }

    fun setIdNotaSeleccionada(idNotaSeleccionada: String) {

        idSeleccionado.value = idNotaSeleccionada
    }

    fun getIdNotaSeleccionada(): MutableLiveData<String> {
        return idSeleccionado
    }

    fun createNota(createNotaDto: CreateNotaDto): LiveData<NotaDto>{
        notaDto = notesApiRepository.createNewNote(createNotaDto)
        return notaDto

    }

    fun deleteNote(idNota:String){
        notesApiRepository.deleteNote(idNota)
    }

    fun editNode(idNota: String,createNotaDto: CreateNotaDto):LiveData<NotaDto>{
        notaDto = notesApiRepository.editNote(idNota,createNotaDto)
        return notaDto
    }

}