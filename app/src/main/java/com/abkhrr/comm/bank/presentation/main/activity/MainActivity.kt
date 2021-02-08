package com.abkhrr.comm.bank.presentation.main.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.comm.bank.BR
import com.abkhrr.comm.bank.R
import com.abkhrr.comm.bank.databinding.ActivityMainBinding
import com.abkhrr.comm.bank.factory.ViewModelProviderFactory
import com.abkhrr.comm.bank.presentation.base.BaseActivity
import com.abkhrr.comm.bank.presentation.main.viewmodel.MainViewModel
import com.abkhrr.comm.bank.staircase.StairCase
import com.abkhrr.comm.bank.utils.log.MyLog
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, factory).get(MainViewModel::class.java)

    override val isNeedOnline: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StairCase().stairCaseLogic(6)
    }
}