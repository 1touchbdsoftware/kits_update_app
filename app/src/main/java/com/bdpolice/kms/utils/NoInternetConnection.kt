package com.bdpolice.kms.utils

import android.app.Activity
import androidx.lifecycle.Lifecycle
import com.bdpolice.kms.R
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum
import org.imaginativeworld.oopsnointernet.dialogs.signal.NoInternetDialogSignal

fun noInternetConnectionDialog(activity : Activity, lifecycle : Lifecycle){
    NoInternetDialogPendulum.Builder(
        activity,
        lifecycle
    ).apply {
        dialogProperties.apply {
            connectionCallback = object : ConnectionCallback { // Optional
                override fun hasActiveConnection(hasActiveConnection: Boolean) {
                    // ...
                }
            }

            cancelable = false // Optional
            noInternetConnectionTitle = activity.resources.getString(R.string.No_Internet) // Optional
            noInternetConnectionMessage =
                activity.resources.getString(R.string.Connection_check)  // Optional
            showInternetOnButtons = true // Optional
            pleaseTurnOnText = activity.resources.getString(R.string.Please_turn_on) // Optional
            wifiOnButtonText = activity.resources.getString(R.string.Wifi) // Optional
            mobileDataOnButtonText = activity.resources.getString(R.string.Mobile_data) // Optional

            onAirplaneModeTitle =  activity.resources.getString(R.string.No_Internet) // Optional
            onAirplaneModeMessage = activity.resources.getString(R.string.turned_airplane_mode)  // Optional
            pleaseTurnOffText =activity.resources.getString(R.string.Please_turn_off)  // Optional
            airplaneModeOffButtonText =activity.resources.getString(R.string.Airplane_mode) // Optional
            showAirplaneModeOffButtons = true // Optional
        }
    }.build()

    // No Internet Dialog: Signal
    NoInternetDialogSignal.Builder(
        activity,
        lifecycle
    ).apply {
        dialogProperties.apply {
            connectionCallback = object : ConnectionCallback { // Optional
                override fun hasActiveConnection(hasActiveConnection: Boolean) {
                    // ...
                }
            }

            cancelable = false // Optional
            noInternetConnectionTitle = activity.resources.getString(R.string.No_Internet) // Optional
            noInternetConnectionMessage =
                activity.resources.getString(R.string.Connection_check) // Optional
            showInternetOnButtons = true // Optional
            pleaseTurnOnText = activity.resources.getString(R.string.Please_turn_on) // Optional
            wifiOnButtonText = activity.resources.getString(R.string.Wifi) // Optional
            mobileDataOnButtonText = activity.resources.getString(R.string.Mobile_data) // Optional

            onAirplaneModeTitle = activity.resources.getString(R.string.No_Internet)// Optional
            onAirplaneModeMessage = activity.resources.getString(R.string.turned_airplane_mode) // Optional
            pleaseTurnOffText = activity.resources.getString(R.string.Please_turn_off) // Optional
            airplaneModeOffButtonText = activity.resources.getString(R.string.Airplane_mode) // Optional
            showAirplaneModeOffButtons = true // Optional
        }
    }.build()
}