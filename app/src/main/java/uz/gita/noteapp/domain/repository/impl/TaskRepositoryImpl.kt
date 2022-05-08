package uz.gita.noteapp.domain.repository.impl

import uz.gita.noteapp.data.sources.local.room.dao.TaskDao
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity
import uz.gita.noteapp.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun getAllTasks(): List<TaskEntity> = taskDao.getAllTasks()
    override suspend fun getAllDeletedTasks(): List<TaskEntity> = taskDao.getAllDeletedTasks()
    override suspend fun insertTask(data: TaskEntity) = taskDao.insertTask(data)
    override suspend fun updateTask(data: TaskEntity) = taskDao.updateTask(data)
    override suspend fun deleteTask(data: TaskEntity) = taskDao.deleteTask(data)
}