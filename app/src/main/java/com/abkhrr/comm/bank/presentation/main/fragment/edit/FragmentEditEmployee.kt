package com.abkhrr.comm.bank.presentation.main.fragment.edit

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abkhrr.comm.bank.BR
import com.abkhrr.comm.bank.R
import com.abkhrr.comm.bank.databinding.FragmentEditEmployeeBinding
import com.abkhrr.comm.bank.domain.dto.model.body.EmployeeBody
import com.abkhrr.comm.bank.domain.dto.model.response.Employee
import com.abkhrr.comm.bank.factory.ViewModelProviderFactory
import com.abkhrr.comm.bank.presentation.base.BaseFragment
import com.abkhrr.comm.bank.presentation.base.navigation.NavigationCommand
import com.abkhrr.comm.bank.presentation.main.viewmodel.EditEmployeeViewModel
import com.abkhrr.comm.bank.utils.event.EventObserver
import javax.inject.Inject

class FragmentEditEmployee : BaseFragment<FragmentEditEmployeeBinding, EditEmployeeViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var employee: Employee? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_edit_employee

    override val viewModel: EditEmployeeViewModel
        get() = ViewModelProvider(this, factory).get(EditEmployeeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            employee = arguments?.getParcelable("employee")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp(){
        setupData()
        setupSubmitButton()
        setupBackButton()
        getUpdateResult()
    }

    private fun setupData(){
        getViewDataBinding().employee = employee
    }

    private fun setupSubmitButton() {
        getViewDataBinding().buttonSubmit.setOnClickListener {
            val name   = getViewDataBinding().editTextName.text.toString()
            val salary = getViewDataBinding().editTextSalary.text.toString()
            val age    = getViewDataBinding().editTextAge.text.toString()
            val body   = EmployeeBody(name, salary, age)

            viewModel.editEmployee(body, employee?.id.toString())
        }
    }

    private fun setupBackButton() {
        getViewDataBinding().customBackButton.setOnClickListener {
            navigate(NavigationCommand.Back)
        }
    }

    private fun getUpdateResult() {
        viewModel.updateData.observe(viewLifecycleOwner, EventObserver{ success ->
            if (success){
                getViewDataBinding().editTextName.text?.clear()
                getViewDataBinding().editTextSalary.text?.clear()
                getViewDataBinding().editTextAge.text?.clear()
                viewModel.showSnack.value = "Successfully Update User Data."
            } else {
                viewModel.showSnack.value = "Something Went Wrong, Please Try Again Later..."
            }
        })
    }

}