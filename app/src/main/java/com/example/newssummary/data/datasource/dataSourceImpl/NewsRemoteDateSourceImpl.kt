package com.example.newssummary.data.datasource.dataSourceImpl

import android.content.Context
import com.example.newssummary.data.api.NewsAPIService
import com.example.newssummary.data.datasource.dataSource.NewsRemoteDatasource
import com.example.newssummary.data.model.APIResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.pytorch.LiteModuleLoader
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class NewsRemoteDateSourceImpl(
    private val newsAPIService: NewsAPIService,

):NewsRemoteDatasource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlindes(country, page)
    }

    override suspend fun getSearchedTopHealines(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadlindes(country, searchQuery, page)
    }

    override suspend fun getSummary(context:Context, url: String): String {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                val htmlContent =  response.body?.string() ?: ""
                return  parsehtml(context, htmlContent)
            } else {
                throw IOException("Unexpected response code: ${response.code}")
            }
        }
    }

    private fun parsehtml(context:Context, htmlContent:String):String {

        val doc = Jsoup.parse(htmlContent)
        val pTags: List<Element> = doc.select("article").select("p")
        var para = ""
        for (i in 0 until minOf(10, pTags.size)) {
            val pTag: Element = pTags[i]
            para += pTag.text()
        }
        summarize(context, para)
        return para
    }

    private fun summarize(context: Context, text:String):String{
        val model = LiteModuleLoader.load(
            assetFilePath(
                context,
                ""
            )
        )


        return ""


    }


    @Throws(IOException::class)
    private fun assetFilePath(context: Context, assetName: String): String? {
        val file = File(context.filesDir, assetName)
        if (file.exists() && file.length() > 0) {
            return file.absolutePath
        }
        context.assets.open(assetName).use { `is` ->
            FileOutputStream(file).use { os ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (`is`.read(buffer).also { read = it } != -1) {
                    os.write(buffer, 0, read)
                }
                os.flush()
            }
            return file.absolutePath
        }
    }
}