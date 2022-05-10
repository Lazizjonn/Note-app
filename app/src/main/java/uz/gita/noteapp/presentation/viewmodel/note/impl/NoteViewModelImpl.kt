package uz.gita.noteapp.presentation.viewmodel.note.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.domain.usecase.note.NoteUseCase
import uz.gita.noteapp.presentation.viewmodel.note.NoteViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModelImpl @Inject constructor(
    private val useCase: NoteUseCase
) : ViewModel(), NoteViewModel {

    override val allNoteLiveData = MutableLiveData<List<NoteData>>()
    override val tagListLiveData = MutableLiveData<List<TagData>>()


    init {
        load()
        getTags()
    }


    override fun getTags(){
        viewModelScope.launch {
            val result = useCase.getTags() ?: listOf()
            tagListLiveData.postValue(result)
        }
    }

    private fun load() {
        useCase.getAllNotes().onEach {
            allNoteLiveData.value = it
        }.launchIn(viewModelScope)
    }

}