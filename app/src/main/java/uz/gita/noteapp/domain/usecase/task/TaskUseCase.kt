package uz.gita.noteapp.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.model.common.TaskData
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

interface TaskUseCase {
    fun getAllTasks(): Flow<List<TaskData>>
    suspend fun updateTask(data: TaskEntity)
}