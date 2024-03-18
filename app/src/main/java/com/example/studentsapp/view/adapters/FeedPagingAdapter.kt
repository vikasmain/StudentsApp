package com.example.studentsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.FeedResponse

class FeedPagingAdapter :
    PagingDataAdapter<FeedResponse.FeedItem, FeedPagingAdapter.FeedViewHolder>(COMPARATOR) {

    class FeedViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindDetails(item: FeedResponse.FeedItem) {
            itemView.findViewById<TextView>(R.id.title).text = item.title
            itemView.findViewById<TextView>(R.id.description).text = item.description
        }
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bindDetails(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.feed_list_item, parent, false)
        return FeedViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<FeedResponse.FeedItem>() {
            override fun areItemsTheSame(
                oldItem: FeedResponse.FeedItem,
                newItem: FeedResponse.FeedItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FeedResponse.FeedItem,
                newItem: FeedResponse.FeedItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
