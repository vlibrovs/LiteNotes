package com.vlibrovs.litenotes.di

import com.vlibrovs.litenotes.presentation.viewmodel.SignInScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        SignInScreenViewModel()
    }
}