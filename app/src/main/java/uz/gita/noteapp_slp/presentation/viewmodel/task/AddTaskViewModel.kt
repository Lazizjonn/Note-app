package uz.gita.noteapp_slp.presentation.viewmodel.task

import androidx.lifecycle.LiveData
import uz.gita.noteapp_slp.data.model.common.TaskData

interface AddTaskViewModel {
    val taskAddedLiveData: LiveData<Unit>
    val taskDeletedLiveData: LiveData<Unit>
    val taskUpdatedLiveData: LiveData<Unit>

    fun addTask(task: TaskData)

    fun deleteTask(task: TaskData)

    fun updateTask(task: TaskData)

}