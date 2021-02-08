package com.abkhrr.comm.bank.data.source

import com.abkhrr.comm.bank.domain.dto.api.ApiResult
import com.abkhrr.comm.bank.domain.dto.model.body.EmployeeBody
import com.abkhrr.comm.bank.domain.dto.model.response.EmployeeResponse

interface AppDataSource {
    suspend fun insertEmployee(body: EmployeeBody)                  : ApiResult<EmployeeResponse>
    suspend fun updateEmployee(body: EmployeeBody, id: String)      : ApiResult<EmployeeResponse>
    suspend fun deleteEmployee(id: String)                          : ApiResult<EmployeeResponse>
    suspend fun readEmployee()                                      : ApiResult<EmployeeResponse>
}