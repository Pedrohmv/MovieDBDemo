package br.com.pedrohmv.ui.genero

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GeneroViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = mutableMapOf<Int, GeneroFragment>()

    override fun getItem(position: Int) = createFragment(position)

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Ação"
            1 -> "Drama"
            2 -> "Fantasia"
            else -> "Ficção"
        }
    }

    private fun createFragment(position: Int) = fragments[position] ?: GeneroFragment().apply {
        arguments = Bundle().apply {
            putInt("ID_GENERO", when(position){
                0 -> 28
                1 -> 18
                2 -> 14
                else -> 878
            })
        }

        fragments[position] = this
    }

}