package uz.gita.noteapp.domain.usecase.task

import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

interface AddTaskUseCase {
    suspend fun insertTask(data: TaskEntity)
    suspend fun updateTask(data: TaskEntity)
}