package uz.gita.noteapp.domain.usecase.task.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.model.common.TaskData
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity
import uz.gita.noteapp.data.sources.local.room.entity.getTaskData
import uz.gita.noteapp.domain.repository.TaskRepository
import uz.gita.noteapp.domain.usecase.task.DeletedTaskUseCase
import javax.inject.Inject

class DeletedTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : DeletedTaskUseCase {

    override fun getAllDeletedTasks(): Flow<List<TaskData>> = flow<List<TaskData>> {
        val list: List<TaskData> = repository.getAllDeletedTasks().map {
            it.getTaskData()
        }
        emit(list)
    }.flowOn(Dispatchers.IO)

    override fun deleteTask(data: TaskEntity) = repository.deleteTask(data)

}