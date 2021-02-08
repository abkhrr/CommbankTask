package com.abkhrr.comm.bank.presentation.base

import androidx.lifecycle.ViewModel
import com.abkhrr.comm.bank.utils.event.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String>  = SingleLiveEvent()
    val showSnack: SingleLiveEvent<String>  = SingleLiveEvent()
}