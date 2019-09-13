package com.yakymovych.simon.yogaapp.presentation.ui.login

import androidx.lifecycle.ViewModel
import com.yakymovych.simon.yogaapp.presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMyViewModel(myViewModel: LoginViewModel): ViewModel
}
