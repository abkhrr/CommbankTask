package com.abkhrr.comm.bank.presentation.main.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abkhrr.comm.bank.R
import com.abkhrr.comm.bank.databinding.ItemEmployeeListBinding
import com.abkhrr.comm.bank.domain.dto.model.response.Employee
import com.abkhrr.comm.bank.presentation.main.fragment.list.listener.OnItemButtonClickListener
import com.abkhrr.comm.bank.presentation.main.fragment.list.listener.RecyclerDeleteButtonListener
import com.abkhrr.comm.bank.presentation.main.fragment.list.listener.RecyclerEditButtonListener


class EmployeeListAdapter(private val listenerButton: OnItemButtonClickListener)
    : RecyclerView.Adapter<EmployeeListAdapter.RVHolder>() {

    val items: MutableList<Employee> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RVHolder(ItemEmployeeListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItems(position: Int){
        items.removeAt(position)
        this.notifyItemRemoved(position)
        this.notifyItemRangeChanged(position, items.size)
        this.notifyDataSetChanged()
    }

    inner class RVHolder(private val binding: ItemEmployeeListBinding): RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(position: Int){
            val employee            = items[position]
            binding.textId.text     = halfBoldString(employee.id, R.string.employeeId, 4)
            binding.textName.text   = halfBoldString(
                employee.employeeName,
                R.string.employeeName,
                6
            )
            binding.textSalary.text = halfBoldString(
                employee.employeeSalary,
                R.string.employeeSalary,
                8
            )
            binding.textAge.text    = halfBoldString(employee.employeeAge, R.string.age, 4)

            binding.deleteListener  = RecyclerDeleteButtonListener { listenerButton.onDeleteClick(employee.id, position) }
            binding.editListener    = RecyclerEditButtonListener { listenerButton.onEditClicked(employee) }

            setCardBackGroundColor(position)
            binding.executePendingBindings()
        }

        private fun halfBoldString(text: String, resId: Int, spannableEnd: Int): SpannableString {
            val textToFormat    = binding.root.resources.getString(resId)
            val formattedString = String.format(textToFormat, text)
            val formattedResult = SpannableString(formattedString)

            formattedResult.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                spannableEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return formattedResult
        }

        private fun setCardBackGroundColor(position: Int){
            if(position % 2 == 0) {
                binding.parentView.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.cardStrongGray))
            } else {
                binding.parentView.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.cardSoftGray))
            }
        }
    }
}