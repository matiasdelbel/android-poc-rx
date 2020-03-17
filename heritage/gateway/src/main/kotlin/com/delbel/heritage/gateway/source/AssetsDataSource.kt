package com.delbel.heritage.gateway.source

import android.content.res.AssetManager
import com.delbel.heritage.gateway.model.HeritageDo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.IOException

internal class AssetsDataSource constructor(
    private val assetsManager: AssetManager,
    private val jsonParser: Gson
) {

    companion object {
        private const val FILE_NAME = "heritages.json"
    }

    fun obtainAll() = Single.create<List<HeritageDo>> { emitter ->
        try {
            val jsonFromAssets = readJsonFromAsset()
            val heritagesDo = convertToDto(json = jsonFromAssets)

            emitter.onSuccess(heritagesDo)
        } catch (e: IOException) {
            emitter.onError(e)
        }
    }

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