package com.cheng.hellodemo.adapter

import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonHelper {

    val json: Json = Json {
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    // The type of T must be marked as @Serializable
    inline fun<reified T> toJsonString(input: T): String {
        return json.encodeToString(input)
    }

    // The type of T must be marked as @Serializable
    inline fun<reified T> fromJsonString(jsonString: String): T? {
        return try {
            json.decodeFromString(jsonString)
        } catch (e: SerializationException) {
            null
        }
    }

}
