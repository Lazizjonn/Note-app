package uz.gita.noteapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.data.model.TaskData
import uz.gita.noteapp.databinding.ItemTaskBinding

class TaskAdapter(private val list: List<TaskData>) : ListAdapter<TaskData, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {

    private var taskListener: ((TaskData) -> Unit)? = null

    object TaskDiffUtil : DiffUtil.ItemCallback<TaskData>() {
        override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData): Boolean = oldItem == newItem
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            taskListener?.invoke(list[absoluteAdapterPosition])
        }

        fun bind() {
            binding.taskText.text = list[absoluteAdapterPosition].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind()

    fun setTaskListener(block: (TaskData) -> Unit) {
        taskListener = block
    }
}