package com.example.mynotes

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    var doubleBackToExitOnce:Boolean = false
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration=AppBarConfiguration(setOf(R.id.homefragment,R.id.createnotesfragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

    }



    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()||super.onNavigateUp()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1){
            supportFragmentManager.popBackStackImmediate()

        }
        else{
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }



}