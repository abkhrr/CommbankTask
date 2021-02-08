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

class EditEmployeeViewModel(private val appDataSource: AppDataSource): BaseViewModel() {

    private val updateResultLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()

    fun editEmployee(body: EmployeeBody, id: String){
        viewModelScope.launch {
            when(val result = appDataSource.updateEmployee(body, id)){
                is ApiResult.Success<EmployeeResponse> -> {
                    showSnack.value            = "Success Updating User Data"
                    updateResultLiveData.value = Event(true)
                }
                is ApiResult.Error -> {
                    updateResultLiveData.value = Event(false)
                    MyLog.e(TAG, "e/${TAG}: ${result.message}")
                    isLoading.value = false
                }
            }
        }
    }

    val updateData: LiveData<Event<Boolean>>
        get() = updateResultLiveData


    companion object{
        private val TAG = EditEmployeeViewModel::class.java.simpleName
    }

}