package com.resurrection.appbase.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.base.component.BiometricManager
import com.resurrection.base.core.BaseActivity
import com.resurrection.base.general.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(
    R.layout.activity_main,
    MainActivityViewModel::class.java) {




    @RequiresApi(Build.VERSION_CODES.N)
    override fun init(savedInstanceState: Bundle?) {
/*
        val fragment = PhotosFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(binding.frameLayout.id, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
*/


        biometricManager.setup(
            "my title",
            "my sub title",
            "my desc",
            biometricIsAvailable = {

            },biometricIsUnAvailable = {

            },success = {
                toast("succes")
            },error = {
                toast("errorr")
        })

        biometricManager.authenticate()


        val dialog = AlertDialog.Builder(this)
            .setTitle("network connection")
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton("try again", null)
            .setCancelable(false)
            .create()

        dialog.setOnShowListener {

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                finish()
            }

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
                if (networkManager.isNetworkAvailable) dialog.dismiss()
            }

        }
        networkManager.setNetworkStateListener(
            available = {
                dialog.dismiss()
            },
            unAvailable = {
                dialog.show()
            }
        )



    }

}

