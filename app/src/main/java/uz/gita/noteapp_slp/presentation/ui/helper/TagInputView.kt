package uz.gita.noteapp_slp.presentation.ui.helper

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout
import uz.gita.noteapp_slp.R


class TagInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    val chipGroup: ChipGroup
    val inputLayout: TextInputLayout
    private val cont = context

    init {
        LayoutInflater.from(context).inflate(R.layout.tags_input_layout, this, true)

        inputLayout = findViewById(R.id.i_input_v)
        val editText = inputLayout.editText!!
        chipGroup = findViewById(R.id.i_flex_box)

        editText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (editText.text.toString() == " "){
                    editText.text.clear()
                }
            } else {
                if (editText.text.isNullOrEmpty() && chipGroup.childCount > 0) {
                    editText.setText(" ")
                }
            }
        }

        editText.setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN && editText.text.isEmpty()) {
//                onBackspacePressed, also edittext is empty
                if (chipGroup.childCount <= 0) return@setOnKeyListener false
                val lastChip = chipGroup.getChildAt(chipGroup.childCount - 1) as Chip
                editText.append(lastChip.text)
                chipGroup.removeView(lastChip)
            }
            false
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val text = editable.toString()
                if (text.length > 26) Toast.makeText(cont, "Tag length limited!", Toast.LENGTH_SHORT).show()

                if (text.isNotEmpty() && text.endsWith(" ") || text.endsWith("\n")) {
                    if (text.trim().length > 0) {
                        addNewChip(text.trim())
                        editable.clear()
                    }
                }
            }
        })
    }

    fun addNewChip(text: String) {
        if (chipGroup.childCount < 10) {
            val newChip = LayoutInflater.from(context).inflate(R.layout.chip, chipGroup, false) as Chip
            newChip.id = ViewCompat.generateViewId()
            newChip.text = text
            newChip.setOnCloseIconClickListener {
                chipGroup.removeView(newChip)
            }
            chipGroup.addView(newChip)
        } else {
            Toast.makeText(cont, "Limited over 10 tags!", Toast.LENGTH_SHORT).show()
        }
    }

}