package uz.gita.noteapp.presentation.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.ChildTask
import uz.gita.noteapp.databinding.ItemTaskBinding

class ChildTaskAdapter(private val context: Context, private var editMode: Boolean) : ListAdapter<ChildTask, ChildTaskAdapter.NoteViewHolder>(TagDiffUtil) {

    private var taskListener: ((ChildTask) -> Unit)? = null
    private var cancelListener: ((ChildTask) -> Unit)? = null
    private var _binding: ItemTaskBinding? = null

    object TagDiffUtil : DiffUtil.ItemCallback<ChildTask>() {
        override fun areItemsTheSame(oldItem: ChildTask, newItem: ChildTask): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ChildTask, newItem: ChildTask): Boolean = oldItem == newItem
     }

    inner class NoteViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            _binding = binding
            binding.taskIsCheck.setOnClickListener {
                currentList[absoluteAdapterPosition].isDone = binding.taskIsCheck.isChecked
                taskListener?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.cancel.setOnClickListener {
                cancelListener?.invoke(getItem(absoluteAdapterPosition))
            }

            binding.title.doOnTextChanged { text, start, before, count ->
                currentList[absoluteAdapterPosition].title = text.toString()
            }
        }

        fun bind() = with(absoluteAdapterPosition) {
            if (editMode) { disableEditText(binding) }
            else { enableEditText() }

            binding.taskIsCheck.isChecked = getItem(this).isDone
            binding.title.setText(getItem(this).title)

            if (getItem(this).isDone) {
                binding.title.paintFlags = binding.title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.title.setTextColor(ContextCompat.getColor(context, R.color.kulrang))
            }
            else {
                binding.title.paintFlags = binding.title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                binding.title.setTextColor(ContextCompat.getColor(context, R.color.black))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildTaskAdapter.NoteViewHolder =
        NoteViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind()
    }

    fun setChildTaskListener(block: ((ChildTask) -> Unit)) {
        taskListener = block
    }
    fun setCancelTaskListener(block: ((ChildTask) -> Unit)) {
        cancelListener = block
    }

    override fun submitList(list: List<ChildTask>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    fun disableEditText(binding: ItemTaskBinding) {
        binding.title.setTextColor(ContextCompat.getColor(context, R.color.kulrang))
        binding.title.isEnabled = false
        binding.taskIsCheck.isEnabled = false
    }

    fun enableEditText() {
        _binding!!.title.setTextColor(ContextCompat.getColor(context, R.color.black))
        _binding!!.title.isEnabled = true
        _binding!!.taskIsCheck.isEnabled = true
    }



}