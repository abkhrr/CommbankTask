package com.abkhrr.comm.bank.di.provider.insert

import com.abkhrr.comm.bank.presentation.main.fragment.insert.FragmentInsertEmployee
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInsertEmployeeProvider {
    @ContributesAndroidInjector
    abstract fun injectFragmentInsertEmployee(): FragmentInsertEmployee
}