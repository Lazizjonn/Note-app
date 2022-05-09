package uz.gita.noteapp.domain.usecase.note.impl

import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.domain.repository.NoteRepository
import uz.gita.noteapp.domain.usecase.note.AddNoteUseCase
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : AddNoteUseCase {

    override suspend fun insertNote(data: NoteEntity) = repository.insertNote(data)
    override suspend fun updateNote(data: NoteEntity)= repository.updateNote(data)
}