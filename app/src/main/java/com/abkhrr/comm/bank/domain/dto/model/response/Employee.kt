package com.abkhrr.comm.bank.domain.dto.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Employee(
    @SerializedName("id")
    var id: String,

    @SerializedName("employee_name")
    var employeeName: String,

    @SerializedName("employee_salary")
    var employeeSalary: String,

    @SerializedName("employee_age")
    var employeeAge: String
): Parcelable