package com.e.notesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.e.notesapp.modelos.ListadoNotaResponseItem
import com.e.notesapp.viewmodel.NotesViewModel

class NotasListFragment : Fragment() {


private lateinit var notasViewModel: NotesViewModel

private lateinit var myNotaRecyclerViewAdapter: MyNotaRecyclerViewAdapter
private lateinit var allNotes: List<ListadoNotaResponseItem?>

private var columnCount = 2


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
        columnCount = it.getInt(ARG_COLUMN_COUNT)
    }
}

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view =
        inflater.inflate(R.layout.fragment_notas_list_list, container, false)

    myNotaRecyclerViewAdapter =
        MyNotaRecyclerViewAdapter()

    // Set the adapter
    if (view is RecyclerView) {
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = myNotaRecyclerViewAdapter
        }
    }
    notasViewModel = NotesViewModel()

    notasViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
        allNotes = it
        myNotaRecyclerViewAdapter.setData(allNotes)
    })

    return view
}


companion object {

    const val ARG_COLUMN_COUNT = "column-count"

    @JvmStatic
    fun newInstance(columnCount: Int) =
        NotasListFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_COLUMN_COUNT, columnCount)
            }
        }
}
}