package com.example.madlevel4task2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.madlevel4task2.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        navController = findNavController( R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(destination.id in arrayOf(R.id.rockPaperFragment)){
                menu.findItem(R.id.action_settings).isVisible = true
                menu.findItem(R.id.action_delete_all).isVisible = false
                supportActionBar?.title = "Mad Level 4 task 2"
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
            }else{
                menu.findItem(R.id.action_settings).isVisible = false
                menu.findItem(R.id.action_delete_all).isVisible = true
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
                supportActionBar?.title = "Your Game History"
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                navController.navigate(R.id.action_rockPaperFragment_to_historyFragment)
                true
            }
            android.R.id.home -> {
                navController.navigate(R.id.action_historyFragment_to_rockPaperFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}