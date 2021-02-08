package com.abkhrr.comm.bank.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.comm.bank.data.source.AppDataSource
import com.abkhrr.comm.bank.presentation.main.viewmodel.EmployeeListViewModel
import com.abkhrr.comm.bank.presentation.main.viewmodel.InsertEmployeeViewModel
import com.abkhrr.comm.bank.presentation.main.viewmodel.MainViewModel
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(
    private val appDataSource: AppDataSource
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(appDataSource) as T
            }
            modelClass.isAssignableFrom(InsertEmployeeViewModel::class.java) -> {
                InsertEmployeeViewModel(appDataSource) as T
            }
            modelClass.isAssignableFrom(EmployeeListViewModel::class.java) -> {
                EmployeeListViewModel(appDataSource) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}