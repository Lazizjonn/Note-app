package uz.gita.noteapp_slp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp_slp.domain.repository.AppRepository
import uz.gita.noteapp_slp.domain.repository.TaskRepository
import uz.gita.noteapp_slp.domain.repository.NoteRepository
import uz.gita.noteapp_slp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp_slp.domain.repository.impl.TaskRepositoryImpl
import uz.gita.noteapp_slp.domain.repository.impl.NoteRepositoryImpl
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @[Singleton Binds]
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository

    @[Singleton Binds]
    fun getCheckListRepository(impl: TaskRepositoryImpl): TaskRepository

    @[Singleton Binds]
    fun getNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}