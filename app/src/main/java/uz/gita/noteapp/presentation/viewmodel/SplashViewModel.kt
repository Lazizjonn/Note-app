package uz.gita.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openNextScreenLiveData: LiveData<Unit>
}