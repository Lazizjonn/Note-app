package uz.gita.noteapp.presentation.viewmodel.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.noteapp.data.model.common.TaskData
import uz.gita.noteapp.domain.usecase.task.AddTaskUseCase
import javax.inject.Inject

interface AddTaskViewModel {
    val taskAddedLiveData: LiveData<Unit>
    val taskDeletedLiveData: LiveData<Unit>
    val taskUpdatedLiveData: LiveData<Unit>

    fun addTask(task: TaskData)

    fun deleteTask(task: TaskData)

    fun updateTask(task: TaskData)

}