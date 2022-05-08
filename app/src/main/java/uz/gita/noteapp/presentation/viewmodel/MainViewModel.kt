package uz.gita.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData

interface MainViewModel {
    val selectPagePositionLiveData: LiveData<Int>
    val openAddNoteScreenLiveData: LiveData<Unit>
    val openAddTaskScreenLiveData: LiveData<Unit>

    fun selectPagePosition(pos: Int)
    fun openNextScreen()
}