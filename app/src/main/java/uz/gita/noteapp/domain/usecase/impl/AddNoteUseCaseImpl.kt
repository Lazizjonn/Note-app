package uz.gita.noteapp.domain.usecase.impl

import uz.gita.noteapp.domain.repository.NoteRepository
import uz.gita.noteapp.domain.usecase.AddNoteUseCase
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : AddNoteUseCase {
}