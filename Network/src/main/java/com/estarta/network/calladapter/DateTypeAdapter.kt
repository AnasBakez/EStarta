package com.estarta.network.calladapter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTypeAdapter : JsonSerializer<Date?>, JsonDeserializer<Date?> {

    companion object {
        private const val TAG = "DateTypeAdapter"
        private const val DATE_MILLI_SECONDS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS"

        private fun convertDateTextToDate(dateText: String?): Date? {
            dateText?.let { date ->
                try {
                    return SimpleDateFormat(DATE_MILLI_SECONDS_PATTERN, Locale.getDefault()).parse(date)
                } catch (e: Exception) {
                    Log.d(TAG, "convertDateTextToDate: option1 : error=${e.message}")
                }
            }
            return null
        }

        private fun getGsonBuilder(): Gson {
            return GsonBuilder().setDateFormat(DATE_MILLI_SECONDS_PATTERN).create()
        }


        private fun getJsonElement(body: Any, typeOfSrc: Type): JsonElement? {
            return try {
                getGsonBuilder().toJsonTree(body, typeOfSrc)
            } catch (ignored: Exception) {
                null
            }
        }
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {
        //check if the json is date or timestamp
        val timeString = json.toString()
        return try {
            Date(timeString.toLong() * 1000)
        } catch (e: Exception) {
            try {
                convertDateTextToDate(json.asString)
            } catch (ignored: Exception) {
                null
            }
        }
    }

    override fun serialize(src: Date?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement? {
        return src?.let { getJsonElement(src, typeOfSrc) }
    }
}