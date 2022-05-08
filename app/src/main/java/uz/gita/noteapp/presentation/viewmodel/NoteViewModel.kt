package uz.gita.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface NoteViewModel {
    val noteLiveData: LiveData<List<NoteData>>
}