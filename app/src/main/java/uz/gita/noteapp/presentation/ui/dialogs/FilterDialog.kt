package uz.gita.noteapp.presentation.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.noteapp.R
import uz.gita.noteapp.presentation.ui.adapter.TagAdapter

class FilterDialog (private val context2: Context) : BottomSheetDialogFragment() {
    val tagAdapter by lazy { TagAdapter(context2) }
    private var tagRecycleView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.filter_dialog, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tagRecycleView = view.findViewById(R.id.tag_recycle_filter)

        tagRecycleView!!.adapter = tagAdapter
        tagRecycleView!!.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.HORIZONTAL)
    }
}