package uz.gita.noteapp.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.noteapp.domain.usecase.AddNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.AddNoteViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModelImpl @Inject constructor(
    private val useCase: AddNoteUseCase
) : ViewModel(), AddNoteViewModel {
}