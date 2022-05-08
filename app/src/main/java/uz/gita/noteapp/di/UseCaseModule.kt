package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.noteapp.domain.usecase.*
import uz.gita.noteapp.domain.usecase.impl.*

@[Module InstallIn(ViewModelComponent::class)]
interface UseCaseModule {

    @Binds
    fun getAddNoteUseCase(impl: AddNoteUseCaseImpl): AddNoteUseCase

    @Binds
    fun getAddTaskUseCase(impl: AddTaskUseCaseImpl): AddTaskUseCase

    @Binds
    fun getMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    fun getNoteUseCase(impl: NoteUseCaseImpl): NoteUseCase

    @Binds
    fun getTaskUseCase(impl: TaskUseCaseImpl): TaskUseCase
}