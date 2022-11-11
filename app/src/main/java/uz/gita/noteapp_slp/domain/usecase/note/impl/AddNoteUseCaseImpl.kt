package uz.gita.noteapp_slp.domain.usecase.note.impl

import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp_slp.domain.repository.NoteRepository
import uz.gita.noteapp_slp.domain.usecase.note.AddNoteUseCase
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : AddNoteUseCase {

    override suspend fun insertNote(data: NoteData) = repository.insertNote(data.toNoteEntity())
    override suspend fun deleteNote(data: NoteData) = repository.deleteNote(data.toNoteEntity())
    override suspend fun updateNote(data: NoteEntity)= repository.updateNote(data)

//    override suspend fun getTags(): List<TagData>? {
//        val result = repository.getTags()
////        return result?.map { it.toTagData() }
//    }
//
//    override suspend fun deleteTag(list: List<TagData>): Boolean {
//        return repository.deleteTag(list.map { it.toTagEntity() })
//    }
//
//    override suspend fun insertTag(list: List<TagData>): Boolean {
//        return repository.insertTag(list.map { it.toTagEntity() })
//    }


}