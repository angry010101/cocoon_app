package com.yakymovych.simon.yogaapp.ui.login

import com.yakymovych.simon.yogaapp.data.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    fun provideViewModel(repository: Repository) = LoginViewModel(repository)
}