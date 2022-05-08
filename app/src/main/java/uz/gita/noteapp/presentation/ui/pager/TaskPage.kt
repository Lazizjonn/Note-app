package uz.gita.noteapp.presentation.ui.pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.TaskData
import uz.gita.noteapp.databinding.FragmentPageTaskBinding
import uz.gita.noteapp.presentation.ui.adapter.TaskAdapter
import uz.gita.noteapp.presentation.viewmodel.task.TaskViewModel
import uz.gita.noteapp.presentation.viewmodel.task.impl.TaskViewModelImpl

@AndroidEntryPoint
class TaskPage : Fragment(R.layout.fragment_page_task) {
    private val binding by viewBinding(FragmentPageTaskBinding::bind)
    private val viewModel: TaskViewModel by viewModels<TaskViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.allTaskLiveData.observe(viewLifecycleOwner, taskObserver)
    }

    private val taskObserver = Observer<List<TaskData>> { taskData ->
        val adapter = TaskAdapter(taskData)
        binding.taskRecyclerview.adapter = adapter
        adapter.setTaskListener {
            findNavController().navigate(R.id.addTaskScreen)
        }
    }
}