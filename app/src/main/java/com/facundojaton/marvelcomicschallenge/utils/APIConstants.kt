package com.facundojaton.marvelcomicschallenge.utils

object APIConstants {
    const val BASE_URL = " https://developer.marvel.com/docs#!/"

    object Endpoints {
        const val CHARACTERS = "v1/public/characters"
    }

    object QueryParams {
        const val APIKEY = "apikey"
        const val TIMESTAMP = "ts"
        const val HASH = "hash"
    }

    object Keys {
        const val PUBLIC_KEY = "7a2a4612ee01864ec5960b4f5d33d529"
        const val PRIVATE_KEY = "0aac414bcd06a85570c827db36d5bada92d0fb93"
    }
}