package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.noteapp.domain.usecase.note.AddNoteUseCase
import uz.gita.noteapp.domain.usecase.note.DeletedNoteUseCase
import uz.gita.noteapp.domain.usecase.note.NoteUseCase
import uz.gita.noteapp.domain.usecase.note.impl.AddNoteUseCaseImpl
import uz.gita.noteapp.domain.usecase.note.impl.DeletedNoteUseCaseImpl
import uz.gita.noteapp.domain.usecase.note.impl.NoteUseCaseImpl
import uz.gita.noteapp.domain.usecase.task.AddTaskUseCase
import uz.gita.noteapp.domain.usecase.task.DeletedTaskUseCase
import uz.gita.noteapp.domain.usecase.task.TaskUseCase
import uz.gita.noteapp.domain.usecase.task.impl.AddTaskUseCaseImpl
import uz.gita.noteapp.domain.usecase.task.impl.DeletedTaskUseCaseImpl
import uz.gita.noteapp.domain.usecase.task.impl.TaskUseCaseImpl

@[Module InstallIn(ViewModelComponent::class)]
interface UseCaseModule {

    @Binds
    fun getAddNoteUseCase(impl: AddNoteUseCaseImpl): AddNoteUseCase

    @Binds
    fun getAddTaskUseCase(impl: AddTaskUseCaseImpl): AddTaskUseCase

    @Binds
    fun getNoteUseCase(impl: NoteUseCaseImpl): NoteUseCase

    @Binds
    fun getTaskUseCase(impl: TaskUseCaseImpl): TaskUseCase

    @Binds
    fun getDeletedTaskUseCase(impl: DeletedTaskUseCaseImpl): DeletedTaskUseCase

    @Binds
    fun getDeletedNoteUseCase(impl: DeletedNoteUseCaseImpl): DeletedNoteUseCase
}