package uz.gita.noteapp.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.TaskData
import uz.gita.noteapp.domain.usecase.TaskUseCase
import uz.gita.noteapp.presentation.viewmodel.TaskViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModelImpl @Inject constructor(
    private val useCase: TaskUseCase
) : ViewModel(), TaskViewModel {

    override val taskLiveData = MutableLiveData<List<TaskData>>()

    init {
        load()
    }

    private fun load() {
        useCase.getAllTasks().onEach {
            taskLiveData.value = it
            Log.d("TTT", "loaded : ${it.size}")
        }.launchIn(viewModelScope)
    }

}