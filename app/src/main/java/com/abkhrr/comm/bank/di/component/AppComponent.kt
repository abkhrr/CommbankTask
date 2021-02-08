package com.abkhrr.comm.bank.di.component

import android.app.Application
import com.abkhrr.comm.bank.di.app.CoreApplication
import com.abkhrr.comm.bank.di.builder.MainActivityBuilder
import com.abkhrr.comm.bank.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityBuilder::class])
interface AppComponent {

    fun inject(app: CoreApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}