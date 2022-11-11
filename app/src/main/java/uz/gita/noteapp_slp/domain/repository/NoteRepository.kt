package uz.gita.noteapp_slp.domain.repository

import uz.gita.noteapp_slp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp_slp.data.sources.local.room.entity.TagEntity

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun getAllDeletedNotes(): List<NoteEntity>
    suspend fun insertNote(data: NoteEntity): Boolean
    suspend fun deleteNote(data: NoteEntity): Boolean
    suspend fun updateNote(data: NoteEntity)

    suspend fun getTags(): List<TagEntity>?

    suspend fun deleteTag(list: List<TagEntity>): Boolean
    suspend fun insertTag(list: List<TagEntity>): Boolean
}