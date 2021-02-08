package com.abkhrr.comm.bank.di.provider.list

import com.abkhrr.comm.bank.presentation.main.fragment.list.FragmentListEmployee
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentListEmployeeProvider {
    @ContributesAndroidInjector
    abstract fun injectFragmentListEmployee(): FragmentListEmployee
}