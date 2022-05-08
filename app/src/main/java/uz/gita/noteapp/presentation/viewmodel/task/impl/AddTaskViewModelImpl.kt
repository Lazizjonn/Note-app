package uz.gita.noteapp.presentation.viewmodel.task.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.noteapp.domain.usecase.task.AddTaskUseCase
import uz.gita.noteapp.presentation.viewmodel.task.AddTaskViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModelImpl @Inject constructor(
    private val useCase: AddTaskUseCase
) : ViewModel(), AddTaskViewModel {

}