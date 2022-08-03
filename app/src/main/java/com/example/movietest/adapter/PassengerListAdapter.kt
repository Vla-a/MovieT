package com.example.movietest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietest.R
import com.example.movietest.data.Results

class PassengerListAdapter : PagingDataAdapter<Results, PassengerListAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: PassengerListAdapter.MyViewHolder, position: Int) {

        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.image)
        private val tvName: TextView = view.findViewById(R.id.movie_title)
        private val tvDesc: TextView = view.findViewById(R.id.movie_desc)

        fun bind(data: Results) {
            tvName.text = data.displayTitle.toString()
            tvDesc.text = data.summaryShort.toString()

            Glide.with(imageView)
                .load(data.multimedia?.src)
                .circleCrop()
                .into(imageView)

        }
    }

}
class DiffUtilCallBack: DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.displayTitle == newItem.displayTitle
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.displayTitle == newItem.displayTitle
                && oldItem.mpaaRating == newItem.mpaaRating
    }

}