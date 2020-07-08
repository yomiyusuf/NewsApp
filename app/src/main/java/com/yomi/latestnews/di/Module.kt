package com.yomi.latestnews.di

import com.yomi.latestnews.data.database.AppDatabase
import com.yomi.latestnews.data.repository.NewsRepository
import com.yomi.latestnews.data.repository.NewsRepositoryImp
import com.yomi.latestnews.domain.usecase.HeadlinesUseCase
import com.yomi.latestnews.domain.usecase.SourcesUseCase
import com.yomi.latestnews.ui.feature.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
val repositoryModule = module {
    factory<NewsRepository> { NewsRepositoryImp(get(), get()) }
}

val useCaseModule = module {
    factory { HeadlinesUseCase(get()) }
    factory { SourcesUseCase(get()) }
}

val viewModelModule = module {
    viewModel { NewsViewModel(get(), get()) }
}

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()) }
}