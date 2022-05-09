package uz.gita.noteapp.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity

interface NoteUseCase {
    fun getAllNotes():Flow<List<NoteData>>
    suspend fun updateNote(data: NoteEntity)
}