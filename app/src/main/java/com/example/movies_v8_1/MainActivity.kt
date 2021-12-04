package com.example.movies_v8_1

import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.movies_v8_1.databinding.ActivityMainBinding
import com.example.movies_v8_1.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            shareAppWithFriends()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_favourites
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun shareAppWithFriends(){
        Invitation().inviteViaEmail(this)
    }

    override fun onBackPressed() {
        if(findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.nav_home)
            LeaveDialog().show(supportFragmentManager, "dialog")
        else
            super.onBackPressed()
    }

    class LeaveDialog: DialogFragment(){
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog.Builder(requireContext())
                .setTitle(R.string.leave_dialog_title)
                .setMessage(R.string.leave_dialog_message)
                .setNegativeButton("No") {
                        dialog, which -> requireActivity().finish()
                }
                .setNeutralButton("Maybe later"){
                        dialog, which -> requireActivity().finish()
                }
                .setPositiveButton("Ok"){
                        dialog, which -> Toast.makeText(requireContext(), "OK", Toast.LENGTH_LONG).show()
                }
                .setCancelable(false)
                .create()
        }
    }
}