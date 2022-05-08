package uz.gita.noteapp.domain.repository

import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

interface TaskRepository {

    suspend fun getAllTasks(): List<TaskEntity>
    suspend fun getAllDeletedTasks(): List<TaskEntity>
    suspend fun insertTask(data: TaskEntity)
    suspend fun updateTask(data: TaskEntity)
    suspend fun deleteTask(data: TaskEntity)
}