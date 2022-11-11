package uz.gita.noteapp_slp.data.model.common

data class TagData0(
    val tag: String
) {
    val toTagData: TagData get() = TagData(tag, false)
}