package uz.gita.noteapp.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.domain.usecase.NoteUseCase
import uz.gita.noteapp.presentation.viewmodel.NoteViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModelImpl @Inject constructor(
    private val useCase: NoteUseCase
) : ViewModel(), NoteViewModel {

    override val noteLiveData = MutableLiveData<List<NoteData>>()

    init {
        load()
    }

    private fun load() {
        useCase.getAllNotes().onEach {
            noteLiveData.value = it
            Log.d("TTT", "loaded : ${it.size}")
        }.launchIn(viewModelScope)
    }

}