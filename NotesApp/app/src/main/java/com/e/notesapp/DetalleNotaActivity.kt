package com.e.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.e.notesapp.common.Constantes
import com.e.notesapp.common.MyApp
import com.e.notesapp.viewmodel.DetalleNoteViewModel
import com.e.notesapp.viewmodel.NotesViewModel

class DetalleNotaActivity : AppCompatActivity() {

    lateinit var idNota: String
    lateinit var idNotaEditar: String
    var detalleNoteViewModel: DetalleNoteViewModel = DetalleNoteViewModel()
    var notaViewModel: NotesViewModel = NotesViewModel()
    lateinit var titulo: TextView
    lateinit var contenido: TextView
    lateinit var autor: TextView
    lateinit var fechaCreacion: TextView
    lateinit var fechaEdicion: TextView
    lateinit var borrar: Button
    lateinit var editar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_nota)


        titulo = findViewById(R.id.titulo2)
        contenido = findViewById(R.id.contenido)
        autor = findViewById(R.id.autor)
        fechaCreacion = findViewById(R.id.textView7)
        fechaEdicion = findViewById(R.id.textView6)
        borrar = findViewById(R.id.button2)
        editar = findViewById(R.id.button)


        val extras = intent.extras

        idNota = extras!!.getString(Constantes.NOTA_ID).toString()
        Constantes.NOTA_ID = ""



        detalleNoteViewModel =
            ViewModelProvider(this).get(DetalleNoteViewModel::class.java)
        detalleNoteViewModel.getNote(idNota)
            .observe(this, androidx.lifecycle.Observer {
                if (it != null) {
                    idNotaEditar = it.id.toString()
                    titulo.text = (it.titulo)
                    contenido.text = (it.contenido)
                    autor.text = (it.autor.username)
                    fechaCreacion.text = (it.fechaCreacion)
                    fechaEdicion.text = (it.fechaUltEdicion)


                }
            })

        borrar.setOnClickListener(View.OnClickListener {
            notaViewModel.deleteNote(idNota)
            val i = Intent(this, MainActivity::class.java)

            startActivity(i)

        })

        editar.setOnClickListener(View.OnClickListener {

            val i = Intent(MyApp.context, CreateNotaActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("idnotaEditar",idNota )
            i.putExtra("editar", "true")

            startActivity(i)
        })
    }


    }

