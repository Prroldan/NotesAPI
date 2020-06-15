package com.e.notesapp

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.e.notesapp.common.Constantes
import com.e.notesapp.common.MyApp
import com.e.notesapp.modelos.ListadoNotaResponseItem
import com.e.notesapp.viewmodel.NotesViewModel

import kotlinx.android.synthetic.main.fragment_notas_list.view.*


class MyNotaRecyclerViewAdapter(

) : RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder>() {

    private var mValues: List<ListadoNotaResponseItem?> = ArrayList()
    private val mOnClickListener: View.OnClickListener
    var notasViewModel:NotesViewModel = NotesViewModel()
    lateinit var idNoteSeleccionada:String

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ListadoNotaResponseItem
            idNoteSeleccionada = item.id
            val intent = Intent(MyApp.context, DetalleNotaActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            notasViewModel.setIdNotaSeleccionada(idNoteSeleccionada)
            intent.putExtra(Constantes.NOTA_ID,idNoteSeleccionada )
            MyApp.context?.let { startActivity(it, intent, null) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notas_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        if (item != null) {
            holder.titulo.text = item.titulo
            holder.fechaCreacion.text = item.fechaCreacion

        }


        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listEventos: List<ListadoNotaResponseItem?>) {
        mValues = listEventos!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titulo: TextView = mView.titulo
        val fechaCreacion: TextView = mView.autor




    }
}