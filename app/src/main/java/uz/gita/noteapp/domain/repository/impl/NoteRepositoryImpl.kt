package uz.gita.noteapp.domain.repository.impl

import uz.gita.noteapp.data.sources.local.room.dao.NoteDao
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {


    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

}