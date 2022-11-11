package uz.gita.noteapp_slp.presentation.ui.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import uz.gita.noteapp_slp.R
import uz.gita.noteapp_slp.data.model.common.IAztecToolbarClickListenerImpl
import uz.gita.noteapp_slp.data.model.common.NoteData
import uz.gita.noteapp_slp.data.model.common.TagData
import uz.gita.noteapp_slp.databinding.FragmentAddNoteScreenBinding
import uz.gita.noteapp_slp.presentation.viewmodel.note.AddNoteViewModel
import uz.gita.noteapp_slp.presentation.viewmodel.note.impl.AddNoteViewModelImpl
import uz.gita.noteapp_slp.utils.popUp


@AndroidEntryPoint
class AddNoteScreen : Fragment(R.layout.fragment_add_note_screen) {
    private val binding by viewBinding(FragmentAddNoteScreenBinding::bind)
    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private var noteArgument: NoteData? = null
    private lateinit var newTags: ArrayList<String>
    private var noteId = 0
    private val oldTags: ArrayList<TagData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteArgument = arguments?.get("note") as NoteData?
        noteId = noteArgument?.id ?: 0
        newTags = ArrayList<String>()
        newTags.addAll(noteArgument?.tag ?: emptyList())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        livData()
        setView()
        clicks()
        Aztec.with(binding.noteInputView, binding.formattingToolbar, IAztecToolbarClickListenerImpl())
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun livData() {
        viewModel.noteAddedLiveData.observe(this@AddNoteScreen, noteAddedObserver)
        viewModel.noteDeletedLiveData.observe(this@AddNoteScreen, noteDeletedObserver)
        viewModel.tagDeletedLiveData.observe(this@AddNoteScreen, tagDeletedObserver)
        viewModel.tagInsertedLiveData.observe(this@AddNoteScreen, tagInsertedObserver)
        viewModel.tagListLiveData.observe(this@AddNoteScreen, tagListLiveData)
    }
    private fun setView() = with(binding) {
        noteArgument?.let {
            Log.d("TAG", "notes: " + it.title)
            addNoteTitle.fromHtml(it.title)
            addNoteTitle.isEnabled = false
            addNoteTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))

            noteInputView.fromHtml(it.note)
            noteInputView.isEnabled = false
            noteInputView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))

            it.tag.map { tagInputView.addNewChip(it) }
            tagInputView.inputLayout.isEnabled = false
            tagInputView.inputLayout.editText!!.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))


            Toast.makeText(requireContext(), "Reading mode!", Toast.LENGTH_SHORT).show()
        }

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

            menu.setOnMenuItemClickListener { it_ ->
                when (it_.itemId) {
                    R.id.edit -> {
                        // editable
                        setEditable()
                        true
                    }
                    R.id.delete -> {
                        // delete
                        if (noteId > 0) with(noteArgument!!) {
                            val dialog = AlertDialog.Builder(requireContext(), R.style.dialog_style)
                            dialog.setTitle("Do you want delete?")
                            dialog.setPositiveButton("Yes"){ _, which ->
                                val deletingNote = NoteData(id = this.id, title = title, note = note, tag = newTags, createTime = this.createTime, this.isPinned, this.isDeleted)
                                viewModel.deleteNote(deletingNote)
                            }.setNegativeButton("No") { _dialog, which ->
                                _dialog.dismiss()
                            }
                            dialog.create().show()

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


    private fun setEditable() {
        binding.addNoteTitle.isEnabled = true
        binding.noteInputView.isEnabled = true
        binding.tagInputView.inputLayout.isEnabled = true

        binding.addNoteTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.noteInputView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.tagInputView.inputLayout.editText!!.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }
    private fun onSaveNoteClick() {
        val title = binding.addNoteTitle.toFormattedHtml()
        val text = binding.noteInputView.toFormattedHtml()


        if (text.isEmpty() || title.isEmpty()) {
            return
        }
        val tags = ArrayList<String>()
        for (i in 0 until binding.tagInputView.chipGroup.childCount) {
            tags.add((binding.tagInputView.chipGroup.getChildAt(i) as Chip).text.toString())
            Log.d("TAG", "getChipChild: size " + tags.size)
        }

        if (noteArgument != null) with(noteArgument!!) {
            viewModel.saveNote(NoteData(id = this.id, title = title, note = text, tag = tags, createTime = this.createTime, this.isPinned, this.isDeleted))
        } else {
            viewModel.saveNote(NoteData(id = 0, title = title, note = text, tag = tags, createTime = System.currentTimeMillis(), false, false))
        }

    }
    private fun printChipsValue(chipGroup: ChipGroup) {
        for (i in 0 until chipGroup.childCount) {
            val chipObj = chipGroup.getChildAt(i) as Chip
            Log.d("Chips text :: ", chipObj.text.toString())

        }
    }


    private val tagListLiveData = Observer<List<TagData>> {
        oldTags.addAll(it)
    }
    private val tagDeletedObserver = Observer<Boolean> {
    }
    private val tagInsertedObserver = Observer<Boolean> {
        findNavController().navigateUp()
    }
    private val noteAddedObserver = Observer<Unit> {
        findNavController().navigateUp() // go back to home fragment
        // note added
        /*if (updateTagList()){
            viewModel.insertTags(oldTags)
        }else{
            findNavController().navigateUp() // go back to home fragment
        }*/
    }
    private val noteDeletedObserver = Observer<Unit> {
        findNavController().navigateUp() // go back to home fragment
    }


    private fun updateTagList(): Boolean {
        Log.d("TAG", "addingNewTag, old size: " + oldTags.size)
        val temp = oldTags.size
        newTags.map { new ->
            var exist = false
            oldTags.forEach { oldTag ->
                if (oldTag.tag == new) {
                    exist = true
                    return@forEach
                }
            }
            if (!exist) oldTags.add(TagData(new))
        }
        Log.d("TAG", "addingNewTag, new size: " + oldTags.size)
        return temp != oldTags.size
    }
    private fun addChipToGroup(txt: String, chipGroup: ChipGroup) {
        val chip = Chip(context)
        chip.text = txt
//        chip.chipIcon = ContextCompat.getDrawable(requireContext(), baseline_person_black_18)
        chip.isCloseIconEnabled = true
        chip.setChipIconTintResource(R.color.grey)

        // necessary to get single selection working
        chip.isClickable = false
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View) }
        printChipsValue(chipGroup)
    }
}