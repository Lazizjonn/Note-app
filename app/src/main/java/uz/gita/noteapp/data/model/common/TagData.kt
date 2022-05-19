package uz.gita.noteapp.data.model.common

import androidx.room.PrimaryKey
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity

data class TagData(
    val tag: String,
    var isChosen: Boolean = false
){
    val toTagData0: TagData0 get() = TagData0(tag)
}