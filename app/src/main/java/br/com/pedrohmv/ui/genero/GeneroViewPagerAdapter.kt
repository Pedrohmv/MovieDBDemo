package br.com.pedrohmv.ui.genero

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GeneroViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val views = mutableMapOf<Int, View>()
    private val fragments = mutableMapOf<Int, Fragment>()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> createFragment(28)
            1 -> createFragment(18)
            2 -> createFragment(14)
            else -> createFragment(878)
        }
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return return when (position) {
            0 -> "Ação"
            1 -> "Drama"
            2 -> "Fantasia"
            else -> "Ficção"
        }
    }

    private fun createFragment(idGenero: Int) = fragments[idGenero] ?: GeneroFragment().apply {
        arguments = Bundle().apply {
            putInt("ID_GENERO", idGenero)
        }
    }

}