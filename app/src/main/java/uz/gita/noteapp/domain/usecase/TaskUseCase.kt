package uz.gita.noteapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.model.TaskData

interface TaskUseCase {

    fun getAllTasks(): Flow<List<TaskData>>
}