package uz.gita.noteapp.domain.usecase.note

import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity

interface AddNoteUseCase {
    suspend fun insertNote(data: NoteEntity)
    suspend fun updateNote(data: NoteEntity)
}