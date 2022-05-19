package uz.gita.noteapp.data.model.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.gita.noteapp.data.sources.local.room.entity.NoteEntity
import java.io.Serializable
import java.lang.reflect.Type

data class NoteData(
    val id: Int,
    val title: String,
    val note: String,
    val tag: List<String>,
    val createTime: Long,
    val isPinned: Boolean,
    val isDeleted: Boolean
): Serializable {

    fun toNoteEntity(): NoteEntity {
        val tagJson: String = Gson().toJson(tag)
        return NoteEntity(id, title, note, tagJson, createTime, isPinned, isDeleted)
    }
}