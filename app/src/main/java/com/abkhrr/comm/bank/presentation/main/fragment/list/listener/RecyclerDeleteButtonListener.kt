package com.abkhrr.comm.bank.presentation.main.fragment.list.listener

class RecyclerDeleteButtonListener(private val onDeleteClicked: () -> Unit) {
    fun onDeleteClick() = onDeleteClicked.invoke()
}