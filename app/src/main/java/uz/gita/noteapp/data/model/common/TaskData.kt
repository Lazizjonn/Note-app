package uz.gita.noteapp.data.model.common

import com.google.gson.Gson
import uz.gita.noteapp.data.sources.local.room.entity.TaskEntity
import java.io.Serializable

data class TaskData(
    val id: Int,
    val title: String,
    val childTasks: List<ChildTask> = emptyList(),
    val createTime: Long,
    val isPinned: Boolean,
    val isDeleted: Boolean
): Serializable {

    fun toTaskEntity(): TaskEntity {
        val childTaskJson = Gson().toJson(childTasks)
        return TaskEntity(id, title, childTaskJson, createTime, isPinned, isDeleted)
    }
}