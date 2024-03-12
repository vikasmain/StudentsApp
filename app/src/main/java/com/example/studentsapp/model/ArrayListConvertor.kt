package com.example.studentsapp.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//We will have to create a type convertor for our class because sqlite only supports type convertor for
//int, blob, text,null, real
@ProvidedTypeConverter
class ArrayListConverter {

    @TypeConverter
    fun fromString(serialized: String): CategoriesData{
        return Gson().fromJson(serialized, CategoriesData::class.java)
    }

    @TypeConverter
    fun toString(entity: CategoriesData): String {
        return Gson().toJson(entity)
    }
}


inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
