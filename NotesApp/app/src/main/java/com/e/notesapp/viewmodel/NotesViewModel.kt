package com.e.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.notesapp.retrofit.Nota
import com.e.notesapp.retrofit.NotesApiRepository
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    notesApiRepository: NotesApiRepository
):ViewModel() {

    private var allNotes :LiveData<List<Nota>> = notesApiRepository.getallPokemons()

    fun getAllNotes():LiveData<List<Nota>>{
        return allNotes
    }

}