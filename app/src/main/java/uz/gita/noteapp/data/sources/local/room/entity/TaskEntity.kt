package uz.gita.noteapp.data.sources.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.gita.noteapp.data.model.common.ChildTask
import uz.gita.noteapp.data.model.common.TaskData
import java.lang.reflect.Type

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val childTask: String = "",
    val createTime: Long,
    val isPinned: Boolean,
    val isDeleted: Boolean
)
fun TaskEntity.getTaskData(): TaskData {
    val type: Type = object: TypeToken<List<ChildTask>>() {}.type
    val childTaskList = Gson().fromJson(childTask, type) as List<ChildTask>?

    return TaskData(this.id, this.title, childTaskList ?: emptyList(), this.createTime, this.isPinned, this.isDeleted)
}
