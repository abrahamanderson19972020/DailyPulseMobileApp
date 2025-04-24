package com.reviver.dailypulsemobileappproject.articles

class ArticleUseCase(private  val articleService: ArticleService) {

    suspend fun getArticles():List<Article> = mapArticles(articleService.fetchArticles())

    private fun mapArticles(articlesRaw:List<ArticleRaw>):List<Article> = articlesRaw.map {
        raw -> Article(
            title = raw.title,
            description = raw.description ?:"Click to find out more",
            date = raw.date,
            imageUrl = raw.imageUrl ?: ""
        )
    }
}