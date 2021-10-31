package com.facundojaton.marvelcomicschallenge.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.ActivityMainBinding
import com.facundojaton.marvelcomicschallenge.utils.SessionController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            showLogoutDialog()
        }
        return true
    }

    private fun showLogoutDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle(R.string.logout_title)
            setMessage(R.string.logout_message)
            setPositiveButton(R.string.yes) { dialog, _ ->
                SessionController.logout()
                navigateToLogin()
                dialog.dismiss()
            }
            setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            setNeutralButton(R.string.exit_without_logout)
        }
        dialog.show()
    }

    private fun navigateToLogin() {
        this.findNavController(R.id.nav_host_fragment).navigateUp()
    }
}