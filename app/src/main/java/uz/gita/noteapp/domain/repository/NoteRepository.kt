package uz.gita.noteapp.domain.repository

import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun getAllDeletedNotes(): List<NoteEntity>
    suspend fun insertNote(data: NoteEntity)
    suspend fun updateNote(data: NoteEntity)
    suspend fun deleteNote(data: NoteEntity)
}