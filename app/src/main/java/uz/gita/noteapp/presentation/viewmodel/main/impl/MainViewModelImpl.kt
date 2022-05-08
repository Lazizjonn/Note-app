package uz.gita.noteapp.presentation.viewmodel.main.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.noteapp.presentation.viewmodel.main.MainViewModel

@HiltViewModel
class MainViewModelImpl : ViewModel(), MainViewModel {
    override val selectPagePositionLiveData = MutableLiveData<Int>()
    override val openAddNoteScreenLiveData = MutableLiveData<Unit>()
    override val openAddTaskScreenLiveData = MutableLiveData<Unit>()
    private var position = 0

    override fun selectPagePosition(pos: Int) {
        if (pos == position) return
        position = pos
        selectPagePositionLiveData.value = pos
    }

    override fun openNextScreen() {
        if (position == 0) openAddNoteScreenLiveData.value = Unit
        else openAddTaskScreenLiveData.value = Unit
    }
}