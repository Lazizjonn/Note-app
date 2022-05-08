package uz.gita.noteapp.domain.repository.impl

import uz.gita.noteapp.data.sources.local.room.dao.NoteDao
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {


    override suspend fun getAllNotes(): List<NoteEntity> = noteDao.getAllNotes()
    override suspend fun getAllDeletedNotes(): List<NoteEntity> = noteDao.getAllDeletedNotes()
    override suspend fun insertNote(data: NoteEntity) = noteDao.insertNote(data)
    override suspend fun updateNote(data: NoteEntity) = noteDao.updateNote(data)
    override suspend fun deleteNote(data: NoteEntity) = noteDao.deleteNote(data)


}