package uz.gita.noteapp.presentation.ui.pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.databinding.FragmentPageNoteBinding
import uz.gita.noteapp.presentation.ui.adapter.NoteAdapter
import uz.gita.noteapp.presentation.viewmodel.NoteViewModel
import uz.gita.noteapp.presentation.viewmodel.impl.NoteViewModelImpl

@AndroidEntryPoint
class NotePage : Fragment(R.layout.fragment_page_note) {
    private val binding by viewBinding(FragmentPageNoteBinding::bind)
    private val viewModel: NoteViewModel by viewModels<NoteViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.noteLiveData.observe(viewLifecycleOwner, noteObserver)
    }

    private val noteObserver = Observer<List<NoteData>> { noteData ->
        val adapter = NoteAdapter(noteData)
        binding.noteRecyclerview.adapter = adapter
        adapter.setNoteListener {
            findNavController().navigate(R.id.addNoteScreen)
        }
    }
}