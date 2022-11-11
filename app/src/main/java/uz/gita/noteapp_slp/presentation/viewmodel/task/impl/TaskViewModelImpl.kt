package uz.gita.noteapp_slp.presentation.viewmodel.task.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp_slp.data.model.common.TaskData
import uz.gita.noteapp_slp.domain.usecase.task.TaskUseCase
import uz.gita.noteapp_slp.presentation.viewmodel.task.TaskViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModelImpl @Inject constructor(
    private val useCase: TaskUseCase
) : ViewModel(), TaskViewModel {

    override val allTaskLiveData = MutableLiveData<List<TaskData>>()


    override fun getTaskList() {
        useCase.getAllTasks().onEach {
            allTaskLiveData.value = it
        }.launchIn(viewModelScope)
    }

}