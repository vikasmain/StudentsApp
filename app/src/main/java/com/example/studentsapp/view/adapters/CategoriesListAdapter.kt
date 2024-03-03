package com.example.studentsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.studentsapp.databinding.CategoriesItemBinding
import com.example.studentsapp.model.CategoriesResponse

class CategoriesListAdapter : RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder>() {

    private val categoriesList = mutableListOf<CategoriesResponse.CategoriesData>()

    fun updateCategoriesList(categoriesData: List<CategoriesResponse.CategoriesData>) {
        categoriesList.clear()
        categoriesList.addAll(categoriesData)
    }

    class CategoriesViewHolder(view: View) : ViewHolder(view) {

        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            CategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind()
    }
}
