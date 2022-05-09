package uz.gita.noteapp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.common.NoteData

interface NoteViewModel {
    val allNoteLiveData: LiveData<List<NoteData>>
}