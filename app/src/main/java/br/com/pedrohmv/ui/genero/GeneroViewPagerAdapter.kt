package br.com.pedrohmv.ui.genero

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GeneroViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

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

    private fun createFragment(position: Int): GeneroFragment {
        val genreId = when(position){
            0 -> 28
            1 -> 18
            2 -> 14
            else -> 878
        }

        return GeneroFragment(genreId)
    }
}