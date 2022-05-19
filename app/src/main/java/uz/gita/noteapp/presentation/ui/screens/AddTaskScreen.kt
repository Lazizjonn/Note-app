package uz.gita.noteapp.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.ChildTask
import uz.gita.noteapp.data.model.common.TaskData
import uz.gita.noteapp.databinding.FragmentAddTaskScreenBinding
import uz.gita.noteapp.presentation.ui.adapter.ChildTaskAdapter
import uz.gita.noteapp.presentation.viewmodel.task.AddTaskViewModel
import uz.gita.noteapp.presentation.viewmodel.task.impl.AddTaskViewModelImpl
import uz.gita.noteapp.utils.popUp

@AndroidEntryPoint
class AddTaskScreen : Fragment(R.layout.fragment_add_task_screen) {
    private val binding by viewBinding(FragmentAddTaskScreenBinding::bind)
    private val viewModel: AddTaskViewModel by viewModels<AddTaskViewModelImpl>()
    private var argTaskData: TaskData? = null
    private var argTaskSize = 4
    private val childTasklist = ArrayList<ChildTask>()
    private val adapter by lazy { ChildTaskAdapter(requireContext(), argTaskData != null) }
    private var taskId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d("TAG", "onCreate: " + argTaskData.toString() + argTaskSize)
//        argTaskData = savedInstanceState?.getSerializable("my_task") as TaskData?
//        argTaskSize = savedInstanceState?.getInt("listSize") ?: 0
//        Log.d("TAG", "onCreate: " + argTaskData.toString() + argTaskSize)
        argTaskData = arguments?.getSerializable("my_task") as TaskData?
        taskId = argTaskData?.id ?: 0
        Log.d("TAG", "onCreate: " + argTaskData.toString() + argTaskSize)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapters()
        setView()
        liveDatas()
        clicks()


    }

    private fun clicks() {
        binding.add.setOnClickListener {
            childTasklist.add(ChildTask(childTasklist.size, "", false))
            adapter.submitList(childTasklist.toMutableList())
        }

        binding.addTaskBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter.setCancelTaskListener {
            childTasklist.remove(it)
            adapter.submitList(childTasklist.toMutableList())
        }

        adapter.setChildTaskListener { new ->
            childTasklist.map{ old ->
                if (new == old) new
            }
            adapter.submitList(childTasklist.toMutableList())
        }

        binding.saveTaskBtn.setOnClickListener {
            // save
            val newTask = TaskData(
                taskId,
                binding.addTaskTitle.text.toString(),
                adapter.currentList,
                argTaskData?.createTime ?: System.currentTimeMillis(),
                argTaskData?.isPinned ?: false,
                argTaskData?.isDeleted ?: false
            )
            viewModel.addTask(newTask)
        }

        binding.moreMenu.setOnClickListener {
            val menu = popUp(requireContext(), R.menu.popup_menu, it)

            menu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit -> {
                        // editable
                        setEditeble()
                        true
                    }
                    R.id.delete -> {
                        // delete
                        if (taskId > 0){
                            val deletingTask = TaskData(
                                taskId,
                                binding.addTaskTitle.text.toString(),
                                adapter.currentList,
                                argTaskData?.createTime ?: System.currentTimeMillis(),
                                argTaskData?.isPinned ?: false,
                                argTaskData?.isDeleted ?: false
                            )
                            viewModel.deleteTask(deletingTask)
                        } else Toast.makeText(requireContext(), "You cannot delete", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun setEditeble() {
        binding.addTaskTitle.isEnabled = true
        adapter.enableEditText()
    }

    private fun liveDatas() {
        viewModel.taskAddedLiveData.observe(this, taskAddedObserver)
        viewModel.taskUpdatedLiveData.observe(this, taskUpdatedObserver)
        viewModel.taskDeletedLiveData.observe(this, taskDeletedObserver)
    }

    private fun setAdapters() {
        binding.taskRecyclerview.adapter = adapter
        binding.taskRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setView() {
        if (argTaskData != null){
            binding.addTaskTitle.setText(argTaskData!!.title)
            binding.addTaskTitle.isEnabled = false
            childTasklist.addAll(argTaskData!!.childTasks)
            adapter.submitList(argTaskData!!.childTasks)
        }
    }

    private val taskAddedObserver = Observer<Unit>{
        findNavController().navigateUp()
    }
    private val taskUpdatedObserver = Observer<Unit>{
        findNavController().navigateUp()
    }
    private val taskDeletedObserver = Observer<Unit>{
        findNavController().navigateUp()
    }

}