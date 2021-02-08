package com.abkhrr.comm.bank.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abkhrr.comm.bank.data.source.AppDataSource
import com.abkhrr.comm.bank.domain.dto.api.ApiResult
import com.abkhrr.comm.bank.domain.dto.model.body.EmployeeBody
import com.abkhrr.comm.bank.domain.dto.model.response.EmployeeResponse
import com.abkhrr.comm.bank.presentation.base.BaseViewModel
import com.abkhrr.comm.bank.utils.event.Event
import com.abkhrr.comm.bank.utils.log.MyLog
import kotlinx.coroutines.launch

class InsertEmployeeViewModel(private val appDataSource: AppDataSource): BaseViewModel() {

    private val eventSuccessUpload = MutableLiveData<Event<Boolean>>()

    fun insertEmployee(body: EmployeeBody){
        viewModelScope.launch {
            when (val result = appDataSource.insertEmployee(body)) {
                is ApiResult.Success<EmployeeResponse> -> {
                    showSnack.value          = "Success Uploading Data To Server..."
                    isLoading.value          = false
                    eventSuccessUpload.value = Event(true)
                }
                is ApiResult.Error -> {
                    eventSuccessUpload.value = Event(false)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                    isLoading.value = false
                }
            }
        }
    }

    val successData : LiveData<Event<Boolean>>
        get() = eventSuccessUpload


    companion object{
        private val TAG = InsertEmployeeViewModel::class.java.simpleName
    }

}