package com.portfolio.pranavelric.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.portfolio.pranavelric.R
import com.portfolio.pranavelric.utils.*
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    lateinit var networkConnection: NetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        networkConnection = NetworkConnection(application)
        setSupportActionBar(toolbar)
        setupDrawerLayout()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open_in_browser -> {

                when (navController.currentDestination?.id) {
                    R.id.home_fragment -> {
                        openInBrowser(Constants.SITE_URL)
                    }
                    R.id.stackFragment -> {
                        openInBrowser(Constants.STACK_URL)
                    }
                    R.id.youtubeFragment -> {
                        openInBrowser(Constants.YOUTUTBE_URL)
                    }
                    R.id.linkedin -> {
                        openInBrowser(Constants.LINKEDIN_URL)
                    }
                    R.id.githubFragment -> {
                        openInBrowser(Constants.GITHUB_URL)
                    }
                    R.id.instagramFragment -> {
                        openInBrowser(Constants.INSTAGRAM_URL)
                    }
                    else -> {
                        this.toast("This page cannot be opened in the browser")
                    }
                }
                return true
            }
            else -> {
                return false
            }
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true;
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer_layout)
    }

    private fun setupDrawerLayout() {

        nav_view.itemIconTintList = null;
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.share -> {
                    this@WebviewActivity.share(
                        "App link will be given here "
                    )





                    true
                }
                else -> {
                    false
                }
            }

            drawer_layout.closeDrawer(GravityCompat.START);
            NavigationUI.onNavDestinationSelected(it, navController);
            //This is for closing the drawer after acting on it

            //      true

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}