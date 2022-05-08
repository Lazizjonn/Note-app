package uz.gita.noteapp.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentAddTaskScreenBinding
import uz.gita.noteapp.presentation.viewmodel.note.AddNoteViewModel
import uz.gita.noteapp.presentation.viewmodel.note.impl.AddNoteViewModelImpl

@AndroidEntryPoint
class AddTaskScreen : Fragment(R.layout.fragment_add_task_screen) {
    private val binding by viewBinding(FragmentAddTaskScreenBinding::bind)
    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}