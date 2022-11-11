package uz.gita.noteapp_slp.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.sources.local.room.entity.NoteEntity

interface NoteUseCase {
    fun getAllNotes():Flow<List<NoteData>>

    suspend fun updateNote(data: NoteEntity)

//
//    suspend fun getTags(): List<TagData>?

}