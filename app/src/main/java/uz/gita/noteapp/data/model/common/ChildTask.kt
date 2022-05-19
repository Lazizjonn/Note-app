package uz.gita.noteapp.data.model.common

import java.io.Serializable

data class ChildTask(
    val id: Int,
    var title: String,
    var isDone: Boolean
): Serializable
