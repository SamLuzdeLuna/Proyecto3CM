package com.example.proyecto3cm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto3cm.databinding.ListElementBinding
import com.example.proyecto3cm.model.Game

class Adaptador(context: Context, games: Game, onItemListener: OnItemListener): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val games = games
    private val mOnItemListener = onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListElementBinding.inflate(layoutInflater)

        return ViewHolder(binding, mOnItemListener)
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.bindData(games.results!![position])
    }

    override fun getItemCount(): Int {
        return games.results!!.size

    }

    interface  OnItemListener{
        fun onItemClick(game: Game.Animal)
    }

    class ViewHolder(binding: ListElementBinding, onItemListener: OnItemListener):RecyclerView.ViewHolder(binding.root), View.OnClickListener{


        private val tvPokeName = binding.tvPokeName
        private val context = binding.root.context
        private  val onItemListener = onItemListener
        private lateinit var game: Game.Animal

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?){
            onItemListener.onItemClick(game)
        }

        fun bindData(item: Game.Animal){
            tvPokeName.text = item.name
            game = item
        }


    }

}