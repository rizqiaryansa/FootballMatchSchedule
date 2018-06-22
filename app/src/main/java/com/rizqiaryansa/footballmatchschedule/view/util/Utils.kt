package com.rizqiaryansa.footballmatchschedule.view.util

import android.content.Context
import android.view.View
import com.rizqiaryansa.footballmatchschedule.view.db.FavoriteDatabaseHelper

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

val Context.db: FavoriteDatabaseHelper
    get() = FavoriteDatabaseHelper.getInstance(applicationContext)