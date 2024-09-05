package com.example.myapplication.core

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.core.Constant.CATEGORY_KEY
import com.example.myapplication.core.Constant.SHARED_NAME

/**
 * This class manages SharedPreferences for storing and retrieving the last opened category by the user.
 *
 * Purpose:
 * - **setCategoryName(name: String):** Saves the given category name to SharedPreferences. This is useful for persisting the user's last selected category.
 * - **getCategoryName():** Retrieves the saved category name from SharedPreferences. If no category is saved, an empty string is returned.
 *
 * This class helps in scenarios where network access is unavailable by allowing the app to display data from the local Room database based on the last used category.
 */

class ArticleSharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    fun setCategoryName (name: String) {
        sharedPreferences.edit().putString(CATEGORY_KEY, name).apply()
    }

    fun getCategoryName(): String {
        return sharedPreferences.getString(CATEGORY_KEY,"")?:""
    }

}