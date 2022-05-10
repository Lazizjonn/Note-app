package uz.gita.noteapp.presentation.ui.adapter

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
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.databinding.ItemNoteBinding
import uz.gita.noteapp.databinding.NoteItemBinding

class NoteAdapter : ListAdapter<NoteData, NoteAdapter.NoteViewHolder>(NoteDiffUtil) {

    private var noteListener: ((NoteData) -> Unit)? = null

    object NoteDiffUtil : DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean = oldItem == newItem
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var text: TextView
        init {
            image = view.findViewById(R.id.image)
            text = view.findViewById(R.id.textView)
            view.findViewById<LinearLayout>(R.id.note_linear).setOnClickListener { noteListener?.invoke(currentList[absoluteAdapterPosition]) }
        }

        fun bind() {
            text.text = currentList[absoluteAdapterPosition].title
            Log.d("TTT", "bind")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder =
        NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind()
        Log.d("TTT", "onBindViewHolder")
    }

    fun setNoteListener(block: ((NoteData) -> Unit)) {
        noteListener = block
    }
}