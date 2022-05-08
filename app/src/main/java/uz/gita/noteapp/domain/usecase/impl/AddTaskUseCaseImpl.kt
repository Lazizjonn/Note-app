package uz.gita.noteapp.domain.usecase.impl

import uz.gita.noteapp.domain.repository.TaskRepository
import uz.gita.noteapp.domain.usecase.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : AddTaskUseCase {
}