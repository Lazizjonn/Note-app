package uz.gita.noteapp.data.sources.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.data.model.TaskData

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val note: String,
    val tag: String,
    val createTime: Long,
    val isPinned: Boolean,
    val isDeleted: Boolean
)

fun TaskEntity.getTaskData(): TaskData = TaskData(
    this.id, this.title, this.note, this.tag.split("#"), this.createTime, this.isPinned, this.isDeleted
)
