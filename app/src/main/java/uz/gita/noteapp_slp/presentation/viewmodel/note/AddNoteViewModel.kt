package uz.gita.noteapp_slp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.model.common.TagData

interface AddNoteViewModel {
    val noteAddedLiveData: LiveData<Unit>
    val noteDeletedLiveData: LiveData<Unit>
    val tagListLiveData: LiveData<List<TagData>>
    val tagDeletedLiveData: LiveData<Boolean>
    val tagInsertedLiveData: LiveData<Boolean>

    fun saveNote(data: NoteData)

    fun deleteNote(data: NoteData)

    fun getTags()

    fun deleteTags(list: List<TagData>)
    fun insertTags(list: List<TagData>)
}