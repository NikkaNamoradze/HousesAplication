package com.example.sellhousesinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sellhousesinfo.databinding.HousesItemBinding
import com.example.sellhousesinfo.extensions.setImage
import com.example.sellhousesinfo.model.Content
import com.example.sellhousesinfo.utils.HousesDiffUtil


class HousesRecyclerAdapter : RecyclerView.Adapter<HousesRecyclerAdapter.HousesViewHolder>() {

    private var housesList = listOf<Content>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HousesRecyclerAdapter.HousesViewHolder {
        return HousesViewHolder(
            HousesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HousesRecyclerAdapter.HousesViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = housesList.size

    inner class HousesViewHolder(var binding: HousesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun onBind(){
                binding.tvPublishDate.text = housesList[adapterPosition].publishDate
                binding.ivCover.setImage(housesList[adapterPosition].cover)
                binding.tvTitle.text = housesList[adapterPosition].titleKA
                binding.tvDescription.text = housesList[adapterPosition].descriptionKA
            }
    }

    fun setData(data: List<Content>){
        val diffUtil = HousesDiffUtil(housesList, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        housesList = data
        diffResult.dispatchUpdatesTo(this)
    }
}