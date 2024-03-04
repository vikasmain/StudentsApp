package com.example.studentsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.R
import com.example.studentsapp.databinding.FragmentCategoriesListBinding
import com.example.studentsapp.view.adapters.CategoriesListAdapter
import com.example.studentsapp.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesListFragment : Fragment() {

    private val categoriesViewModel: CategoriesViewModel by viewModels()
    lateinit var fragmentCategoriesBinding: FragmentCategoriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentCategoriesBinding = FragmentCategoriesListBinding.inflate(layoutInflater)
        return fragmentCategoriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            categoriesViewModel.feedListStateFlow.onEach {
                val adapter = CategoriesListAdapter()
                fragmentCategoriesBinding.categoriesList.adapter = adapter
                fragmentCategoriesBinding.categoriesList.layoutManager =
                    LinearLayoutManager(activity)
                it?.let { it1 -> adapter.updateCategoriesList(it1) }
            }.catch {

            }
        }
    }
}
