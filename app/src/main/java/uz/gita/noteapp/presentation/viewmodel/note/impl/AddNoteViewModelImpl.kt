package uz.gita.noteapp.presentation.viewmodel.note.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.domain.usecase.note.AddNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.note.AddNoteViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModelImpl @Inject constructor(
    val useCase: AddNoteUseCase
) : ViewModel(), AddNoteViewModel {

    override val noteAddedLiveData = MutableLiveData<Unit>()

    override fun saveNote(data: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertNote(data)
        }
    }


}