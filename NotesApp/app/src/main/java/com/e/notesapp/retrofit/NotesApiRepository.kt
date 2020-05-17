package com.e.notesapp.retrofit

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.e.notesapp.common.MyApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesApiRepository @Inject constructor(var notesAppService: NotesAppService){


    var notesAppClient:NotesApiClient = NotesApiClient()
    var allNotes: MutableLiveData<List<Nota>>



    init {
        notesAppService = notesAppClient.getNotesAppService()
        allNotes = MutableLiveData<List<Nota>>()
    }

    fun getAllnotes(): MutableLiveData<List<Nota>> {
        if(allNotes == null){
            allNotes = MutableLiveData<List<Nota>>()
        }

        val call : Call<ListadoNotasResponse> = notesAppService.getAllNotas()
        call.enqueue(object : Callback<ListadoNotasResponse> {
            override fun onResponse(
                call: Call<ListadoNotasResponse>,
                response: Response<ListadoNotasResponse>
            ) {
                if(response.isSuccessful){
                    allNotes.value = response.body()
                }
            }

            override fun onFailure(call: Call<ListadoNotasResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "No funciona cabesa",Toast.LENGTH_SHORT).show()
            }
        })

        return allNotes
    }

}