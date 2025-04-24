package com.reviver.dailypulsemobileappproject.articles

import com.reviver.dailypulsemobileappproject.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel:BaseViewModel() {
    private val _articlesState:MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState(loading = true))
    val articleState:StateFlow<ArticleState> get() = _articlesState
    private val useCase:ArticleUseCase
    init {
        val httpClient = HttpClient{
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        val service = ArticleService(httpClient)
        useCase = ArticleUseCase(service)
        getArticles()
    }
    private  fun getArticles(){
        scope.launch {
            val fetchedArticles = useCase.getArticles()
            _articlesState.emit(ArticleState(articles = fetchedArticles))
        }
    }

}