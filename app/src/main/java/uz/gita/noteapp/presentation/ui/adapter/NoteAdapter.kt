package uz.gita.noteapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.databinding.ItemNoteBinding

class NoteAdapter(private val list: List<NoteData>) : ListAdapter<NoteData, NoteAdapter.NoteViewHolder>(NoteDiffUtil) {

    private var noteListener: ((NoteData) -> Unit)? = null

    object NoteDiffUtil : DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean = oldItem == newItem
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.noteLinear.setOnClickListener {
                noteListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() {
            binding.noteText.text = list[absoluteAdapterPosition].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.bind()

    fun setNoteListener(block: ((NoteData) -> Unit)) {
        noteListener = block
    }
}