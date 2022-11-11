package uz.gita.noteapp_slp.domain.usecase.task.impl

import uz.gita.noteapp_slp.data.model.common.TaskData
import uz.gita.noteapp_slp.domain.repository.TaskRepository
import uz.gita.noteapp_slp.domain.usecase.task.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : AddTaskUseCase {
    override fun insertTask(data: TaskData) = repository.insertTask(data.toTaskEntity())
    override fun updateTask(data: TaskData) = repository.updateTask(data.toTaskEntity())
    override fun deleteTask(data: TaskData) = repository.deleteTask(data.toTaskEntity())
}