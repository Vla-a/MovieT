package com.example.movietest.adapter

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietest.data.Movie
import com.example.movietest.databinding.ItemMovieBinding


class MovieAdapter() : ListAdapter<Movie, MovieAdapter.AnalitycViewHolder>(DiffUtilItemCallbac()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.AnalitycViewHolder =
        AnalitycViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MovieAdapter.AnalitycViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class AnalitycViewHolder(
        private val bindingView: ItemMovieBinding

    ) : RecyclerView.ViewHolder(bindingView.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(item: Movie) {

            bindingView.movieTitle.text = item.name
            bindingView.movieDesc.text = item.about
            Glide
                .with(itemView.context)
                .load(item.image)
                .into(bindingView.image)
        }
    }
}

class DiffUtilItemCallbac : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.name == newItem.name
    }
}


