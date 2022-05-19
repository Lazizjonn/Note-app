package uz.gita.noteapp.presentation.viewmodel.task.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.common.TaskData
import uz.gita.noteapp.domain.usecase.task.AddTaskUseCase
import uz.gita.noteapp.presentation.viewmodel.task.AddTaskViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModelImpl @Inject constructor(
    private val useCase: AddTaskUseCase
) : ViewModel(), AddTaskViewModel {
    override val taskAddedLiveData = MutableLiveData<Unit>()
    override val taskDeletedLiveData = MutableLiveData<Unit>()
    override val taskUpdatedLiveData = MutableLiveData<Unit>()

    override fun addTask(task: TaskData) {
        useCase.insertTask(task).onEach {
            taskAddedLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun deleteTask(task: TaskData) {
        useCase.deleteTask(task).onEach {
            taskDeletedLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun updateTask(task: TaskData) {
        useCase.updateTask(task).onEach {

        }.launchIn(viewModelScope)
    }


}