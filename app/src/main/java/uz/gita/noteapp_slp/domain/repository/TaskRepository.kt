package uz.gita.noteapp_slp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp_slp.data.sources.local.room.entity.TaskEntity

interface TaskRepository {

    suspend fun getAllTasks(): List<TaskEntity>
    suspend fun getAllDeletedTasks(): List<TaskEntity>
    fun insertTask(data: TaskEntity): Flow<Unit>
    fun updateTask(data: TaskEntity): Flow<Unit>
    fun deleteTask(data: TaskEntity): Flow<Unit>
}