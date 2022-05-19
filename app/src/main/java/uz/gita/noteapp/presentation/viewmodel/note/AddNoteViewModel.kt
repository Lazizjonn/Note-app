package uz.gita.noteapp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity

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