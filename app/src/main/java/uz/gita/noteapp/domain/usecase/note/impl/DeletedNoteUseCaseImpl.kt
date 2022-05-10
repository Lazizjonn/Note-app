package uz.gita.noteapp.domain.usecase.note.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity
import uz.gita.noteapp.data.sources.local.room.entity.getNoteData
import uz.gita.noteapp.domain.repository.NoteRepository
import uz.gita.noteapp.domain.usecase.note.DeletedNoteUseCase
import javax.inject.Inject

class DeletedNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : DeletedNoteUseCase {

    override fun getAllDeletedNotes(): Flow<List<NoteData>> = flow<List<NoteData>> {
        val list: List<NoteData> = repository.getAllDeletedNotes().map {
            it.getNoteData()
        }
        emit(list)
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteNote(data: NoteEntity) = repository.deleteNote(data)

}
