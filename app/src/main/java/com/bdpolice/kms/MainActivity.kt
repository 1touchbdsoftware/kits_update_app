package com.bdpolice.kms

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.bdpolice.kms.databinding.ActivityMainBinding
import com.bdpolice.kms.utils.noInternetConnectionDialog
import com.saadahmedev.popupdialog.PopupDialog
import com.saadahmedev.popupdialog.listener.StandardDialogActionListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpNavigation()
        initView()
        noInternetConnectionDialog(
            activity = this,
            lifecycle = lifecycle
        )
    }

    private fun initView() {
        binding.apply {
            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.videoTutorial -> {
                        MaterialToolbar.isVisible = false
                        BottomNavigationView.isVisible = false
                    }

                    else -> {
                        MaterialToolbar.isVisible = true
                        BottomNavigationView.isVisible = true
                    }
                }
            }
        }
    }


    private fun setUpNavigation() {
        binding.apply {
            setSupportActionBar(MaterialToolbar)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.Profile, R.id.KitsList, R.id.Notification)
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            NavigationUI.setupWithNavController(BottomNavigationView, navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Video -> {
                navController.navigate(
                    R.id.videoTutorial, null, NavOptions.Builder()
                        .build()
                )
            }

            R.id.Logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        PopupDialog.getInstance(this)
            .standardDialogBuilder()
            .createStandardDialog()
            .setHeading(resources.getString(R.string.Logout))
            .setDescription(
               resources.getString(R.string.Logout_details)
            )
            .setIcon(R.drawable.rounded_logout_24)
            .setIconColor(R.color.carbon_grey_500)
            .build(object : StandardDialogActionListener {
                override fun onPositiveButtonClicked(dialog: Dialog) {
                    dialog.dismiss()
                }

                override fun onNegativeButtonClicked(dialog: Dialog) {
                    dialog.dismiss()
                }
            })
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}