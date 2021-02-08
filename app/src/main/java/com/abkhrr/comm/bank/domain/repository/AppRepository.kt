package com.abkhrr.comm.bank.domain.repository

import com.abkhrr.comm.bank.data.remote.ApiService
import com.abkhrr.comm.bank.data.source.AppDataSource
import com.abkhrr.comm.bank.domain.dto.api.ApiResult
import com.abkhrr.comm.bank.domain.dto.model.body.EmployeeBody
import com.abkhrr.comm.bank.domain.dto.model.response.EmployeeResponse
import com.abkhrr.comm.bank.utils.api.ErrorCodes
import com.abkhrr.comm.bank.utils.api.TypeError
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiService: ApiService) : AppDataSource {

    override suspend fun insertEmployee(body: EmployeeBody): ApiResult<EmployeeResponse> {
        return try {
            val insertEmployeeResponse = apiService.insertEmployee(body)
            return ApiResult.Success(insertEmployeeResponse)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    override suspend fun updateEmployee(body: EmployeeBody, id: String): ApiResult<EmployeeResponse> {
        return try {
            val updateEmployeeResponse = apiService.updateEmployee(body, id)
            return ApiResult.Success(updateEmployeeResponse)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    override suspend fun deleteEmployee(id: String): ApiResult<EmployeeResponse> {
        return try {
            val deleteEmployeeResponse = apiService.deleteEmployee(id)
            return ApiResult.Success(deleteEmployeeResponse)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    override suspend fun readEmployee(): ApiResult<EmployeeResponse> {
        return try {
            val readEmployeeResponse = apiService.readEmployee()
            return ApiResult.Success(readEmployeeResponse)
        } catch (e: Exception){
            getExceptionType(e)
        }
    }

    private fun getExceptionType(e: Exception) : ApiResult.Error {
        return when (e) {
            is HttpException          -> ApiResult.Error(e.message(), e.code(), TypeError.ERROR_HTTP)
            is SocketTimeoutException -> ApiResult.Error(e.localizedMessage, ErrorCodes.SocketTimeOut.code, TypeError.ERROR_SOCKET)
            else                      -> ApiResult.Error(e.localizedMessage, Int.MAX_VALUE, TypeError.NETWORK_ERROR)
        }
    }

}