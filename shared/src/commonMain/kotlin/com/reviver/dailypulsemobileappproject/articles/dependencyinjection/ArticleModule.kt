package com.reviver.dailypulsemobileappproject.articles.dependencyinjection

import com.reviver.dailypulsemobileappproject.articles.ArticleService
import com.reviver.dailypulsemobileappproject.articles.ArticleUseCase
import com.reviver.dailypulsemobileappproject.articles.ArticlesViewModel

import org.koin.dsl.module




val articleModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel() }
}
