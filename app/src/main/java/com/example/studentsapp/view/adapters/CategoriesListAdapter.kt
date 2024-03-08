package com.example.studentsapp.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.studentsapp.data.CategoryItemData
import com.example.studentsapp.databinding.CategoriesHeaderLayoutBinding
import com.example.studentsapp.databinding.CategoriesItemBinding
import com.example.studentsapp.databinding.CategoriesSearchLayoutBinding

class CategoriesListAdapter : RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder>() {

    val categoriesList = mutableListOf<CategoryItemData>()

    fun updateCategoriesList(categoriesData: List<CategoryItemData>) {
        categoriesList.clear()
        categoriesList.addAll(categoriesData)
    }

    sealed class CategoriesViewHolder(view: View) : ViewHolder(view) {

        class CategoriesItemHolder(val view: CategoriesItemBinding) :
            CategoriesViewHolder(view.root) {

            fun bind(item: CategoryItemData.CategoryItem?) {
                view.title.text = item?.title
                view.description.text = item?.description
            }
        }

        class CategoriesHeader(val view: CategoriesHeaderLayoutBinding) :
            CategoriesViewHolder(view.root) {
            fun bind(categoryHeader: CategoryItemData.CategoryHeader?) {
                view.title.text = categoryHeader?.title
                view.description.text = categoryHeader?.description
            }
        }

        class CategoriesSearch(val view: CategoriesSearchLayoutBinding) :
            CategoriesViewHolder(view.root) {
            fun bind() {
                view.searchBar.visibility = View.VISIBLE
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
                    )
                )
            }

            CategoriesViewType.SEARCH.ordinal -> {
                CategoriesViewHolder.CategoriesSearch(
                    CategoriesSearchLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            CategoriesViewType.ITEM.ordinal -> {
                CategoriesViewHolder.CategoriesItemHolder(
                    CategoriesItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                CategoriesViewHolder.CategoriesItemHolder(
                    CategoriesItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (categoriesList[position]) {
            is CategoryItemData.CategoryItem -> CategoriesViewType.ITEM.ordinal
            is CategoryItemData.CategoryHeader -> CategoriesViewType.HEADER.ordinal
            CategoryItemData.SearchBar -> CategoriesViewType.SEARCH.ordinal
        }
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        when (holder) {
            is CategoriesViewHolder.CategoriesItemHolder -> {
                holder.bind(
                    categoriesList[position] as? CategoryItemData.CategoryItem
                )
            }

            is CategoriesViewHolder.CategoriesSearch -> {
                holder.bind()
            }

            is CategoriesViewHolder.CategoriesHeader -> {
                holder.bind(
                    categoriesList[position] as? CategoryItemData.CategoryHeader
                )
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