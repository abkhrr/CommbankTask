package com.abkhrr.comm.bank.data.remote

import com.abkhrr.comm.bank.domain.dto.model.body.EmployeeBody
import com.abkhrr.comm.bank.domain.dto.model.response.EmployeeResponse
import retrofit2.http.*

interface ApiService {

    @POST(ApiEndpoint.API_INSERT_EMPLOYEE)
    suspend fun insertEmployee(@Body body: EmployeeBody): EmployeeResponse

    @PUT(ApiEndpoint.API_UPDATE_EMPLOYEE)
    suspend fun updateEmployee(@Body body: EmployeeBody, @Query("id") id: String): EmployeeResponse

    @GET(ApiEndpoint.API_READ_EMPLOYEE)
    suspend fun readEmployee(): EmployeeResponse

    @DELETE(ApiEndpoint.API_DELETE_EMPLOYEE)
    suspend fun deleteEmployee(@Query("id") id: String): EmployeeResponse

}