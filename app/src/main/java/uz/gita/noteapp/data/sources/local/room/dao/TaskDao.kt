package uz.gita.noteapp.data.sources.local.room.dao

import androidx.room.*
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(data: TaskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(data: TaskEntity)

    @Delete
    suspend fun deleteTask(data: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.isDeleted == 0")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.isDeleted == 1")
    suspend fun getAllDeletedTasks(): List<TaskEntity>
}