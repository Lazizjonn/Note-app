package uz.gita.noteapp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.common.NoteData
import uz.gita.noteapp.data.model.common.TagData

interface NoteViewModel {
    val allNoteLiveData: LiveData<List<NoteData>>
    val tagListLiveData: LiveData<List<TagData>>

    fun getTags()

}