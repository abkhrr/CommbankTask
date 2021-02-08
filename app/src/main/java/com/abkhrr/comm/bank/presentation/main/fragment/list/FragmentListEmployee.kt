package com.abkhrr.comm.bank.presentation.main.fragment.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.comm.bank.BR
import com.abkhrr.comm.bank.R
import com.abkhrr.comm.bank.databinding.FragmentListEmployeeBinding
import com.abkhrr.comm.bank.domain.dto.model.response.Employee
import com.abkhrr.comm.bank.factory.ViewModelProviderFactory
import com.abkhrr.comm.bank.presentation.base.BaseFragment
import com.abkhrr.comm.bank.presentation.base.navigation.NavigationCommand
import com.abkhrr.comm.bank.presentation.main.adapter.EmployeeListAdapter
import com.abkhrr.comm.bank.presentation.main.fragment.list.listener.OnItemButtonClickListener
import com.abkhrr.comm.bank.presentation.main.viewmodel.EmployeeListViewModel
import com.abkhrr.comm.bank.utils.event.EventObserver
import javax.inject.Inject

class FragmentListEmployee : BaseFragment<FragmentListEmployeeBinding, EmployeeListViewModel>(), OnItemButtonClickListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var adapter: EmployeeListAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_list_employee

    override val viewModel: EmployeeListViewModel
        get() = ViewModelProvider(this, factory).get(EmployeeListViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = EmployeeListAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setupListData()
        setupAdapter()
    }

    private fun setupListData(){
        viewModel.fetchAllEmployee()
        viewModel.employeeData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                adapter.items.clear()
                adapter.items.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun setupAdapter(){
        val layoutManager                     = LinearLayoutManager(requireActivity())
        layoutManager.orientation             = RecyclerView.VERTICAL

        getViewDataBinding().viewCollectionEmployeeRecyclerView.adapter        = adapter
        getViewDataBinding().viewCollectionEmployeeRecyclerView.layoutManager  = layoutManager
        getViewDataBinding().viewCollectionEmployeeRecyclerView.itemAnimator   = null
        getViewDataBinding().lifecycleOwner                                    = this
    }

    override fun onEditClicked(employee: Employee) {
        navigate(NavigationCommand.To(FragmentListEmployeeDirections.toFragmentEditEmployee(employee)))
    }

    override fun onDeleteClick(employeeId: String, position: Int) {
        viewModel.deleteEmployee(employeeId)
        viewModel.deleteData.observe(viewLifecycleOwner, EventObserver{ success ->
            if (success){
                adapter.removeItems(position)
                getViewDataBinding().viewCollectionEmployeeRecyclerView.removeViewAt(position)
            } else {
                viewModel.showSnack.value = "Error Happen When Try To Delete Employee..."
            }
        })
    }

}