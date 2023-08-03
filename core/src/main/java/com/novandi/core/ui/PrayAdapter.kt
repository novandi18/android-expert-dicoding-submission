package com.novandi.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.novandi.core.databinding.ItemListPrayBinding
import com.novandi.core.domain.model.Pray

class PrayAdapter : RecyclerView.Adapter<PrayAdapter.ListViewHolder>() {
    private lateinit var binding: ItemListPrayBinding
    private lateinit var onItemClickCallback: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayAdapter.ListViewHolder {
        binding = ItemListPrayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder()
    }

    override fun onBindViewHolder(holder: PrayAdapter.ListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ListViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pray) {
            with(binding) {
                tvItemAyat.text = data.ayat
                tvItemDoa.text = data.doa
                tvItemLatin.text = data.latin
                cvPray.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }
            }
        }
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClickCallback = onItemClick
    }

    interface OnItemClick {
        fun onItemClick(data: Pray)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Pray>() {
        override fun areItemsTheSame(oldItem: Pray, newItem: Pray): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Pray, newItem: Pray): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)
}