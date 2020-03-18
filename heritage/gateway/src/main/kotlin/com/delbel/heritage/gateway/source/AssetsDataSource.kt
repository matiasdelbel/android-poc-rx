package com.delbel.heritage.gateway.source

import android.content.res.AssetManager
import com.delbel.heritage.gateway.model.HeritageDo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Scheduler
import io.reactivex.Single
import java.io.IOException

internal class AssetsDataSource constructor(
    private val assetsManager: AssetManager,
    private val jsonParser: Gson,
    private val scheduler: Scheduler
) {

    companion object {
        private const val FILE_NAME = "heritages.json"
    }

    fun obtainAll() = Single.fromCallable { readJsonFromAsset() }
        .subscribeOn(scheduler)
        .map { convertToDto(json = it) }

    @Throws(IOException::class)
    private fun readJsonFromAsset(): String {
        val jsonStream = assetsManager.open(FILE_NAME)
        return jsonStream.bufferedReader().use { it.readText() }
    }

    private fun convertToDto(json: String): List<HeritageDo> {
        val dtoType = object : TypeToken<List<HeritageDo>>() {}.type
        return jsonParser.fromJson(json, dtoType)
    }
}