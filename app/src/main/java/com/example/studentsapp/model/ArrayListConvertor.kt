package com.example.studentsapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


//We will have to create a type convertor for our class because sqlite only supports type convertor for
//int, blob, text,null, real
class ArrayListConverter {

    @TypeConverter
    fun listToJson(value: List<CategoriesData.CategoriesItem>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<CategoriesData.CategoriesItem>::class.java).toList()
}


inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
