package uz.gita.noteapp.data.sources.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteapp.data.model.common.TagData

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val id: Int,
    val tag: String
)