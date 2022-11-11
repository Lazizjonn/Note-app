package uz.gita.noteapp_slp.domain.usecase.note.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.sources.local.room.entity.getNoteData
import uz.gita.noteapp_slp.domain.repository.NoteRepository
import uz.gita.noteapp_slp.domain.usecase.note.DeletedNoteUseCase
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


}
