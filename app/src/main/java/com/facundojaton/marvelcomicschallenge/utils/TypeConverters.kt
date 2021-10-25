package com.facundojaton.marvelcomicschallenge.utils

import androidx.room.TypeConverter
import com.facundojaton.marvelcomicschallenge.model.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class UrlConverter {
    @TypeConverter
    fun storedStringToUrls(data: String?): List<Url?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Url?>?>() {}.type
        return gson.fromJson<List<Url?>>(data, listType)
    }

    @TypeConverter
    fun UrlsStoredString(myObjects: List<Url?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}

