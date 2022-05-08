package uz.gita.noteapp.data.sources.local.room.dao

import androidx.room.*
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(data: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(data: NoteEntity)

    @Delete
    suspend fun deleteNote(data: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE NoteEntity.isDeleted == 0")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE NoteEntity.isDeleted == 1")
    suspend fun getAllDeletedNotes(): List<NoteEntity>

}