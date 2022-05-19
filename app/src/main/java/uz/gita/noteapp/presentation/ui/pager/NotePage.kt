package uz.gita.noteapp.presentation.ui.pager

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData
import uz.gita.noteapp.data.model.common.TagData0
import uz.gita.noteapp.databinding.FragmentPageNoteBinding
import uz.gita.noteapp.presentation.ui.adapter.NoteAdapter
import uz.gita.noteapp.presentation.ui.adapter.TagAdapter
import uz.gita.noteapp.presentation.viewmodel.note.NoteViewModel
import uz.gita.noteapp.presentation.viewmodel.note.impl.NoteViewModelImpl

@AndroidEntryPoint
class NotePage : Fragment(R.layout.fragment_page_note) {
    private val binding by viewBinding(FragmentPageNoteBinding::bind)
    private val viewModel: NoteViewModel by viewModels<NoteViewModelImpl>()
    private val noteAdapter = NoteAdapter()
    private val tagAdapter by lazy { TagAdapter(requireContext()) }
    private var allTags = ArrayList<TagData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        liveDatas()
        clicks()
        adapters()

    }

    private fun adapters() {
        binding.noteRecyclerview.adapter = noteAdapter
        binding.noteRecyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false )

        binding.tagRecycle.adapter = tagAdapter
        binding.tagRecycle.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.HORIZONTAL)

    }

    private fun liveDatas() {
        viewModel.allNoteLiveData.observe(viewLifecycleOwner, noteObserver)
//        viewModel.tagListLiveData.observe(viewLifecycleOwner, tagListObserver)
        viewModel.tagFilterLiveData.observe(viewLifecycleOwner, tagFilterObserver)
    }

    private fun clicks() {
        noteAdapter.setNoteListener {
            val bundle = bundleOf("note" to it)
            Log.d("TAG", "noteScreen, bundle:"+ bundle.size())
            findNavController().navigate(R.id.addNoteScreen, bundle)
        }

        tagAdapter.setNoteListener{
            viewModel.tagFilter(it)
            Toast.makeText(requireContext(), it.tag, Toast.LENGTH_SHORT).show()
        }
    }

    private val noteObserver = Observer<Pair<Boolean, List<NoteData>>> { noteData ->
        if (!noteData.first) {
            val A = ArrayList<TagData0>()
            noteData.second.forEach { A.addAll(it.tag.map{ item-> TagData0(item) }) }
            allTags.clear()
            A.distinct().onEach { allTags.add(it.toTagData) }
            tagAdapter.submitList(allTags)
        }
        noteAdapter.submitList(noteData.second)

    }

    private val tagFilterObserver = Observer<TagData> { changed ->
        val temp: ArrayList<TagData> = ArrayList()
        for (old in allTags) {
            if (old.tag == changed.tag ) {
                temp.add(changed)
            } else {
                temp.add(old)
            }
        }
        tagAdapter.submitList(temp)
    }

    private val tagListObserver = Observer<List<TagData>> {
//        val array = ArrayList<TagData>()
//        array.addAll(it)
//        array.addAll(arrayOf(TagData(1, "android"), TagData(2, "university"),  TagData(3, "universit"), TagData(4, "universityyscascvscv"),  TagData(5, "university")))
//        Log.d("TTT", "NotePage, tagListSize: ${it.size} ")
//        tagAdapter.submitList(it)
    }

    override fun onResume() {
        super.onResume()
        allTags.clear()
        viewModel.load()
    }
}