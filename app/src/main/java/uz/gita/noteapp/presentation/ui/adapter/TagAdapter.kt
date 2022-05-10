package uz.gita.noteapp.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.databinding.ItemNoteBinding
import uz.gita.noteapp.databinding.TagLayoutBinding

class TagAdapter : ListAdapter<TagData, TagAdapter.NoteViewHolder>(TagDiffUtil) {

    private var noteListener: ((TagData) -> Unit)? = null

    object TagDiffUtil : DiffUtil.ItemCallback<TagData>() {
        override fun areItemsTheSame(oldItem: TagData, newItem: TagData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TagData, newItem: TagData): Boolean = oldItem == newItem
    }

    inner class NoteViewHolder(private val binding: TagLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.chipText.setOnClickListener { noteListener?.invoke(currentList[absoluteAdapterPosition]) }
        }

        fun bind() {
            binding.chipText.text = currentList[absoluteAdapterPosition].tag
            Log.d("TTT", "bind")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.NoteViewHolder =
        NoteViewHolder(TagLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind()
        Log.d("TTT", "onBindViewHolder")
    }

    fun setNoteListener(block: ((TagData) -> Unit)) {
        noteListener = block
    }
}