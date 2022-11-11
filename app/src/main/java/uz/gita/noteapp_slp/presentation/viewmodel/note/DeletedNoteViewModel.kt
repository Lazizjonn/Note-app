package uz.gita.noteapp_slp.presentation.viewmodel.note

import androidx.lifecycle.LiveData
import uz.gita.noteapp_slp.data.model.common.NoteData

interface DeletedNoteViewModel {
    val allDeletedNoteLiveData: LiveData<List<NoteData>>
}