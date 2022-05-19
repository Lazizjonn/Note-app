package uz.gita.noteapp.data.model.common

import androidx.room.PrimaryKey
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity

data class TagData0(
    val tag: String
) {
    val toTagData: TagData get() = TagData(tag, false)
}