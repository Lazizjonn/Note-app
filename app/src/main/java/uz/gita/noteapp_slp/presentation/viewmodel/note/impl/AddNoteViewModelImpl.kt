package uz.gita.noteapp_slp.presentation.viewmodel.note.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.model.common.TagData
import uz.gita.noteapp_slp.domain.usecase.note.AddNoteUseCase
import uz.gita.noteapp_slp.presentation.viewmodel.note.AddNoteViewModel
import javax.inject.Inject
@HiltViewModel
class AddNoteViewModelImpl @Inject constructor(
    private val useCase: AddNoteUseCase
) : ViewModel(), AddNoteViewModel {

    override val noteAddedLiveData = MutableLiveData<Unit>()
    override val noteDeletedLiveData = MutableLiveData<Unit>()
    override val tagListLiveData = MutableLiveData<List<TagData>>()
    override val tagDeletedLiveData = MutableLiveData<Boolean>()
    override val tagInsertedLiveData = MutableLiveData<Boolean>()

    init {
        getTags()
    }

    override fun saveNote(data: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.insertNote(data)
            if (result) {
                noteAddedLiveData.postValue(Unit)
            }
        }
    }
    override fun deleteNote(data: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.deleteNote(data)
            if (result) {
                noteDeletedLiveData.postValue(Unit)
            }
        }
    }

    override fun getTags(){
        viewModelScope.launch {
//            val result = useCase.getTags() ?: listOf()
//            tagListLiveData.postValue(result)
        }
    }

    override fun deleteTags(list: List<TagData>){
        viewModelScope.launch {
//            val result = useCase.deleteTag(list)
//            tagDeletedLiveData.postValue(result)
        }
    }

    override fun insertTags(list: List<TagData>){
         viewModelScope.launch {
//             val result = useCase.insertTag(list)
//             tagInsertedLiveData.postValue(result)
         }
    }


}

