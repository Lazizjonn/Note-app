package uz.gita.noteapp_slp.domain.usecase.task.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp_slp.data.model.common.TaskData
import uz.gita.noteapp_slp.data.sources.local.room.entity.TaskEntity
import uz.gita.noteapp_slp.data.sources.local.room.entity.getTaskData
import uz.gita.noteapp_slp.domain.repository.TaskRepository
import uz.gita.noteapp_slp.domain.usecase.task.TaskUseCase
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

    override suspend fun updateTask(data: TaskEntity) {
        repository.updateTask(data)
    }
}