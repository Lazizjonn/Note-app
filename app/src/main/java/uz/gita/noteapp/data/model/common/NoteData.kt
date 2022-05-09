package uz.gita.noteapp.data.model.common

data class NoteData(
    val id: Int,
    val title: String,
    val note: String,
    val tag: List<String>,
    val createTime: Long,
    val isPinned: Boolean,
    val isDeleted: Boolean
)