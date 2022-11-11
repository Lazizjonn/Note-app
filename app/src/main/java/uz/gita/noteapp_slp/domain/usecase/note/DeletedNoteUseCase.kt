package uz.gita.noteapp_slp.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp_slp.data.model.common.NoteData

interface DeletedNoteUseCase {
    fun getAllDeletedNotes(): Flow<List<NoteData>>

}