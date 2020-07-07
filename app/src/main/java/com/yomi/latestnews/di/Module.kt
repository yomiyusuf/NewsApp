package com.yomi.latestnews.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
val repositoryModule = module {
    factory<IRepository> { NewsRepository(get()) }
}

val useCaseModule = module {
    factory { HeadlinesUseCase(get()) }
}

val viewModelModule = module {
    viewModel { HeadlinesViewModel(get()) }
}