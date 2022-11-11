package uz.gita.noteapp_slp.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp_slp.data.model.common.TaskData
import uz.gita.noteapp_slp.data.sources.local.room.entity.TaskEntity

interface TaskUseCase {
    fun getAllTasks(): Flow<List<TaskData>>
    suspend fun updateTask(data: TaskEntity)
}