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
    private val filterTag = ArrayList<String>()
    private var recentNotes = ArrayList<NoteData>()

    override val allNoteLiveData = MutableLiveData<Pair<Boolean,List<NoteData>>>()
//    override val tagListLiveData = MutableLiveData<List<TagData>>()
    override val tagFilterLiveData = MutableLiveData<TagData>()


    override fun getTags(){
        viewModelScope.launch {
//            val result = useCase.getTags() ?: listOf()
//            tagListLiveData.postValue(result)
        }
    }

    override fun load() {
        useCase.getAllNotes().onEach {
            recentNotes.clear()
            recentNotes.addAll(it)
            filterTag.clear()
            allNoteLiveData.value = Pair(false, it)
        }.launchIn(viewModelScope)
    }

    override fun tagFilter(tag: TagData) {
        if (filterTag.contains(tag.tag)) {
            filterTag.remove(tag.tag)
            tag.isChosen = false
            filterTag.remove(tag.tag)
        } else {
            tag.isChosen = true
            filterTag.add(tag.tag)
        }
        tagFilterLiveData.value = tag

        if (filterTag.size>0) filterNotes(filterTag, recentNotes)
        else load()
    }

    override fun filterNotes(allTags: List<String>, noteData: List<NoteData>) {
        val A = ArrayList<NoteData>()
        noteData.onEach { note ->
            note.tag.onEachIndexed {a, tagInNote ->
                allTags.onEach { tag ->
                    if (tag == tagInNote){
                        A.add(note)
                        return@onEachIndexed
                    }
                }
            }
        }
        allNoteLiveData.value = Pair(true, A.distinct())
    }

}