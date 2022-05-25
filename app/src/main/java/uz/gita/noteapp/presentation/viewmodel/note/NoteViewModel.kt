package uz.gita.noteapp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData

interface NoteViewModel {
    val allNoteLiveData: LiveData<Pair<Boolean,List<NoteData>>>
//    val tagListLiveData: LiveData<List<TagData>>
    val tagFilterLiveData: LiveData<TagData>
    val openFilterLiveData : LiveData<Unit>

    fun getTags()

    fun load()

    fun tagFilter(tag: TagData)

    fun filterNotes(allTags: List<String>, noteData: List<NoteData>)

    fun openFilterDialog()

}