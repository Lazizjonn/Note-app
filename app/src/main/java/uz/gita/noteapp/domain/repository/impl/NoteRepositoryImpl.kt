package uz.gita.noteapp.domain.repository.impl

import uz.gita.noteapp.data.sources.local.room.dao.NoteDao
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity
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


    override suspend fun getTags(): List<TagEntity>? {
        try {
            val result = noteDao.getTagList()
            return result
        } catch (e: Throwable){
            return null
        }
    }


    override suspend fun deleteTag(list: List<TagEntity>): Boolean {
        try {
            noteDao.deleteTag(list)
            return true
        } catch (e: Throwable){
            return false
        }

    }
    override suspend fun insertTag(list: List<TagEntity>): Boolean {
        try {
            noteDao.insertTag(list)
            return true
        } catch (e: Throwable){
            return false
        }

    }


}

