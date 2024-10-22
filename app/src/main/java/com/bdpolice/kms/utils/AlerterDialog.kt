package com.bdpolice.kms.utils

import androidx.fragment.app.FragmentManager
import me.pisal.alerter.Alerter

object AlerterDialog {

    fun success(title : String, message : String, supportFragmentManager: FragmentManager){
        Alerter.success()
            .withTitle(title)
            .withMessage(message)
            .show(supportFragmentManager, "Tag")
    }

    fun alert_error(title : String, message : String, supportFragmentManager: FragmentManager){
        Alerter.error()
            .withTitle(title)
            .withMessage(message)
            .show(supportFragmentManager, "Tag")
    }

    fun info(title : String, message : String, supportFragmentManager: FragmentManager){
        Alerter.info()
            .withTitle(title)
            .withMessage(message)
            .show(supportFragmentManager, "Tag")
    }
}