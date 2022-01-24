package com.zainalfn.moviecatalogue.util

import android.content.Context
import com.zainalfn.moviecatalogue.data.source.remote.response.CatalogueResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * Created by zainal on 1/17/22 - 7:12 AM
 */
class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadCatalogue(isMovie: Boolean): List<CatalogueResponse> {
        val list = ArrayList<CatalogueResponse>()
        try {
            val fileName = if (isMovie) "movie.json" else "tvshows.json"
            val responseObject = JSONObject(parsingFileToString(fileName).toString())
            val listArray = responseObject.getJSONArray("response")
            for (i in 0 until listArray.length()) {
                val catalogue = listArray.getJSONObject(i)
                catalogue.apply {
                    val id = getInt("id")
                    val title = getString("title")
                    val overview = getString("overview")
                    val year = getInt("year")
                    val genre = getString("genre")
                    val poster = getString("poster")
                    val score = getString("score")

                    val courseResponse =
                        CatalogueResponse(id, title, overview, genre, score, poster, year)
                    list.add(courseResponse)
                }

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun getDetailMovie(id: Int): CatalogueResponse? {
        var data: CatalogueResponse? = null
        try {
            val result = loadCatalogue(true)
            val element = result.firstOrNull { it.id == id }
            element?.apply {
                data = this
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return data
    }

    fun getDetailTvSHow(id: Int): CatalogueResponse? {
        var data: CatalogueResponse? = null
        try {
            val result = loadCatalogue(false)
            val element = result.firstOrNull { it.id == id }
            element?.apply {
                data = this
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return data
    }

}