package uz.gita.noteapp_slp.data.model.common

data class TagData(
    val tag: String,
    var isChosen: Boolean = false
){
    val toTagData0: TagData0 get() = TagData0(tag)
}