package uz.gita.noteapp.data.sources.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.gita.noteapp.data.model.common.NoteData
import java.lang.reflect.Type

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

fun NoteEntity.getNoteData(): NoteData {
    val _tagList: List<String>
    if (this.tag.length > 0) {
        val type: Type = object : TypeToken<List<String>>() {}.type
        _tagList = Gson().fromJson(tag, type) as ArrayList<String>
    } else{
        _tagList = emptyList()
    }

    return NoteData(this.id, this.title, this.note, _tagList, this.createTime, this.isPinned, this.isDeleted)
}