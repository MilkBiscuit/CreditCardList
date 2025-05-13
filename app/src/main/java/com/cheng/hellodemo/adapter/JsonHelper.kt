package com.cheng.hellodemo.adapter

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

object JsonHelper {

    val json: Json = Json {
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    // The type of T must be marked as @Serializable
    inline fun<reified T> fromJsonString(jsonString: String): T? {
        return json.decodeFromString(jsonString)
    }

}
