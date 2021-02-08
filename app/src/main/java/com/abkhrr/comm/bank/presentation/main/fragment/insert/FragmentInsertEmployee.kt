package com.abkhrr.comm.bank.presentation.main.fragment.insert

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.comm.bank.BR
import com.abkhrr.comm.bank.R
import com.abkhrr.comm.bank.databinding.FragmentInsertEmployeeBinding
import com.abkhrr.comm.bank.domain.dto.model.body.EmployeeBody
import com.abkhrr.comm.bank.factory.ViewModelProviderFactory
import com.abkhrr.comm.bank.presentation.base.BaseFragment
import com.abkhrr.comm.bank.presentation.base.navigation.NavigationCommand
import com.abkhrr.comm.bank.presentation.main.viewmodel.InsertEmployeeViewModel
import com.abkhrr.comm.bank.utils.event.EventObserver
import javax.inject.Inject

class FragmentInsertEmployee : BaseFragment<FragmentInsertEmployeeBinding, InsertEmployeeViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_insert_employee

    override val viewModel: InsertEmployeeViewModel
        get() = ViewModelProvider(this, factory).get(InsertEmployeeViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setupShowAllDataButton()
        setupSubmitNewEmployeeButton()
        getResultFromViewModel()
    }

    private fun setupShowAllDataButton(){
        getViewDataBinding().buttonSubmit.setOnClickListener {
            val name   = getViewDataBinding().editTextName.text.toString()
            val salary = getViewDataBinding().editTextSalary.text.toString()
            val age    = getViewDataBinding().editTextAge.text.toString()
            val body   = EmployeeBody(name, salary, age)

            viewModel.insertEmployee(body)
        }
    }

    private fun setupSubmitNewEmployeeButton(){
        getViewDataBinding().buttonShowAllEmployee.setOnClickListener {
            navigate(NavigationCommand.To(FragmentInsertEmployeeDirections.toFragmentListEmployee()))
        }
    }

    private fun getResultFromViewModel(){
        viewModel.successData.observe(viewLifecycleOwner, EventObserver{ success ->
            if (success){
                getViewDataBinding().editTextName.text?.clear()
                getViewDataBinding().editTextSalary.text?.clear()
                getViewDataBinding().editTextAge.text?.clear()
                viewModel.showSnack.value = "Success Uploading Data To Server..."
            } else {
                viewModel.showSnack.value = "Something Went Wrong, Please Try Again Later..."
            }
        })
    }

}