package uz.gita.noteapp.domain.repository

import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteEntity>
}