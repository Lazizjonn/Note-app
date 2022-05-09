package uz.gita.noteapp.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.wordpress.aztec.Aztec
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.IAztecToolbarClickListenerImpl
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.databinding.FragmentAddNoteScreenBinding
import uz.gita.noteapp.presentation.viewmodel.note.AddNoteViewModel
import uz.gita.noteapp.presentation.viewmodel.note.impl.AddNoteViewModelImpl
import uz.gita.noteapp.utils.hideKeyboard

@AndroidEntryPoint
class AddNoteScreen : Fragment(R.layout.fragment_add_note_screen) {
    private val binding by viewBinding(FragmentAddNoteScreenBinding::bind)
    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.addNoteBtnBack.setOnClickListener {
            onSaveNoteClick()
        }
        Aztec.with(this.binding.noteInputView, this.binding.formattingToolbar, IAztecToolbarClickListenerImpl())
    }

    private fun onSaveNoteClick() {
        val title = binding.addNoteTitle.toFormattedHtml()
        val text = binding.noteInputView.toFormattedHtml()

        binding.noteInputView.hideKeyboard()

        if (text.isEmpty()) { return }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.saveNote(NoteEntity(id = 0, title = title, note = text, createTime = System.currentTimeMillis()))
            findNavController().navigateUp() // go back to home fragment

        }

    }

}