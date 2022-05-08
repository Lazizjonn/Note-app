package uz.gita.noteapp.domain.usecase.task.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.model.TaskData
import uz.gita.noteapp.data.sources.local.room.entity.getTaskData
import uz.gita.noteapp.domain.repository.TaskRepository
import uz.gita.noteapp.domain.usecase.task.TaskUseCase
import javax.inject.Inject

class TaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : TaskUseCase {

    override fun getAllTasks(): Flow<List<TaskData>> = flow<List<TaskData>> {
        val list: List<TaskData> = repository.getAllTasks().map {
            it.getTaskData()
        }
        emit(list)
    }.flowOn(Dispatchers.IO)
}