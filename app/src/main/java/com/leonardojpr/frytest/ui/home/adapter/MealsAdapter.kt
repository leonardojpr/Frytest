package com.leonardojpr.frytest.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leonardojpr.frytest.databinding.ItemFoodBinding
import com.leonardojpr.frytest.domain.api.response.MealsItem

class MealsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<MealsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MealsViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun addMeals(item: MealsItem) {
        items.add(item)
        notifyDataSetChanged()
    }

    class MealsViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MealsItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}