package uz.gita.noteapp_slp.domain.usecase.note

import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.sources.local.room.entity.NoteEntity

interface AddNoteUseCase {
    suspend fun insertNote(data: NoteData): Boolean
    suspend fun deleteNote(data: NoteData): Boolean
    suspend fun updateNote(data: NoteEntity)

//    suspend fun getTags(): List<TagData>?
//
//    suspend fun deleteTag(list: List<TagData>):Boolean
//    suspend fun insertTag(list: List<TagData>): Boolean
}