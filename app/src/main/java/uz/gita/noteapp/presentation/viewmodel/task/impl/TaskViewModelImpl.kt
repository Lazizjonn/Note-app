package uz.gita.noteapp.presentation.viewmodel.task.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.TaskData
import uz.gita.noteapp.domain.usecase.task.TaskUseCase
import uz.gita.noteapp.presentation.viewmodel.task.TaskViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModelImpl @Inject constructor(
    private val useCase: TaskUseCase
) : ViewModel(), TaskViewModel {

    override val allTaskLiveData = MutableLiveData<List<TaskData>>()

    init {
        load()
    }

    private fun load() {
        useCase.getAllTasks().onEach {
            allTaskLiveData.value = it
        }.launchIn(viewModelScope)
    }

}