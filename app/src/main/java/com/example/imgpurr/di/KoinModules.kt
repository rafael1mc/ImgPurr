package com.example.imgpurr.di

import com.example.imgpurr.repository.RetrofitBuilder
import com.example.imgpurr.repository.api.imgur.ImgurRepository
import com.example.imgpurr.repository.api.imgur.ImgurRepositoryImpl
import com.example.imgpurr.screen.imgur.MainViewModel
import com.example.imgpurr.util.StringUtil
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val repositoriesModule = module {
    single<ImgurRepository> { ImgurRepositoryImpl(get()) }
}

val utilsModule = module {
    single { StringUtil(get()) }
}

val commonsModule = module {
    single { RetrofitBuilder() }
}