package com.bdpolice.kms.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bdpolice.kms.data.model.question.QuestionResponseItem
import com.bdpolice.kms.databinding.QuestionItemBinding
import com.bdpolice.kms.ui.viewmodel.SecurityQuestionVH
import javax.inject.Inject

class SecurityQuestionAdapter @Inject constructor() : ListAdapter<QuestionResponseItem, SecurityQuestionVH>(DIFFUTILS){

    private lateinit var click : onClick

    companion object{
        val DIFFUTILS = object : DiffUtil.ItemCallback<QuestionResponseItem>(){
            override fun areItemsTheSame(
                oldItem: QuestionResponseItem,
                newItem: QuestionResponseItem
            ): Boolean {
                return oldItem.SecurityNameId == newItem.SecurityNameId
            }

            override fun areContentsTheSame(
                oldItem: QuestionResponseItem,
                newItem: QuestionResponseItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecurityQuestionVH {
        val l = LayoutInflater.from(parent.context)
        val view = QuestionItemBinding.inflate(l, parent, false)
        return SecurityQuestionVH(view)
    }

    override fun onBindViewHolder(holder: SecurityQuestionVH, position: Int) {
        holder.questionItemBinding.apply {
            question = getItem(position)

            holder.itemView.setOnClickListener {
                if(holder.bindingAdapterPosition != RecyclerView.NO_POSITION){
                    click.click(getItem(position))
                }
            }
        }
    }

    interface onClick{
        fun click(item: QuestionResponseItem)
    }
    fun setOnClick(onClick: onClick) {
        click = onClick
    }

}