package uz.gita.noteapp.presentation.viewmodel.note.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.domain.usecase.note.DeletedNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.note.DeletedNoteViewModel
import javax.inject.Inject

@HiltViewModel
class DeletedNoteViewModelImpl @Inject constructor(
    private val useCase: DeletedNoteUseCase
) : ViewModel(), DeletedNoteViewModel {

    override val allDeletedNoteLiveData = MutableLiveData<List<NoteData>>()

    init {
        load()
    }

    private fun load() {
        useCase.getAllDeletedNotes().onEach {
            allDeletedNoteLiveData.value = it
        }.launchIn(viewModelScope)
    }


}