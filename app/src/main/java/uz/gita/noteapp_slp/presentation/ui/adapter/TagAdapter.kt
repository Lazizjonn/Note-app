package uz.gita.noteapp_slp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp_slp.R
import uz.gita.noteapp_slp.data.model.common.TagData
import uz.gita.noteapp_slp.databinding.TagLayoutBinding

class TagAdapter(private val context: Context) : ListAdapter<TagData, TagAdapter.NoteViewHolder>(TagDiffUtil) {

    private var noteListener: ((TagData) -> Unit)? = null

    object TagDiffUtil : DiffUtil.ItemCallback<TagData>() {
        override fun areItemsTheSame(oldItem: TagData, newItem: TagData): Boolean = oldItem.tag == newItem.tag
        override fun areContentsTheSame(oldItem: TagData, newItem: TagData): Boolean = oldItem == newItem
     }

    inner class NoteViewHolder(private val binding: TagLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.chipText.setOnClickListener { noteListener?.invoke(getItem(absoluteAdapterPosition)) }
        }

        fun bind() {
            binding.chipText.text = getItem(absoluteAdapterPosition).tag

            if (getItem(absoluteAdapterPosition).isChosen) {  binding.chipText.setBackground(ContextCompat.getDrawable(context, R.drawable.chosen_tag_background)) }
            else {  binding.chipText.setBackground(ContextCompat.getDrawable(context, R.drawable.tag_background)) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.NoteViewHolder =
        NoteViewHolder(TagLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind()
    }

    fun setNoteListener(block: ((TagData) -> Unit)) {
        noteListener = block
    }

    override fun submitList(list: List<TagData>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

}