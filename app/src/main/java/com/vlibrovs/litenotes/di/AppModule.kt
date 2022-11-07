package com.vlibrovs.litenotes.di

import com.vlibrovs.litenotes.presentation.viewmodel.MainScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignInScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignUpScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        SignInScreenViewModel()
    }
    viewModel {
        SignUpScreenViewModel()
    }
    viewModel {
        MainScreenViewModel()
    }
}