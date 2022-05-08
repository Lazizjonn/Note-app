package uz.gita.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.TaskData

interface TaskViewModel {
    val taskLiveData: LiveData<List<TaskData>>
}