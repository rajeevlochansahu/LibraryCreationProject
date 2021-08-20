package com.rajeev.halodocapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rajeev.halodocapp.R
import com.rajeev.halodocapp.databinding.RecyclerViewItemRowBinding
import com.rajeev.halodocapp.model.MyData

class MyRecyclerViewAdapter internal constructor(
    private val listRowData: ArrayList<MyData>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<MyRecyclerViewAdapter.SummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder =
        SummaryViewHolder.from(parent)

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) = holder.bind(listRowData[position], clickListener)

    fun setData(list: List<MyData>) {
        //listRowData.clear()
        listRowData.addAll(list)
        notifyDataSetChanged()
    }

    class SummaryViewHolder(private val binding: RecyclerViewItemRowBinding, val mContext : Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData, listener: ItemClickListener) {
            binding.data = myData
            binding.itemClick = listener
            binding.titleTV.text = myData.title
            binding.authorTV.text = myData.author
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SummaryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: RecyclerViewItemRowBinding = DataBindingUtil
                        .inflate(layoutInflater, R.layout.recycler_view_item_row,
                                parent, false)
                return SummaryViewHolder(binding,parent.context)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClicked(data: MyData)
    }

    override fun getItemCount(): Int {
        return listRowData.size
    }
}