package com.abkhrr.comm.bank.presentation.main.fragment.list.listener

import com.abkhrr.comm.bank.domain.dto.model.response.Employee

interface OnItemButtonClickListener {
    fun onEditClicked(employee: Employee)
    fun onDeleteClick(employeeId: String, position: Int)
}