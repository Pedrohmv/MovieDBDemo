package br.com.pedrohmv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import br.com.pedrohmv.R
import br.com.pedrohmv.ui.genero.GeneroViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar?.title = "Movie"
        toolbar.title = "Movie"
        setSupportActionBar(toolbar)

        viewPager.adapter = GeneroViewPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchMenuItem = menu.findItem(R.id.searchView)
        val searchView = searchMenuItem.actionView
//        searchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
//            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
//                supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.white)))
//                val a = searchView
//                return true
//            }
//
//            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
//                supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
//                return true
//            }
//
//        })

        return true
    }

}
