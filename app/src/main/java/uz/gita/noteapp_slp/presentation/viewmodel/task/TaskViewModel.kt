package uz.gita.noteapp_slp.presentation.viewmodel.task

import androidx.lifecycle.LiveData
import uz.gita.noteapp_slp.data.model.common.TaskData

interface TaskViewModel {
    val allTaskLiveData: LiveData<List<TaskData>>

    fun getTaskList()
}