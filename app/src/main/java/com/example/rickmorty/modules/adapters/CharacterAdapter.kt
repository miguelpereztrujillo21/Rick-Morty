package com.example.rickmorty.modules.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ItemCharacterBinding
import com.example.rickmorty.modules.helpers.Utils
import com.example.rickmorty.modules.models.Character

class CharacterAdapter(val context: Context, private val clickListener: ClickListener) :
    androidx.recyclerview.widget.ListAdapter<Character, CharacterAdapter.ViewHolder>(
        CountryDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            context: Context,
            item: Character,
            clickListener: ClickListener
        ) {
            Glide.with(context)
                .load(item.image)
                .into(binding.imageCharacterItem)



                binding.imageStatusCharacterItem.setImageResource(Utils.getIsAlive(item.status))


            binding.name = item.name?.let { Utils.addEllipsisToSentence(it) }
            binding.cardCharacterItem.setOnClickListener {
                clickListener.onClick(adapterPosition)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    class CountryDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun onClick(position: Int)
    }
}