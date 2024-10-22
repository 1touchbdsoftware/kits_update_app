package com.bdpolice.kms.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bdpolice.kms.data.model.kits.KitsListResponseItem
import com.bdpolice.kms.databinding.KitsItemBinding
import com.bdpolice.kms.ui.viewholder.KitsItemViewHolder
import javax.inject.Inject

class KitsListAdapter @Inject constructor() : ListAdapter<KitsListResponseItem, KitsItemViewHolder>(DIFF_CALLBACK) {

    private lateinit var click: onClickViewMore
    private lateinit var commentClick : onClickComment

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<KitsListResponseItem>() {
            override fun areItemsTheSame(oldItem: KitsListResponseItem, newItem: KitsListResponseItem): Boolean {
                return oldItem.Serial_VW == newItem.Serial_VW
            }

            override fun areContentsTheSame(oldItem: KitsListResponseItem, newItem: KitsListResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitsItemViewHolder {
        val l = LayoutInflater.from(parent.context)
        val view = KitsItemBinding.inflate(l, parent, false)
        return KitsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: KitsItemViewHolder, position: Int) {
        holder.kitsItemBinding.apply {
            kitsList = getItem(position)
            ArrowIcon.setOnClickListener {
                val position = holder.bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    click.onClickViewMore(getItem(position), position)
                }
            }
            CommentBtn.setOnClickListener {
                val position = holder.bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    commentClick.comment(getItem(position), position)
                }
            }
        }
    }


    interface onClickViewMore{
        fun onClickViewMore(item: KitsListResponseItem, position: Int)
    }
    fun onClickViewState(onClickViewMore: onClickViewMore){
        this.click = onClickViewMore
    }

    interface onClickComment{
        fun comment(item: KitsListResponseItem, position: Int)
    }
    fun onClickCommentState(comment: onClickComment){
        this.commentClick = comment
    }
}