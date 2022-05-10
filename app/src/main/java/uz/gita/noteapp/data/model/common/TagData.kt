package uz.gita.noteapp.data.model.common

import androidx.room.PrimaryKey
import uz.gita.noteapp.data.sources.local.room.entity.TagEntity

data class TagData(
    @PrimaryKey
    val id: Int,
    val tag: String
){
    fun toTagEntity(): TagEntity = TagEntity(id, tag)
}
