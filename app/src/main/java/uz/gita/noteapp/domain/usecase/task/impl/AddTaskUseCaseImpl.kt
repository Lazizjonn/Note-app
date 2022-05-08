package uz.gita.noteapp.domain.usecase.task.impl

import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity
import uz.gita.noteapp.domain.repository.TaskRepository
import uz.gita.noteapp.domain.usecase.task.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : AddTaskUseCase {
    override suspend fun insertTask(data: TaskEntity) = repository.insertTask(data)
    override suspend fun updateTask(data: TaskEntity) = repository.updateTask(data)
}