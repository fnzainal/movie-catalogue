package com.zainalfn.moviecatalogue.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zainalfn.moviecatalogue.BuildConfig.IMG_URL
import com.zainalfn.moviecatalogue.data.source.remote.response.Genres
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(IMG_URL + url)
        .centerCrop()
        .into(this)
}

fun ArrayList<Genres>?.toGenreString(): String {
    return if (!this.isNullOrEmpty()) {
        this.joinToString { "${it.name}" }
    } else ""
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


@SuppressLint("SimpleDateFormat")
fun toReadableDate(date: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return try {
        val dates = formatter.parse(date) as Date
        val locale = Locale.getDefault()
        val newFormat = SimpleDateFormat("d MMMM yyyy", locale)
        newFormat.format(dates)
    } catch (e: ParseException) {
        e.printStackTrace()
        date
    }
}