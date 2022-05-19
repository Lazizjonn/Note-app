package uz.gita.noteapp.domain.usecase.note.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.getNoteData
import uz.gita.noteapp.domain.repository.NoteRepository
import uz.gita.noteapp.domain.usecase.note.NoteUseCase
import javax.inject.Inject

class NoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteUseCase {


//    override suspend fun getTags(): List<TagData>? {
//        val result = repository.getTags()
//        return result?.map { it.toTagData() }
//    }

    override fun getAllNotes(): Flow<List<NoteData>> = flow<List<NoteData>> {
        val list: List<NoteData> = repository.getAllNotes().map {
            it.getNoteData()
        }
        emit(list)
    }.flowOn(Dispatchers.IO)

    override suspend fun updateNote(data: NoteEntity) {
        repository.updateNote(data)
    }


}