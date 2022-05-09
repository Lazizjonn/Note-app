package uz.gita.noteapp.presentation.viewmodel.task

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.common.TaskData

interface TaskViewModel {
    val allTaskLiveData: LiveData<List<TaskData>>
}