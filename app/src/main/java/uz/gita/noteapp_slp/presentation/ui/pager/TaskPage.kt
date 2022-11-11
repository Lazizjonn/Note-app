package uz.gita.noteapp_slp.presentation.ui.pager

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp_slp.R
import uz.gita.noteapp_slp.data.model.common.TaskData
import uz.gita.noteapp_slp.databinding.FragmentPageTaskBinding
import uz.gita.noteapp_slp.presentation.ui.adapter.TaskAdapter
import uz.gita.noteapp_slp.presentation.viewmodel.task.TaskViewModel
import uz.gita.noteapp_slp.presentation.viewmodel.task.impl.TaskViewModelImpl

@AndroidEntryPoint
class TaskPage : Fragment(R.layout.fragment_page_task) {
    private val binding by viewBinding(FragmentPageTaskBinding::bind)
    private val viewModel: TaskViewModel by viewModels<TaskViewModelImpl>()
    private val taskList = ArrayList<TaskData>()
    private val adapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setAdapter()


        viewModel.allTaskLiveData.observe(viewLifecycleOwner, taskObserver)


    }

    private fun setAdapter() {
        binding.taskRecyclerview.isVisible = false
        binding.taskRecyclerview.adapter = adapter
        binding.taskRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    private val taskObserver = Observer<List<TaskData>> { taskData ->
        if (taskData.size> 0) {
            binding.taskRecyclerview.isVisible = true
            binding.animView.isVisible = false
            taskList.clear()
            taskList.addAll(taskData)
            adapter.submitList(taskList.toMutableList())
        } else {
            binding.taskRecyclerview.isVisible = false
            binding.animView.isVisible = true
        }

        adapter.setTaskListener {
            /*val bundle = Bundle()
            Log.d("TAG", "taskData___: " + it.toString())
            bundle.putSerializable("my_task", TaskData(it.id, it.title, it.childTasks, it.createTime, it.isPinned, it.isDeleted))
            bundle.putInt( "listSize", taskList.size)*/

            val bundle = bundleOf("my_task" to it)
            findNavController().navigate(R.id.addTaskScreen, bundle)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTaskList()
    }
}