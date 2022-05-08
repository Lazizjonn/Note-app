package uz.gita.noteapp.presentation.viewmodel.task

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.TaskData

interface DeletedTaskViewModel {
    val allDeletedTaskLiveData: LiveData<List<TaskData>>
}