package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.loadImage
import com.target.targetcasestudy.ui.DealItemAdapter.DealItemViewHolder
import java.util.*

class DealItemAdapter(val dealItemClickListener: DealItemClickListener) : ListAdapter<DealItem, DealItemViewHolder>(DealsDiffCallBack()) {

  lateinit var binding: DealListItemBinding

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {

    binding = DealListItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.deal_list_item, parent, false))
    return DealItemViewHolder(binding.root)
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = getItem(position)
    viewHolder.bindView(item, dealItemClickListener)

  }

  inner class DealItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(dealItem: DealItem, dealItemClickListener: DealItemClickListener) {
         binding.apply {
           dealListItemTitle.text = dealItem.title.capitalize(Locale.ROOT)
           dealListItemPrice.text = dealItem.regularPrice?.display_string
           loadImage(dealListItemImageView, dealItem.img_url)
           dealListItemAisleTxt.text = dealItem.aisle.toUpperCase(Locale.ROOT)

         }
           binding.root.setOnClickListener { dealItemClickListener.onClick(dealItem) }
    }
  }
}

   class DealsDiffCallBack: DiffUtil.ItemCallback<DealItem>() {
     override fun areItemsTheSame(oldItem: DealItem, newItem: DealItem): Boolean {
       return oldItem == newItem
     }

     override fun areContentsTheSame(oldItem: DealItem, newItem: DealItem): Boolean {
       return oldItem.id == newItem.id
     }
   }

  class DealItemClickListener(val clickListener: (dealItem :DealItem) -> Unit) {
    fun onClick(dealItem: DealItem) = clickListener(dealItem)
  }