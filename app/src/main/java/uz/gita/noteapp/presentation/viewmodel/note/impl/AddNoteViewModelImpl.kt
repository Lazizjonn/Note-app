package uz.gita.noteapp.presentation.viewmodel.note.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity
import uz.gita.noteapp.domain.usecase.note.AddNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.note.AddNoteViewModel
import javax.inject.Inject
@HiltViewModel
class AddNoteViewModelImpl @Inject constructor(
    private val useCase: AddNoteUseCase
) : ViewModel(), AddNoteViewModel {

    override val noteAddedLiveData = MutableLiveData<Unit>()
    override val tagListLiveData = MutableLiveData<List<TagData>>()
    override val tagDeletedLiveData = MutableLiveData<Boolean>()
    override val tagInsertedLiveData = MutableLiveData<Boolean>()

    init {
        getTags()
    }

    override fun saveNote(data: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) { useCase.insertNote(data) }
    }

    override fun getTags(){
        viewModelScope.launch {
            val result = useCase.getTags() ?: listOf()
            tagListLiveData.postValue(result)
        }
    }

    override fun deleteTag(list: List<TagData>){
        viewModelScope.launch {
            val result = useCase.deleteTag(list)
            tagDeletedLiveData.postValue(result)
        }
    }

    override fun insertTag(list: List<TagData>){
         viewModelScope.launch {
             val result = useCase.insertTag(list)
             tagInsertedLiveData.postValue(result)
         }
    }


}

