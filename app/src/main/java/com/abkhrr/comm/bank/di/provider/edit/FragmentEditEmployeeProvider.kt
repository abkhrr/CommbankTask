package com.abkhrr.comm.bank.di.provider.edit

import com.abkhrr.comm.bank.presentation.main.fragment.edit.FragmentEditEmployee
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentEditEmployeeProvider {
    @ContributesAndroidInjector
    abstract fun injectFragmentEditEmployee(): FragmentEditEmployee
}