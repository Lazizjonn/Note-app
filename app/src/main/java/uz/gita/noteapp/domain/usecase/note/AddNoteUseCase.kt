package uz.gita.noteapp.domain.usecase.note

import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity

interface AddNoteUseCase {
    suspend fun insertNote(data: NoteEntity)
    suspend fun updateNote(data: NoteEntity)

    suspend fun getTags(): List<TagData>?

    suspend fun deleteTag(list: List<TagData>):Boolean
    suspend fun insertTag(list: List<TagData>): Boolean
}