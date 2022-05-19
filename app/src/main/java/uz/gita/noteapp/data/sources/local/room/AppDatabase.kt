package uz.gita.noteapp.data.sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.noteapp.data.sources.local.room.dao.NoteDao
import uz.gita.noteapp.data.sources.local.room.dao.TaskDao
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity

@Database(entities = [NoteEntity::class, TaskEntity::class, TagEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getTaskDao(): TaskDao
}