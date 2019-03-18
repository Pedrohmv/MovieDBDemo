package br.com.pedrohmv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.pedrohmv.R
import br.com.pedrohmv.ui.genero.GeneroViewPagerAdapter
import br.com.pedrohmv.util.inflate
import kotlinx.android.synthetic.main.fragment_tabbed.*

class TabbedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_tabbed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = GeneroViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}