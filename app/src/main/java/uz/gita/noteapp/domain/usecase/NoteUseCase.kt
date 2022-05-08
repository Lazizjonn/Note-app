package uz.gita.noteapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.model.NoteData

interface NoteUseCase {
    fun getAllNotes():Flow<List<NoteData>>
}