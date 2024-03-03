package com.example.studentsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.studentsapp.databinding.CategoriesHeaderLayoutBinding
import com.example.studentsapp.databinding.CategoriesItemBinding
import com.example.studentsapp.databinding.CategoriesSearchLayoutBinding
import com.example.studentsapp.model.CategoriesResponse

class CategoriesListAdapter : RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder>() {

    private val categoriesList = mutableListOf<CategoriesResponse.CategoriesData>()

    fun updateCategoriesList(categoriesData: List<CategoriesResponse.CategoriesData>) {
        categoriesList.clear()
        categoriesList.addAll(categoriesData)
    }

    sealed class CategoriesViewHolder(view: View) : ViewHolder(view) {

        class CategoriesItemHolder(view: View) : CategoriesViewHolder(view) {

            fun bind() {

            }
        }

        class CategoriesHeader(view: View) : CategoriesViewHolder(view) {
            fun bind() {

            }
        }

        class CategoriesSearch(view: View) : CategoriesViewHolder(view) {
            fun bind() {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return when (viewType) {
            CategoriesViewType.HEADER.ordinal -> {
                CategoriesViewHolder.CategoriesHeader(
                    CategoriesHeaderLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).root
                )
            }

            CategoriesViewType.SEARCH.ordinal -> {
                CategoriesViewHolder.CategoriesSearch(
                    CategoriesSearchLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).root
                )
            }

            else -> {
                CategoriesViewHolder.CategoriesItemHolder(
                    CategoriesItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).root
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        when (holder) {
            is CategoriesViewHolder.CategoriesItemHolder -> {
                holder.bind()
            }

            is CategoriesViewHolder.CategoriesSearch -> {
                holder.bind()
            }

            is CategoriesViewHolder.CategoriesHeader -> {
                holder.bind()
            }

            else -> {}
        }
    }
}

enum class CategoriesViewType {
    HEADER,
    ITEM,
    SEARCH
}