package com.abkhrr.comm.bank.domain.dto.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("data")
    var data: List<Employee>,

    @SerializedName("message")
    var message: String
): Parcelable