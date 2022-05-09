package uz.gita.noteapp.data.sources.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteapp.data.model.common.NoteData

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val note: String = "",
    val tag: String = "",
    val createTime: Long = 0,
    val isPinned: Boolean = false,
    val isDeleted: Boolean = false
)

fun NoteEntity.getNoteData(): NoteData = NoteData(
    this.id, this.title, this.note, this.tag.split("#"), this.createTime, this.isPinned, this.isDeleted
)