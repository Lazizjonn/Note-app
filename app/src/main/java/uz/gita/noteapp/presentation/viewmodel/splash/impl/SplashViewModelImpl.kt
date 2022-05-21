package uz.gita.noteapp.presentation.viewmodel.splash.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.noteapp.presentation.viewmodel.splash.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : SplashViewModel, ViewModel() {
    override val openNextScreenLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1200)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}