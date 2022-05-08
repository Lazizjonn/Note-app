package uz.gita.noteapp.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.noteapp.presentation.ui.pager.NotePage
import uz.gita.noteapp.presentation.ui.pager.TaskPage

class MainViewPageAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) NotePage()
        else TaskPage()
    }

}