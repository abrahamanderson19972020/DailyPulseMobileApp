package com.reviver.dailypulsemobileappproject.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "cb6daac8764d49d091e52df54c28f7cb"

    suspend fun fetchArticles():List<ArticleRaw>{
        val url:String = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=cb6daac8764d49d091e52df54c28f7cb"
        val response:ArticlesResponse = httpClient.get(url).body()
        return response.articles
    }
}