package uz.gita.noteapp_slp.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp_slp.R
import uz.gita.noteapp_slp.data.model.common.TaskData

class TaskAdapter: ListAdapter<TaskData, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {

    private var taskListener: ((TaskData) -> Unit)? = null

    object TaskDiffUtil : DiffUtil.ItemCallback<TaskData>() {
        override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData): Boolean = oldItem == newItem
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var text: TextView
        init {
            image = view.findViewById(R.id.image)
            text = view.findViewById(R.id.textView)
            view.findViewById<LinearLayout>(R.id.note_linear).setOnClickListener {
                taskListener?.invoke(currentList[absoluteAdapterPosition]) }
        }

        fun bind() {
            text.text = currentList[absoluteAdapterPosition].title
            Log.d("TTT", "bind")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder =
        TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind()
        Log.d("TTT", "onBindViewHolder")
    }

    fun setTaskListener(block: ((TaskData) -> Unit)) {
        taskListener = block
    }


}