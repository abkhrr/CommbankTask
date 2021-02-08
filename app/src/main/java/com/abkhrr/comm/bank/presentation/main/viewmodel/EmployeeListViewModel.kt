package com.abkhrr.comm.bank.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abkhrr.comm.bank.data.source.AppDataSource
import com.abkhrr.comm.bank.domain.dto.api.ApiResult
import com.abkhrr.comm.bank.domain.dto.model.response.Employee
import com.abkhrr.comm.bank.domain.dto.model.response.EmployeeResponse
import com.abkhrr.comm.bank.presentation.base.BaseViewModel
import com.abkhrr.comm.bank.utils.api.ApiUtils
import com.abkhrr.comm.bank.utils.event.Event
import com.abkhrr.comm.bank.utils.log.MyLog
import kotlinx.coroutines.launch

class EmployeeListViewModel(private val appDataSource: AppDataSource): BaseViewModel() {

    private val employeeListLiveData: MutableLiveData<List<Employee>> = MutableLiveData()
    private val deleteResultLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()

    fun deleteEmployee(id: String){
        viewModelScope.launch {
            when(val result = appDataSource.deleteEmployee(id)){
                is ApiResult.Success<EmployeeResponse> -> {
                    showSnack.value            = "Success Delete Data!"
                    deleteResultLiveData.value = Event(true)
                }
                is ApiResult.Error -> {
                    deleteResultLiveData.value = Event(false)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                }
            }
        }
    }

    fun fetchAllEmployee(){
        viewModelScope.launch {
            when(val result = appDataSource.readEmployee()){
                is ApiResult.Success<EmployeeResponse> -> {
                    result.data.data.let { mapEmployeeData(it) }
                }
                is ApiResult.Error -> {
                    val fourHundredError     = "Something Went Wrong..."
                    showSnack.value          = ApiUtils().getErrorMessage(result.statusCode, result.typeError, fourHundredError)
                    MyLog.e(TAG, "e/$TAG: ${result.message}")
                }
            }
        }
    }

    val employeeData: LiveData<List<Employee>>
        get() = employeeListLiveData

    val deleteData: LiveData<Event<Boolean>>
        get() = deleteResultLiveData

    private fun mapEmployeeData(employee: List<Employee>){
        employeeListLiveData.value = employee.map {
            Employee(
                it.id,
                it.employeeName,
                it.employeeSalary,
                it.employeeAge
            )
        }
    }

    companion object{
        private val TAG = EmployeeListViewModel::class.java.simpleName
    }

}