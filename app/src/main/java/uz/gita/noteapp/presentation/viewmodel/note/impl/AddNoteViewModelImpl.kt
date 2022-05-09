package uz.gita.noteapp.presentation.viewmodel.note.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.noteapp.domain.usecase.note.AddNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.note.AddNoteViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModelImpl @Inject constructor(
     val useCase: AddNoteUseCase
) : ViewModel(), AddNoteViewModel {


}