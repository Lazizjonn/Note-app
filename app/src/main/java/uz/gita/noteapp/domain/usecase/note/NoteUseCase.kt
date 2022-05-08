package uz.gita.noteapp.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.model.NoteData

interface NoteUseCase {
    fun getAllNotes():Flow<List<NoteData>>
}