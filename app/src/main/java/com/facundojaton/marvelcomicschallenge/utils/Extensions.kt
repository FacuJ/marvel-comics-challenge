package com.facundojaton.marvelcomicschallenge.utils

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.facundojaton.marvelcomicschallenge.R

fun Fragment.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(messageRes: Int) {
    Toast.makeText(this.context, messageRes, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLogoutDialog() {
    this.context?.let {
        val dialog = AlertDialog.Builder(it)
        dialog.apply {
            setTitle(R.string.logout_title)
            setMessage(R.string.logout_message)
            setPositiveButton(R.string.yes) { dialog, _ ->
                SessionController.logout()
                findNavController().navigateUp()
                dialog.dismiss()
            }
            setNeutralButton(R.string.exit_without_logout) { dialog, _ ->
                dialog.dismiss()
                activity?.finish()
            }
            setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}

fun Activity.showLogoutDialog() {
    val dialog = AlertDialog.Builder(this)
    dialog.apply {
        setTitle(R.string.logout_title)
        setMessage(R.string.logout_message)
        setPositiveButton(R.string.yes) { dialog, _ ->
            SessionController.logout()
            findNavController(R.id.nav_host_fragment).navigateUp()
            dialog.dismiss()
        }
        setNeutralButton(R.string.exit_without_logout) { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        setNegativeButton(R.string.no) { dialog, _ ->
            dialog.dismiss()
        }
    }
    dialog.show()
}
/*
fun RoomCharacter.toDomainCharacter(): Character =
    Character(
        id,
        name,
        description,
        modified,
        resourceUri,
        urls,
        thumbnail,
        comics,
        stories,
        events,
        series
    )

fun Character.toRoomCharacter(): RoomCharacter =
    RoomCharacter(
        id,
        name,
        description,
        modified,
        resourceUri,
        urls,
        thumbnail,
        comics,
        stories,
        events,
        series
    )*/
