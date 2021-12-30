package com.example.parsinglocaljson

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljson.databinding.ItemRowBinding

class RecycleViewAdpter(var list : ArrayList<DataItem>,var context: Context) : RecyclerView.Adapter<RecycleViewAdpter.HolderItem>(){
    class HolderItem(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItem {
        return HolderItem(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HolderItem, position: Int) {
        holder.binding.apply {
            tvTitle.text = list[position].title
            Glide.with(context)
                .load(list[position].url)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int = list.size

    fun update(newList : ArrayList<DataItem>){
        list = newList
        notifyDataSetChanged()
    }
}