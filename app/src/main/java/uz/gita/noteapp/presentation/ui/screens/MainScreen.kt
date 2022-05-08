package uz.gita.noteapp.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentMainScreenBinding
import uz.gita.noteapp.presentation.ui.adapter.MainViewPageAdapter
import uz.gita.noteapp.presentation.viewmodel.MainViewModel
import uz.gita.noteapp.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main_screen) {
    private val binding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private lateinit var adapter :MainViewPageAdapter

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MainViewPageAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        binding.addButton.setOnClickListener { viewModel.openNextScreen() }
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomNote -> viewModel.selectPagePosition(0)
                R.id.bottomTask -> viewModel.selectPagePosition(1)
            }
            return@setOnItemSelectedListener true
        }

        viewModel.selectPagePositionLiveData.observe(viewLifecycleOwner, selectPagePositionObserver)
        viewModel.openAddNoteScreenLiveData.observe(this@MainScreen, openAddNoteScreenObserver)
        viewModel.openAddTaskScreenLiveData.observe(this@MainScreen, openAddTaskScreenObserver)

    }

    private val selectPagePositionObserver = Observer<Int> { binding.viewPager.currentItem = it }
    private val openAddNoteScreenObserver = Observer<Unit> { findNavController().navigate(R.id.addNoteScreen) }
    private val openAddTaskScreenObserver = Observer<Unit> { findNavController().navigate(R.id.addTaskScreen) }
}