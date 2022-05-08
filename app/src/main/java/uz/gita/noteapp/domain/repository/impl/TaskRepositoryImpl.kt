package uz.gita.noteapp.domain.repository.impl

import uz.gita.noteapp.data.sources.local.room.dao.TaskDao
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity
import uz.gita.noteapp.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun getAllTasks(): List<TaskEntity> {
       return taskDao.getAllNotes()
    }

}