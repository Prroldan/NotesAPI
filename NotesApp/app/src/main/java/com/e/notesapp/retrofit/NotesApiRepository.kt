package com.e.notesapp.retrofit

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.e.notesapp.common.MyApp
import com.e.notesapp.dto.CreateNotaDto
import com.e.notesapp.dto.NotaDto
import com.e.notesapp.modelos.DetalleNotaResponse
import com.e.notesapp.modelos.ListadoNotaResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotesApiRepository(){

    var  notesAppService: NotesAppService
    var notesAppClient:NotesApiClient = NotesApiClient()
    var allNotes: MutableLiveData<List<ListadoNotaResponseItem>>
    var oneNote: MutableLiveData<DetalleNotaResponse> = MutableLiveData<DetalleNotaResponse>()
    var responseCreateNote: MutableLiveData<NotaDto> = MutableLiveData()




    init {
        notesAppService = notesAppClient.getNotesAppService()
        allNotes = MutableLiveData<List<ListadoNotaResponseItem>>()
    }

    fun getAllnotes(): MutableLiveData<List<ListadoNotaResponseItem>> {
        val call: Call<List<ListadoNotaResponseItem>> = notesAppService.getAllNotas()
        call.enqueue(object: Callback<List<ListadoNotaResponseItem>> {
            override fun onResponse(
                call: Call<List<ListadoNotaResponseItem>>,
                response: Response<List<ListadoNotaResponseItem>>
            ) {
                if(response.isSuccessful) {
                    allNotes.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ListadoNotaResponseItem>>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error pay", Toast.LENGTH_LONG).show()
            }
        })

        return allNotes
    }

    fun getOneNote(idNota:String): MutableLiveData<DetalleNotaResponse> {
        val call: Call<DetalleNotaResponse> = notesAppService.getNotasById(idNota)
        call.enqueue(object: Callback<DetalleNotaResponse> {
            override fun onResponse(
                call: Call<DetalleNotaResponse>,
                response: Response<DetalleNotaResponse>
            ) {
                if(response.isSuccessful) {
                    oneNote.value = response.body()
                }
            }

            override fun onFailure(call: Call<DetalleNotaResponse>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error pay", Toast.LENGTH_LONG).show()
            }
        })

        return oneNote
    }

    fun createNewNote(createNotaDto: CreateNotaDto): MutableLiveData<NotaDto> {
        val call: Call<NotaDto> = notesAppService.createNota(createNotaDto)
        call.enqueue(object: Callback<NotaDto> {
            override fun onResponse(
                call: Call<NotaDto>,
                response: Response<NotaDto>
            ) {
                if(response.isSuccessful) {
                    responseCreateNote.value = response.body()
                }
            }

            override fun onFailure(call: Call<NotaDto>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error al crear", Toast.LENGTH_LONG).show()

            }
        })

        return responseCreateNote
    }


    fun deleteNote(idNote: String) {
        val call: Call<Void> = notesAppService.deleteNota(idNote)
        call.enqueue(object: Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(MyApp.context, "Nota borrada correctamente",Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error al borrar", Toast.LENGTH_LONG).show()

            }
        })

    }

    fun editNote(idNota: String,createNotaDto: CreateNotaDto): MutableLiveData<NotaDto> {
        val call: Call<NotaDto> = notesAppService.editNota(idNota,createNotaDto)
        call.enqueue(object: Callback<NotaDto> {
            override fun onResponse(
                call: Call<NotaDto>,
                response: Response<NotaDto>
            ) {
                if(response.isSuccessful) {
                    responseCreateNote.value = response.body()
                }
            }

            override fun onFailure(call: Call<NotaDto>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error al crear", Toast.LENGTH_LONG).show()

            }
        })

        return responseCreateNote
    }


}