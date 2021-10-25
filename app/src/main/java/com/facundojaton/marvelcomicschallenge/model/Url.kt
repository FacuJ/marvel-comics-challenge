package com.facundojaton.marvelcomicschallenge.model

import java.io.Serializable

data class Url(
    val type: String?,
    val url: String?
) : Serializable