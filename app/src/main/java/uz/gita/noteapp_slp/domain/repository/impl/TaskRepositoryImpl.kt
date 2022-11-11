package uz.gita.noteapp_slp.domain.repository.impl

import kotlinx.coroutines.flow.flow
import uz.gita.noteapp_slp.data.sources.local.room.dao.TaskDao
import uz.gita.noteapp_slp.data.sources.local.room.entity.TaskEntity
import uz.gita.noteapp_slp.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun getAllTasks(): List<TaskEntity> = taskDao.getAllTasks()
    override suspend fun getAllDeletedTasks(): List<TaskEntity> = taskDao.getAllDeletedTasks()
    override fun insertTask(data: TaskEntity) = flow<Unit> {
        taskDao.insertTask(data)
        emit(Unit)
    }
    override fun updateTask(data: TaskEntity) = flow<Unit> {
        taskDao.updateTask(data)
        emit(Unit)
    }
    override fun deleteTask(data: TaskEntity) = flow<Unit> {
        taskDao.deleteTask(data)
        emit(Unit)
    }

}