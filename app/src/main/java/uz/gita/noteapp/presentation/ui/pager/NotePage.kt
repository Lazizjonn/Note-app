package uz.gita.noteapp.presentation.ui.pager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.databinding.FragmentPageNoteBinding
import uz.gita.noteapp.presentation.ui.adapter.NoteAdapter
import uz.gita.noteapp.presentation.viewmodel.note.NoteViewModel
import uz.gita.noteapp.presentation.viewmodel.note.impl.NoteViewModelImpl

@AndroidEntryPoint
class NotePage : Fragment(R.layout.fragment_page_note) {
    private val binding by viewBinding(FragmentPageNoteBinding::bind)
    private val viewModel: NoteViewModel by viewModels<NoteViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.allNoteLiveData.observe(viewLifecycleOwner, noteObserver)
    }

    private val noteObserver = Observer<List<NoteData>> { noteData ->
        Log.d("TTT", "NotePage: ${noteData.size} ")
        val adapter = NoteAdapter(noteData)
        binding.noteRecyclerview.adapter = adapter
        binding.noteRecyclerview.layoutManager =LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false )
        adapter.submitList(noteData)
        adapter.setNoteListener { findNavController().navigate(R.id.addNoteScreen) }
    }
}