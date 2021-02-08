package com.abkhrr.comm.bank.di.builder

import com.abkhrr.comm.bank.di.provider.edit.FragmentEditEmployeeProvider
import com.abkhrr.comm.bank.di.provider.insert.FragmentInsertEmployeeProvider
import com.abkhrr.comm.bank.di.provider.list.FragmentListEmployeeProvider
import com.abkhrr.comm.bank.presentation.main.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    // Main Activity
    @ContributesAndroidInjector(modules = [
        FragmentInsertEmployeeProvider::class,
        FragmentListEmployeeProvider::class,
        FragmentEditEmployeeProvider::class,
    ])
    abstract fun bindMainActivity(): MainActivity

}