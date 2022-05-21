package uz.gita.noteapp.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.*
import uz.gita.noteapp.databinding.FragmentAddNoteScreenBinding
import uz.gita.noteapp.presentation.viewmodel.note.AddNoteViewModel
import uz.gita.noteapp.presentation.viewmodel.note.impl.AddNoteViewModelImpl
import uz.gita.noteapp.utils.hideKeyboard
import uz.gita.noteapp.utils.popUp

@AndroidEntryPoint
class AddNoteScreen : Fragment(R.layout.fragment_add_note_screen) {
    private val binding by viewBinding(FragmentAddNoteScreenBinding::bind)
    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private var noteArgument: NoteData? = null
    private lateinit var newTags: List<String>
    private var noteId = 0
    private val oldTags: ArrayList<TagData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteArgument = arguments?.get("note") as NoteData?
        noteId = noteArgument?.id ?: 0
        newTags = noteArgument?.tag ?: emptyList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        livData()
        setView()
        clicks()
        Aztec.with(binding.noteInputView, binding.formattingToolbar, IAztecToolbarClickListenerImpl())
    }

    private fun livData() {
        viewModel.noteAddedLiveData.observe(this, noteAddedObserver)
        viewModel.noteDeletedLiveData.observe(this, noteDeletedObserver)
        viewModel.tagDeletedLiveData.observe(this, tagDeletedObserver)
        viewModel.tagInsertedLiveData.observe(this, tagInsertedObserver)
        viewModel.tagListLiveData.observe(this, tagListLiveData)
    }

    private fun clicks() = with(binding) {
        addNoteBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveNoteBtn.setOnClickListener {
            onSaveNoteClick()
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
                        if (noteId > 0) with(noteArgument!!){
                            val deletingNote = NoteData(id = this.id, title = title, note = note, tag = newTags, createTime = this.createTime, this.isPinned, this.isDeleted)
                            viewModel.deleteNote(deletingNote)
                        } else Toast.makeText(requireContext(), "You cannot delete", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        false
                    }
                }
//                } else {
//                    false
//                }
            }
        }

    }

    private fun setEditeble() {
        binding.addNoteTitle.isEnabled = true
        binding.noteInputView.isEnabled = true
        binding.tagInputView.isEnabled = true

        binding.addNoteTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.noteInputView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.tagInputView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }

    private fun setView() = with(binding){
        noteArgument?.let {
            Log.d("TAG", "notes: " + it.title)
            addNoteTitle.fromHtml(it.title)
            addNoteTitle.isEnabled = false
            addNoteTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))

            noteInputView.fromHtml(it.note)
            noteInputView.isEnabled = false
            noteInputView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            var tagString = ""
            it.tag.map { tagString = tagString + " $it" }
            tagInputView.fromHtml(tagString.trim())
            tagInputView.isEnabled = false
            tagInputView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))


            Toast.makeText(requireContext(), "Reading mode!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun onSaveNoteClick() {
        val title = binding.addNoteTitle.toFormattedHtml()
        val text = binding.noteInputView.toFormattedHtml()
        newTags = binding.tagInputView.text.toString().trim().split(" ")
        Log.d("TAG", "addNoteScreen: newTags" + newTags.toString())
        binding.noteInputView.hideKeyboard()

        if (text.isEmpty() || title.isEmpty()) { return }

        if (noteArgument != null) with(noteArgument!!) {
            viewModel.saveNote(NoteData(id = this.id, title = title, note = text, tag = newTags, createTime = this.createTime, this.isPinned, this.isDeleted))
        } else{
            viewModel.saveNote(NoteData(id = 0, title = title, note = text, tag = newTags, createTime = System.currentTimeMillis(), false, false))
        }

    }

    private val tagListLiveData = Observer<List<TagData>>{
        oldTags.addAll(it)
    }

    private val tagDeletedObserver = Observer<Boolean>{
    }

    private val tagInsertedObserver = Observer<Boolean>{
        findNavController().navigateUp()
    }

    private val noteAddedObserver = Observer<Unit>{
        findNavController().navigateUp() // go back to home fragment
        // note added
        /*if (updateTagList()){
            viewModel.insertTags(oldTags)
        }else{
            findNavController().navigateUp() // go back to home fragment
        }*/
    }
    private val noteDeletedObserver = Observer<Unit>{
        findNavController().navigateUp() // go back to home fragment
    }


    private fun updateTagList(): Boolean{
        Log.d("TAG", "addingNewTag, old size: " + oldTags.size )
        val temp = oldTags.size
        newTags.map { new ->
            var exist = false
            oldTags.forEach { oldTag ->
                if(oldTag.tag == new){
                    exist = true
                    return@forEach
                }
            }
            if (!exist) oldTags.add(TagData(new))
        }
        Log.d("TAG", "addingNewTag, new size: " + oldTags.size )
        return temp != oldTags.size
    }

}