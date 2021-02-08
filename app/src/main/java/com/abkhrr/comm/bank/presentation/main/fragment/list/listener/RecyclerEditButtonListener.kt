package com.abkhrr.comm.bank.presentation.main.fragment.list.listener

class RecyclerEditButtonListener(private val onEditClicked: () -> Unit) {
    fun onEditClick()   = onEditClicked.invoke()
}