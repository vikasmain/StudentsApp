package com.example.studentsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.databinding.ActivityLoaderViewBinding

class PagingLoaderAdapter : LoadStateAdapter<PagingLoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindDetails(loadState: LoadState) {
            val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bindDetails(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view =
            ActivityLoaderViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(view.root)
    }
}
