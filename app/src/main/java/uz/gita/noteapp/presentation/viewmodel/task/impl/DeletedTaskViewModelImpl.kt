package uz.gita.noteapp.presentation.viewmodel.task.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.TaskData
import uz.gita.noteapp.domain.usecase.task.DeletedTaskUseCase
import uz.gita.noteapp.presentation.viewmodel.task.DeletedTaskViewModel
import javax.inject.Inject

@HiltViewModel
class DeletedTaskViewModelImpl @Inject constructor(
    private val useCase: DeletedTaskUseCase
) : ViewModel(), DeletedTaskViewModel {

    override val allDeletedTaskLiveData = MutableLiveData<List<TaskData>>()

    init {
        load()
    }

    private fun load() {
        useCase.getAllDeletedTasks().onEach {
            allDeletedTaskLiveData.value = it
        }.launchIn(viewModelScope)
    }

}