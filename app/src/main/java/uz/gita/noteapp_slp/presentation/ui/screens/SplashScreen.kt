package uz.gita.noteapp_slp.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp_slp.R
import uz.gita.noteapp_slp.databinding.FragmentSplashScreenBinding
import uz.gita.noteapp_slp.presentation.viewmodel.splash.SplashViewModel
import uz.gita.noteapp_slp.presentation.viewmodel.splash.impl.SplashViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.fragment_splash_screen) {
    private val binding by viewBinding(FragmentSplashScreenBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreenLiveData.observe(this@SplashScreen, openNextScreenObserver)
    }

    private val openNextScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_splashScreen_to_mainScreen) }
}