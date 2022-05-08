package uz.gita.noteapp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface DeletedNoteViewModel {
    val allDeletedNoteLiveData: LiveData<List<NoteData>>
}