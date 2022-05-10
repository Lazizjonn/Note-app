package uz.gita.noteapp.domain.usecase.note.impl

import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity
import uz.gita.noteapp.domain.repository.NoteRepository
import uz.gita.noteapp.domain.usecase.note.AddNoteUseCase
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : AddNoteUseCase {

    override suspend fun insertNote(data: NoteEntity) = repository.insertNote(data)
    override suspend fun updateNote(data: NoteEntity)= repository.updateNote(data)

    override suspend fun getTags(): List<TagData>? {
        val result = repository.getTags()
        return result?.map { it.toTagData() }
    }

    override suspend fun deleteTag(list: List<TagData>): Boolean {
        return repository.deleteTag(list.map { it.toTagEntity() })
    }

    override suspend fun insertTag(list: List<TagData>): Boolean {
        return repository.insertTag(list.map { it.toTagEntity() })
    }


}