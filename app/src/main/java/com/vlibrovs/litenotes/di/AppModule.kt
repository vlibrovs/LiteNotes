package com.vlibrovs.litenotes.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vlibrovs.litenotes.data.repository.FirebaseNoteRepository
import com.vlibrovs.litenotes.data.repository.FirebaseUserRepository
import com.vlibrovs.litenotes.domain.repository.NoteRepository
import com.vlibrovs.litenotes.domain.repository.UserRepository
import com.vlibrovs.litenotes.domain.usecase.user.GetCurrentUserUseCase
import com.vlibrovs.litenotes.domain.usecase.user.SignInUserUseCase
import com.vlibrovs.litenotes.domain.usecase.user.SignOutUserUseCase
import com.vlibrovs.litenotes.domain.usecase.user.SignUpUserUseCase
import com.vlibrovs.litenotes.presentation.viewmodel.EditNoteScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.MainScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignInScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignUpScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> {
        FirebaseUserRepository(Firebase.auth)
    }
    single<NoteRepository> {
        FirebaseNoteRepository()
    }

    single {
        SignInUserUseCase(get())
    }
    single {
        SignUpUserUseCase(get())
    }
    single {
        SignOutUserUseCase(get())
    }
    single {
        GetCurrentUserUseCase(get())
    }

    viewModel {
        SignInScreenViewModel(get(), get())
    }
    viewModel {
        SignUpScreenViewModel(get())
    }
    viewModel {
        MainScreenViewModel(get())
    }
    viewModel {
        EditNoteScreenViewModel()
    }
}