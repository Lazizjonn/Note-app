package uz.gita.noteapp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity

interface AddNoteViewModel {
    val noteAddedLiveData: LiveData<Unit>

    fun saveNote(data: NoteEntity)
}