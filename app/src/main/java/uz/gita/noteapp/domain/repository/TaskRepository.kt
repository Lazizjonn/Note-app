package uz.gita.noteapp.domain.repository

import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

interface TaskRepository {

    suspend fun getAllTasks(): List<TaskEntity>
}