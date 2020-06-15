package com.e.notesapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.notesapp.common.Constantes
import com.e.notesapp.dto.CreateNotaDto
import com.e.notesapp.modelos.DetalleNotaResponse
import com.e.notesapp.modelos.Nota
import com.e.notesapp.viewmodel.DetalleNoteViewModel
import com.e.notesapp.viewmodel.NotesViewModel
import java.time.LocalDate

class CreateNotaActivity : AppCompatActivity() {


    lateinit var titulo: TextView
    lateinit var contenido: TextView
    lateinit var buttonSave: Button
    var detalleNoteViewModel: DetalleNoteViewModel = DetalleNoteViewModel()
    lateinit var isEditable : String
    var idEditar : String = ""

    var notesViewModel: NotesViewModel = NotesViewModel()
    @RequiresApi(Build.VERSION_CODES.O)
    var fechaCreacion: LocalDate = LocalDate.now()

    lateinit var createNotaDto:CreateNotaDto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_nota)

        titulo = findViewById(R.id.titulo)
        contenido = findViewById(R.id.contenido)
        buttonSave = findViewById(R.id.buttonSave)

        var extras = intent.extras

        isEditable = intent!!.getStringExtra("editar")
        idEditar = intent!!.getStringExtra("idnotaEditar")


        notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)



            if(isEditable.equals("true")){
                detalleNoteViewModel.getNote(idEditar).observe(this, Observer {
                    titulo.setText(it.titulo)
                    contenido.setText(it.contenido)
                })
                buttonSave.setOnClickListener(View.OnClickListener {

                    createNotaDto = CreateNotaDto(
                        titulo = titulo.text.toString(),
                        contenido = contenido.text.toString(),
                        fechaCreacion = fechaCreacion.toString()
                    )
                    notesViewModel.editNode(idEditar,createNotaDto).observe(this, Observer {
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    })
                })

                }else {
                buttonSave.setOnClickListener(View.OnClickListener {

                    createNotaDto = CreateNotaDto(
                        titulo = titulo.text.toString(),
                        contenido = contenido.text.toString(),
                        fechaCreacion = fechaCreacion.toString()
                    )
                    notesViewModel.createNota(createNotaDto).observe(this, Observer {
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    })

                })
            }


    }
}
