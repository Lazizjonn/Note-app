package uz.gita.noteapp.data.sources.local.room.dao

import androidx.room.*
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(data: TaskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(data: TaskEntity)

    @Delete
    suspend fun deleteNote(data: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.isDeleted == false")
    suspend fun getAllNotes(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.isDeleted == true")
    suspend fun getAllDeletedNotes(): List<TaskEntity>
}