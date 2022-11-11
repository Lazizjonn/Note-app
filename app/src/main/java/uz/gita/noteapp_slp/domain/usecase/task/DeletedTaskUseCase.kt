package uz.gita.noteapp_slp.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp_slp.data.model.common.TaskData
import uz.gita.noteapp_slp.data.sources.local.room.entity.TaskEntity

interface DeletedTaskUseCase {
    fun getAllDeletedTasks(): Flow<List<TaskData>>

    fun deleteTask(data: TaskEntity): Flow<Unit>
}