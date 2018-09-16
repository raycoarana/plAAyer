package com.raycoarana.plaayer.core.json

import com.google.gson.Gson
import java.io.Reader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Json @Inject constructor(
        private val gson: Gson
) {
    fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson(json, clazz)
    fun <T> fromJson(jsonReader: Reader, clazz: Class<T>): T = gson.fromJson(jsonReader, clazz)
}
