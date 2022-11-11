package uz.gita.noteapp_slp.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp_slp.data.model.common.TaskData

interface AddTaskUseCase {
    fun insertTask(data: TaskData): Flow<Unit>
    fun updateTask(data: TaskData): Flow<Unit>
    fun deleteTask(data: TaskData): Flow<Unit>
}