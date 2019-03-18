package br.com.pedrohmv.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.pedrohmv.R
import br.com.pedrohmv.ui.genero.GeneroFragment
import br.com.pedrohmv.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = "Movie"
        setSupportActionBar(toolbar)
        replaceFragmentInActivity(R.id.contentFrame, TabbedFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchMenuItem = menu.findItem(R.id.searchView)
        searchView = searchMenuItem.actionView as SearchView

        searchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                replaceFragmentInActivity(R.id.contentFrame, GeneroFragment().apply {
                    arguments = Bundle()
                })
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                replaceFragmentInActivity(R.id.contentFrame, TabbedFragment())
                return true
            }

        })

        return true
    }

}
