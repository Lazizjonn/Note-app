package uz.gita.noteapp.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.databinding.ItemNoteBinding

class NoteAdapter() : ListAdapter<NoteData, NoteAdapter.NoteViewHolder>(NoteDiffUtil) {

    private var noteListener: ((NoteData) -> Unit)? = null

    object NoteDiffUtil : DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean = oldItem == newItem
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.noteLinear.setOnClickListener { noteListener?.invoke(currentList[absoluteAdapterPosition]) }
        }

        fun bind() {
            binding.noteText.text = list[absoluteAdapterPosition].title
            Log.d("TTT", "bind, "+ list[absoluteAdapterPosition].title)
            binding.noteText.text = currentList[absoluteAdapterPosition].title
            Log.d("TTT", "bind")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind()
        Log.d("TTT", "onBindViewHolder")
    }

    fun setNoteListener(block: ((NoteData) -> Unit)) {
        noteListener = block
    }
}