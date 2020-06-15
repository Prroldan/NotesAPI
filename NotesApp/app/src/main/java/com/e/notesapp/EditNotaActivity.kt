package com.e.notesapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.notesapp.common.Constantes
import com.e.notesapp.dto.CreateNotaDto
import com.e.notesapp.viewmodel.NotesViewModel
import java.time.LocalDate

class EditNotaActivity : AppCompatActivity() {

    lateinit var titulo: TextView
    lateinit var contenido: TextView
    lateinit var buttonSave: Button
    lateinit var idNotaEditar:String
    var notesViewModel: NotesViewModel = NotesViewModel()
    @RequiresApi(Build.VERSION_CODES.O)
    var fechaCreacion: LocalDate = LocalDate.now()

    lateinit var createNotaDto: CreateNotaDto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nota)

        titulo = findViewById(R.id.tituloEditar)
        contenido = findViewById(R.id.contenidoEditar)
        buttonSave = findViewById(R.id.buttonSave)

        val extras = intent.extras

        idNotaEditar = extras!!.getString(Constantes.NOTA_ID_EDIT).toString()
        //idNotaEditar = notesViewModel.getIdNotaSeleccionada().toString()



        notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)

        buttonSave.setOnClickListener(View.OnClickListener {

            createNotaDto = CreateNotaDto(
                titulo = titulo.text.toString(),
                contenido = contenido.text.toString(),
                fechaCreacion = fechaCreacion.toString()
            )
            notesViewModel.editNode(idNotaEditar,createNotaDto).observe(this, Observer {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            })


        })



    }
}
