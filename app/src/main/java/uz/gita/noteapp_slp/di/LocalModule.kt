package uz.gita.noteapp_slp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp_slp.data.sources.local.room.AppDatabase
import uz.gita.noteapp_slp.data.sources.local.room.dao.NoteDao
import uz.gita.noteapp_slp.data.sources.local.room.dao.TaskDao
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class LocalModule {

    @[Provides Singleton]
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "Note")
        .fallbackToDestructiveMigration()
        .build()

    @[Provides Singleton]
    fun getNoteDao(database: AppDatabase): NoteDao = database.getNoteDao()

    @[Provides Singleton]
    fun getTaskDao(database: AppDatabase): TaskDao = database.getTaskDao()

}