package com.facundojaton.marvelcomicschallenge.utils

object APIConstants {
    const val BASE_URL = "https://gateway.marvel.com/"

    object Endpoints {
        const val CHARACTERS = "v1/public/characters"
        const val EVENTS = "v1/public/events"
        const val CHARACTER_COMICS = "v1/public/characters/{${QueryParams.CHARACTER_ID}}/comics"
        const val EVENT_COMICS = "v1/public/events/{${QueryParams.EVENT_ID}}/comics"
        const val COMICS = "v1/public/comics"
    }

    object QueryParams {
        const val EVENTS = "events"
        const val ORDER_BY = "orderBy"
        const val OFFSET = "offset"
        const val LIMIT = "limit"
        const val APIKEY = "apikey"
        const val TIMESTAMP = "ts"
        const val HASH = "hash"
        const val PAGE = "page"
        const val EVENT_ID = "eventId"
        const val CHARACTER_ID = "characterId"
    }

    object Keys {
        const val APIKEY = "7a2a4612ee01864ec5960b4f5d33d529"
        const val HASH = "82f5c79e12146961988feac56f8bb4c3"
        const val TIMESTAMP = "1635284721"
    }
}