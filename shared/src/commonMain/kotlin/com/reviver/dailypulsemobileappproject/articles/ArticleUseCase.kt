package com.reviver.dailypulsemobileappproject.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticleUseCase(private  val articleService: ArticleService) {

    suspend fun getArticles():List<Article> = mapArticles(articleService.fetchArticles())

    private fun mapArticles(articlesRaw:List<ArticleRaw>):List<Article> = articlesRaw.map {
        raw -> Article(
            title = raw.title,
            description = raw.description ?:"Click to find out more",
            date = getDaysString(raw.date),
            imageUrl = raw.imageUrl ?: ""
        )
    }

    private fun getDaysString(date:String):String{
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days)>1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }
        return result
    }
}