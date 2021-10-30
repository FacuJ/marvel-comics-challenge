package com.facundojaton.marvelcomicschallenge.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(messageRes: Int) {
    Toast.makeText(this.context, messageRes, Toast.LENGTH_SHORT).show()
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
