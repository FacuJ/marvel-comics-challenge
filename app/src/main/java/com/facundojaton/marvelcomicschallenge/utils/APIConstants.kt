package com.facundojaton.marvelcomicschallenge.utils

object APIConstants {
    const val BASE_URL = "https://gateway.marvel.com/"

    object Endpoints {
        const val CHARACTERS = "v1/public/characters"
    }

    object QueryParams {
        const val APIKEY = "apikey"
        const val TIMESTAMP = "ts"
        const val HASH = "hash"
        const val PAGE = "page"
    }

    object Keys {
        const val PUBLIC_KEY = "7a2a4612ee01864ec5960b4f5d33d529"
        const val PRIVATE_KEY = "82f5c79e12146961988feac56f8bb4c3"
        const val TIMESTAMP = "1635284721"
    }
}